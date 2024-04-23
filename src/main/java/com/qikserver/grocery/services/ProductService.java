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
            double productValue = product.getPrice();
            double itemTotal = productValue * quantity;
            double itemSavings = 0;

            for (Cupom promotion : product.getPromotions()) {
                double[] result = applyDiscount(promotion.getType(), itemTotal, productValue, quantity, promotion,
                        itemSavings);
                itemTotal = result[0];
                itemSavings = result[1];
            }

            itemCheckouts.add(new Order(product, quantity, itemTotal, itemSavings));
        }

        return itemCheckouts;
    }

    private double[] applyDiscount(CupomType type, double itemTotal, double productValue,
            int quantity, Cupom promotion, double itemSavings) {
        switch (type) {
            case FLAT_PERCENT:
                return ImplementFlatCupom(itemTotal, productValue, quantity, promotion.getAmount(), itemSavings);
            case BUY_X_GET_Y_FREE:
                return ImplementBuyXGetYCupom(itemTotal, productValue, quantity, promotion.getRequiredQty(),
                        itemSavings);
            case QTY_BASED_PRICE_OVERRIDE:
                return ImplementOverRideCupom(itemTotal, productValue, quantity,
                        promotion.getRequiredQty(), promotion.getPrice(), itemSavings);
            default:

                return new double[] { roundToTwoDecimals(itemTotal), roundToTwoDecimals(itemSavings) };
        }
    }

    private double[] ImplementBuyXGetYCupom(double itemTotal, double productValue, int quantity,
            int requiredQty, double itemSavings) {
        double qtFree = Math.floor(quantity / requiredQty);
        double totalFreeItemsValue = productValue * qtFree;

        itemTotal -= totalFreeItemsValue;
        itemSavings += totalFreeItemsValue;

        return new double[] { roundToTwoDecimals(itemTotal), roundToTwoDecimals(itemSavings) };
    }

    private double[] ImplementOverRideCupom(double itemTotal, double productValue,
            int quantity, int requiredQty, double promoPrice,
            double itemSavings) {
        int qtPromo = (int) (quantity / requiredQty);
        int qtNonPromo = (int) (quantity % requiredQty);

        double totalDiscountedPrice = qtPromo * promoPrice;

        if (qtNonPromo > 0) {
            totalDiscountedPrice += productValue;
        }

        itemSavings += itemTotal - totalDiscountedPrice;
        itemTotal -= itemSavings;

        return new double[] { roundToTwoDecimals(itemTotal), roundToTwoDecimals(itemSavings) };
    }

    private double[] ImplementFlatCupom(double itemTotal, double productValue, int quantity,
            double discountPercent, double itemSavings) {
        double discountAmount = productValue * (discountPercent / 100);
        double totalDiscount = discountAmount * quantity;

        itemTotal -= totalDiscount;
        itemSavings += totalDiscount;

        return new double[] { roundToTwoDecimals(itemTotal), roundToTwoDecimals(itemSavings) };
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}