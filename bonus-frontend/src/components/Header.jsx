import { useContext } from "react";
import Button from "./UI/Button";
import CartContext from "./store/CartContext";
import UserProgressContext from "./store/UserProgressContext";

export default function Header() {
  const cartContext = useContext(CartContext);
  const userProgressCtx = useContext(UserProgressContext);
  const totalCartItems = cartContext.items.reduce(
    (totalNumberOfItems, items) => {
      return totalNumberOfItems + items.quantity;
    },
    0
  );
  function handleShowCart() {
    userProgressCtx.showCart();
  }
  return (
    <header id="main-header">
      <div id="title">
        <h1>React Grocery</h1>
      </div>
      <nav>
        <Button textOnly onClick={handleShowCart}>
          Cart({totalCartItems})
        </Button>
      </nav>
    </header>
  );
}
