import { useContext } from "react";
import Modal from "./UI/Modal";
import CartContext from "./store/CartContext";
import Button from "./UI/Button";
import UserProgressContext from "./store/UserProgressContext";
import CartItem from "./CartItem";
import { currencyFormatter } from "../util/formatting.js";

export function Cart() {
  const cartContext = useContext(CartContext);
  const userProgressCtx = useContext(UserProgressContext);
  function handleCloseCart() {
    userProgressCtx.hideCart();
  }
  function handleGoToCheckout() {
    userProgressCtx.showCheckout();
  }
  const cartTotal = cartContext.items.reduce(
    (totalPrice, item) => totalPrice + item.quantity * item.price,
    0
  );
  return (
    <Modal
      className="cart"
      open={userProgressCtx.progress === "cart"}
      onClose={userProgressCtx.progress === "cart" ? handleCloseCart : null}
    >
      <h2>Your cart </h2>
      <ul>
        {cartContext.items.map((item) => (
          <CartItem
            key={item.id}
            name={item.name}
            qty={item.quantity}
            price={item.price}
            onIcrease={() => cartContext.addItem(item)}
            onDecrease={() => cartContext.removeItem(item.id)}
          />
        ))}
      </ul>
      <p className="cart-total">{currencyFormatter.format(cartTotal)}</p>
      <p className="modal-actions">
        <Button textOnly onClick={handleCloseCart}>
          Close
        </Button>
        <Button onClick={handleGoToCheckout}>Go to checkout</Button>
      </p>
    </Modal>
  );
}

export default Cart;
