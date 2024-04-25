export default function Button({
  children,
  textOnly,
  textChange,
  className,
  ...props
}) {
  let buttonText = children;
  if (textChange) {
    buttonText = "Add one more to the cart";
  }

  let cssClasses = textOnly ? `text-button` : "button";
  cssClasses += " " + className;

  return (
    <button className={cssClasses} {...props}>
      {buttonText}
    </button>
  );
}
