import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Navigation from "./Components/Navigation";
import Home from "./Components/Home";
import Footer from "./Components/Footer";
import About from "./Components/About";
import Contact from "./Components/Contact";
import Experimental from "./Components/Experimental";

function App() {
  const BASE_PATH = "/wallpaperscool";
  return <div className="App">
    <BrowserRouter>
    <Navigation />
        <Routes>
          <Route path={BASE_PATH + "/"} element={<Home />} />
          <Route path={BASE_PATH + "/about"} element={<About />} />
          <Route path={BASE_PATH + "/contact"} element={<Contact />} />
          <Route path={BASE_PATH + "/experimental"} element={<Experimental />} />
        </Routes>
        <Footer />
    </BrowserRouter>
  </div>;
}

export default App;
