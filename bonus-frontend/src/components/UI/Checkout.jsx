import { useContext } from "react";
import Modal from "./Modal.jsx";
import CartContext from "../store/CartContext.jsx";
import Error from "../Error.jsx";
import Button from "./Button.jsx";
import UserProgressContext from "../store/UserProgressContext.jsx";
import useHttp from "../hooks/useHttp.js";
import { currencyFormatter } from "./../../util/formatting.js";

const requestConfig = {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
};

export default function Checkout() {
  const cartCtx = useContext(CartContext);
  const userProgressCtx = useContext(UserProgressContext);

  const {
    data,
    isLoading: isSending,
    error,
    sendRequest,
    clearData,
  } = useHttp("http://localhost:8080/addCart", requestConfig);

  const cartTotal = cartCtx.items.reduce(
    (totalPrice, item) => totalPrice + item.quantity * item.price,
    0
  );

  function handleClose() {
    userProgressCtx.hideCheckout();
  }

  function handleFinish() {
    userProgressCtx.hideCheckout();
    cartCtx.clearCart();
    window.location.reload();
  }

  function handleSubmit(event) {
    event.preventDefault();

    sendRequest(
      JSON.stringify(
        cartCtx.items.map((item) => ({
          productId: item.id,
          quantity: item.quantity,
        }))
      )
    );
  }

  let actions = (
    <>
      <Button type="button" textOnly onClick={handleClose}>
        Close
      </Button>
      <Button>Submit Order</Button>
    </>
  );

  if (isSending) {
    actions = <span>Sending order data...</span>;
  }

  if (data && !error) {
    return (
      <Modal
        open={userProgressCtx.progress === "checkout"}
        onClose={handleFinish}
      >
        <h2>Success!</h2>
        <p className="product-item-description">
          Your order was submitted successfully.
        </p>
        {data.map((item, index) => (
          <div key={index}>
            <ul>
              {item.product.promotions.map((promotion, promoIndex) => (
                <li key={promoIndex}> Cupom unlocked: {promotion.type}</li>
              ))}
            </ul>
            <p className="product-item-description">
              Product Name: {item.product.name}
            </p>
            <p className="product-item-description">
              Quantity: {item.quantity}
            </p>

            <p className="product-item-description">
              {" "}
              Your Savings with the Cupom:{" "}
              {currencyFormatter.format(item.savings)}
            </p>

            <p className="product-item-description">
              Item Total: {currencyFormatter.format(item.itemTotal)}
            </p>
          </div>
        ))}

        <p className="modal-actions">
          <Button onClick={handleFinish}>Ok</Button>
        </p>
      </Modal>
    );
  }

  return (
    <Modal open={userProgressCtx.progress === "checkout"} onClose={handleClose}>
      <form onSubmit={handleSubmit}>
        <h2>Checkout</h2>
        <p>Total Amount: {currencyFormatter.format(cartTotal)}</p>

        {error && <Error title="Failed to submit order" message={error} />}

        <p className="modal-actions">{actions}</p>
      </form>
    </Modal>
  );
}
