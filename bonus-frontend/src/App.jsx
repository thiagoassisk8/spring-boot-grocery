import Cart from "./components/Cart.jsx";
import Header from "./components/Header.jsx";
import Products from "./components/Products.jsx";
import Checkout from "./components/UI/Checkout.jsx";
import { CartContextProvider } from "./components/store/CartContext";
import { UserProgressContextProvider } from "./components/store/UserProgressContext.jsx";

export function App() {
  return (
    <UserProgressContextProvider>
      <CartContextProvider>
        <Header />
        <Products />
        <Cart />
        <Checkout />
      </CartContextProvider>
    </UserProgressContextProvider>
  );
}

export default App;
