const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const Product = require("./models/Product");
const User = require("./models/User");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");

const app = express();
app.use(cors());
app.use(express.json());

mongoose.connect("mongodb://localhost:27017/mern_cart");

const JWT_SECRET = "your_secret_key_here";

app.post("/register", async (req, res) => {
  const { email, password } = req.body;
  const existingUser = await User.findOne({ email });
  if (existingUser) return res.status(400).json({ message: "User exists" });

  const hashed = await bcrypt.hash(password, 10);
  await new User({ email, password: hashed }).save();
  res.status(201).json({ message: "User created" });
});

app.post("/login", async (req, res) => {
  const { email, password } = req.body;
  const user = await User.findOne({ email });
  if (!user || !(await bcrypt.compare(password, user.password))) {
    return res.status(400).json({ message: "Invalid credentials" });
  }

  const token = jwt.sign({ userId: user._id }, JWT_SECRET);
  res.json({ token });
});

app.get("/products", async (req, res) => {
  const products = await Product.find();
  res.json(products);
});

app.get("/seed", async (req, res) => {
  await Product.deleteMany();
  await Product.insertMany([
    { name: "Laptop", price: 999 },
    { name: "Phone", price: 499 },
    { name: "Headphones", price: 199 }
  ]);
  res.send("Seeded");
});

app.listen(5000, () => console.log("Server started on port 5000"));
