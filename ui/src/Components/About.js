import React from "react";
import "./about.css";
import profile from "../Assets/gallery.png";
import instagram from "../Assets/instagram-brands-solid.svg";
import linkedin from "../Assets/linkedin-brands-solid.svg";

export default function About() {
  return (
    <div className="about">
      <div className="left">
        <div className="title2">I create human-centric designs.</div>
        <div className="title2">I Humanize the Technology</div>
        <p>
          “I’m a curious UX designer who believes empathy is a natural part of
          being human, helping us connect and solve problems together. I love
          meeting new people, learning how they see challenges, and exploring
          creative solutions. For me, UX isn’t just about what we create—it’s
          about the process of designing thoughtful experiences that truly make
          a difference. My goal is to understand people better and craft designs
          that matter."
        </p>

        <a href="mailto:sagarwan712@gmail.com">sagarwan712@gmail.com</a>

        <div className="actions">
          <a href="https://www.instagram.com/saagarr_7/profilecard/?igsh=MXY0ZjZvdjFkY3lzbw=="><img src={instagram} alt="logo here" /></a>
          <a href="https://www.linkedin.com/in/sagar-wankhade-9b8791168/"><img src={linkedin} alt="logo here" /></a>
        </div>
      </div>
      <div className="right">
        <img src={profile} alt="logo here" />
      </div>
    </div>
  );
}
