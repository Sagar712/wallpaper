import React, { useState, useEffect } from "react";
import "./home.css";
import Placeholders from "./Placeholders";

export default function Home() {
  const [data, setData] = useState([]);
//   const [refresh, setRefresh] = useState(true);
  useEffect(() => {
    const URI = "http://localhost:8088/data/getIds";
    const fetchData = async () => {
      const res = await fetch(URI).then((resp) => resp.json());
      const arr = []
      for (let i = 0; i < res.length; i++) {
        arr.push(res[i])
        setData(arr)
      }
      console.log(arr);
      
    };

    fetchData();
  }, []);
  return (
    <>
      <div className="home">
        <div className="imageContainer">
        {data.length > 0 
        ? data.map((elem, key) => <Placeholders elem={elem} key={key}/>)
        : <p>Loading...</p>}
        </div>
      </div>

      {/* <About /> */}
    </>
  );
}
