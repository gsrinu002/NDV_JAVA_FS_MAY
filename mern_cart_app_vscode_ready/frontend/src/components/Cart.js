import React from "react";
import { useCart } from "../context/CartContext";

export default function Cart() {
  const { cart, dispatch } = useCart();
  return (
    <div>
      <h2>Cart</h2>
      {cart.map((item, i) => (
        <div key={i}>
          {item.name} - ₹{item.price}
          <button onClick={() => dispatch({ type: "REMOVE_ITEM", payload: i })}>Remove</button>
        </div>
      ))}
      <p>Total: ₹{cart.reduce((sum, item) => sum + item.price, 0)}</p>
    </div>
  );
}
