import "./placeholders.css";

export default function Placeholders(props) {
    let i = 0;
    const prepare_title = (elem) => {
        let title = elem.title.replace(".jpeg", "").replace(".jpg", "").replace(".png", "").replaceAll("-", " ").replaceAll("_", " ")
        return `${title} (${elem.size})`
    }
  return (
    <div className="placeholder">
      <img src={props.elem.uri} key={i++} className="myImage" alt=""/>
      <p>{prepare_title(props.elem)}</p>
    </div>
  );
}