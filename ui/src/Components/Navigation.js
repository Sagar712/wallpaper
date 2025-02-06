import React, { useState } from "react";
import "./navigation.css";
import logo from "../Assets/gallery.png";
import bar from "../Assets/bars-solid.svg";
import cross from "../Assets/x-solid.svg";
import { Link } from "react-router-dom";

export default function Navigation() {
  const [num, setNum] = useState(0);
  const [selected, setSelected] = useState(1);
  const BASE_URL = "/wallpaperscool";

  function popupmenu() {
    // let cross = document.querySelector(".bar");
    let menu = document.querySelector(".menuitems");
    // cross.classList.toggle("active");
    menu.classList.toggle("active");
    setNum(num + 1);
  }

  function handleClick(key) {
    setSelected(key);
  }

  return (
    <>
      <nav>
        <Link to={BASE_URL + "/"} style={{ textDecoration: "none" }}>
          <div className="hero">
            <img src={logo} alt="logo here" />
            <p>Wallpaper Scool</p>
          </div>
        </Link>
        <div className="menu_list">
          <Link
            onClick={() => handleClick(1)}
            className={`tabOptions ${selected === 1 ? "active" : ""}`}
            to={BASE_URL + "/"}
          >
            Home
          </Link>
          <Link
            onClick={() => handleClick(2)}
            className={`tabOptions ${selected === 2 ? "active" : ""}`}
            to={BASE_URL + "/contact"}
          >
            Contact
          </Link>
          <Link
            onClick={() => handleClick(3)}
            className={`tabOptions ${selected === 3 ? "active" : ""}`}
            to={BASE_URL + "/about"}
          >
            About
          </Link>
          <Link
            onClick={() => handleClick(4)}
            className={`tabOptions ${selected === 4 ? "active" : ""}`}
            to={BASE_URL + "/experimental"}
          >
            Upload
          </Link>
        </div>

        <div className="bar" onClick={popupmenu}>
          <img className="bar_icon" src={bar} alt="Bar Icon" />
        </div>
      </nav>

      {/* for mobile view */}
      <div className="menuitems">
        <div className="closebtnn" onClick={popupmenu}>
          <img className="cross_icon" src={cross} alt="Cross Icon" />
        </div>
        <Link onClick={popupmenu} className="tabOptions" to={BASE_URL + "/"}>
          Home
        </Link>
        <Link
          onClick={popupmenu}
          className="tabOptions"
          to={BASE_URL + "/contact"}
        >
          Contact
        </Link>
        <Link
          onClick={popupmenu}
          className="tabOptions"
          to={BASE_URL + "/about"}
        >
          About
        </Link>
        <Link
          onClick={popupmenu}
          className="tabOptions"
          to={BASE_URL + "/experimental"}
        >
          Upload
        </Link>
        
      </div>
    </>
  );
}
