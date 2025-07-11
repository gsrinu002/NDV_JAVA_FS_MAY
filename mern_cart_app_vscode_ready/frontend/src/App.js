import React, { useState } from "react";
import { CartProvider } from "./context/CartContext";
import ProductList from "./components/ProductList";
import Cart from "./components/Cart";
import AuthForm from "./components/AuthForm";

export default function App() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  return (
    <CartProvider>
      <div className="p-6">
        <h1>MERN Cart</h1>
        {!token ? <AuthForm setToken={setToken} /> : <>
          <ProductList />
          <Cart />
        </>}
      </div>
    </CartProvider>
  );
}
