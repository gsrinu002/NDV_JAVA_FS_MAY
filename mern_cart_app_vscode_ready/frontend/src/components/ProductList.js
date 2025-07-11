import React, { useEffect, useState } from "react";
import { useCart } from "../context/CartContext";

export default function ProductList() {
  const { dispatch } = useCart();
  const [products, setProducts] = useState([]);
  useEffect(() => {
    fetch("http://localhost:5000/products")
      .then(res => res.json())
      .then(data => setProducts(data));
  }, []);
  return (
    <>
      <h2>Products</h2>
      {products.map(p => (
        <div key={p._id}>
          {p.name} - ₹{p.price}
          <button onClick={() => dispatch({ type: "ADD_ITEM", payload: p })}>
            Add to Cart
          </button>
        </div>
      ))}
    </>
  );
}
