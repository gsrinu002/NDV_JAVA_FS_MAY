import React, { useState } from "react";

export default function AuthForm({ setToken }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isRegister, setIsRegister] = useState(false);
  const [error, setError] = useState("");

  const submit = async (e) => {
    e.preventDefault();
    const url = \`http://localhost:5000/\${isRegister ? "register" : "login"}\`;
    const res = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });
    const data = await res.json();
    if (res.ok && data.token) {
      localStorage.setItem("token", data.token);
      setToken(data.token);
    } else {
      setError(data.message);
    }
  };

  return (
    <form onSubmit={submit}>
      <h3>{isRegister ? "Register" : "Login"}</h3>
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" required />
      <input value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Password" required />
      <button type="submit">{isRegister ? "Register" : "Login"}</button>
      <button type="button" onClick={() => setIsRegister(!isRegister)}>
        Switch to {isRegister ? "Login" : "Register"}
      </button>
      {error && <p style={{color: "red"}}>{error}</p>}
    </form>
  );
}
