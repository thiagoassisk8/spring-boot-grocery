import ProductItem from "./ProductItem.jsx";
import Error from "./Error.jsx";
import useHttp from "./hooks/useHttp.js";
const reqConfig = {};
export default function Products() {
  const {
    data: loadedProducts,
    isLoading,
    error,
  } = useHttp("http://localhost:8080/products", reqConfig, []);

  if (isLoading) {
    return <p className="center">Fetching products</p>;
  }
  if (error) {
    return <Error title="Failed" message={error}></Error>;
  }
  return (
    <ul id="products">
      {loadedProducts.map((product) => (
        <ProductItem key={product.id} product={product} />
      ))}
    </ul>
  );
}
