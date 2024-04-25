import { useState, useEffect } from "react";
import ProductItem from "./ProductItem";

export default function ProductsReverse() {
  const [loadedReverseProducts, setloadedReverseProducts] = useState([]);
  useEffect(() => {
    async function fetchReverseProducts() {
      const res = await fetch("http://localhost:3000/reverseproducts");
      console.log("res");
      console.log(res);
      const products = await res.json();
      console.log("products");
      console.log(products);
      setloadedReverseProducts(products);
    }
    fetchReverseProducts();
  }, []);

  return (
    <ul id="products">
      {loadedReverseProducts.map((product) => (
        <ProductItem key={product.id} product={product} />
      ))}
    </ul>
  );
}
