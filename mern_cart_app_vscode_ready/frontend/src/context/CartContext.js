import React, { createContext, useContext, useReducer } from "react";
const CartContext = createContext();
const reducer = (state, action) => {
  if (action.type === "ADD_ITEM") return [...state, action.payload];
  if (action.type === "REMOVE_ITEM") return state.filter((_, i) => i !== action.payload);
  return state;
};

export function CartProvider({ children }) {
  const [cart, dispatch] = useReducer(reducer, []);
  return <CartContext.Provider value={{ cart, dispatch }}>{children}</CartContext.Provider>;
}
export const useCart = () => useContext(CartContext);
