import { currencyFormatter } from "../util/formatting.js";

export default function CartItem({ name, qty, price, onIcrease, onDecrease }) {
  return (
    <li className="cart-item">
      <p>
        {name} - {qty} x {currencyFormatter.format(price)}
      </p>
      <p className="cart-item-actions">
        <button
          onClick={() => {
            if (qty > 0) {
              onDecrease();
            }
          }}
        >
          -
        </button>
        <span>{qty}</span>
        <button onClick={onIcrease}>+</button>
      </p>
    </li>
  );
}
