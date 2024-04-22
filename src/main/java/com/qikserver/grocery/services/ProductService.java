package com.qikserver.grocery.services;

import com.qikserver.grocery.entities.CartItem;
import com.qikserver.grocery.entities.Cupom;
import com.qikserver.grocery.entities.CupomType;
import com.qikserver.grocery.entities.Order;
import com.qikserver.grocery.entities.Product;
import com.qikserver.grocery.client.ExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ExternalApi ExternalApi;

    @Autowired
    public ProductService(ExternalApi ExternalApi) {
        this.ExternalApi = ExternalApi;
    }

    public List<Product> fetchProducts() throws IOException {
        return this.ExternalApi.fetchProducts();
    }

    public Product fetchProductById(String productId) throws IOException {
        return this.ExternalApi.fetchProductById(productId);
    }

    public List<Order> addToCart(List<CartItem> items) throws IOException {
        List<Order> itemCheckouts = new ArrayList<>();

        for (CartItem item : items) {
            Product product = this.ExternalApi.fetchProductById(item.getProductId());

            int quantity = item.getQuantity();

            BigDecimal productValue = new BigDecimal(product.getPrice());
            BigDecimal itemTotal = productValue.multiply(BigDecimal.valueOf(quantity));
            BigDecimal itemSavings = BigDecimal.ZERO;
            for (Cupom promotion : product.getPromotions()) {
                if (promotion.getType() == CupomType.FLAT_PERCENT) {
                    BigDecimal discountPercentage = BigDecimal.valueOf(promotion.getAmount())
                            .divide(BigDecimal.valueOf(100));
                    BigDecimal discountAmount = productValue.multiply(discountPercentage);

                    itemTotal = itemTotal.subtract(discountAmount.multiply(BigDecimal.valueOf(quantity)));
                    itemSavings = itemSavings.add(discountAmount.multiply(BigDecimal.valueOf(quantity)));
                } else if (promotion.getType() == CupomType.BUY_X_GET_Y_FREE) {
                    BigDecimal qtFree = BigDecimal.valueOf(quantity).divide(new BigDecimal(promotion.getRequiredQty()));

                    itemTotal = itemTotal.subtract(qtFree.multiply(productValue));
                    itemSavings = itemSavings.add(qtFree.multiply(productValue));
                } else if (promotion.getType() == CupomType.QTY_BASED_PRICE_OVERRIDE) {
                    int requiredQt = promotion.getRequiredQty();
                    BigDecimal promoPrice = new BigDecimal(promotion.getPrice());

                    int qtPromo = quantity / requiredQt;
                    int qtNonPromo = quantity % requiredQt;

                    BigDecimal totalDesc = new BigDecimal(qtPromo).multiply(promoPrice);

                    if (qtNonPromo > 0) {
                        totalDesc = totalDesc.add(productValue);
                    }

                    itemSavings = itemSavings.add(itemTotal.subtract(totalDesc));
                    itemTotal = itemTotal.subtract(itemSavings);
                } else {

                }
            }

            Order dto = new Order(product, quantity, itemTotal, itemSavings);
            itemCheckouts.add(dto);

        }

        return itemCheckouts;
    }

}
