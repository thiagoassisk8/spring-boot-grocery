import { useState, useContext } from "react";
import Button from "./UI/Button";
import CartContext from "./store/CartContext.jsx";
import { currencyFormatter } from "../util/formatting.js";

export default function ProductItem({ product }) {
  const [addedToCart, setAddedToCart] = useState(false);
  const cartContext = useContext(CartContext);

  function handleAddProductItem() {
    cartContext.addItem(product);
    setAddedToCart(true);
  }

  return (
    <li className="product-item">
      <article>
        <img
          src={`http://localhost:3000/${product.image}`}
          alt={product.name}
        ></img>

        <div>
          <h3>{product.name}</h3>
          <p className="product-item-price">
            {currencyFormatter.format(product.price)}
          </p>
        </div>
        <p className="product-item-actions">
          <Button onClick={handleAddProductItem} textChange={addedToCart}>
            Add to the cart
          </Button>
        </p>
      </article>
    </li>
  );
}
