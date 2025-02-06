import React from "react";
import "./contact.css";
import phones_wall from "../Assets/phoness.jpg";

export default function Contact() {
  return (
    <div className="contact">
      <div className="left">
        <img src={phones_wall} alt="logo here" />
      </div>
      <div className="right">
        <div>
          <div className="title2">Hi there!</div>
          <label>Full name *</label>
          <input name="myInput" placeholder="Full name" />
          <label>Email *</label>
          <input name="myInput" placeholder="abc@gmail.com" type="email" />
          <label>Phone (Optional)</label>
          <input name="myInput" placeholder="Phone" />
          <label>Message *</label>
          <textarea name="myInput" placeholder="Message" rows={"4"} />
          <button>Send</button>
        </div>
      </div>
    </div>
  );
}
