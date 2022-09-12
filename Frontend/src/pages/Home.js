import React from "react";
import { Link } from "react-router-dom";
// import BannerImage from "../assets/homepage.jpg";

import "../styles/Home.css";

function Home() {
  return (
    <div>
      {/* <Navbar/> */}
      <div
        className="home" /*style={{ backgroundImage: `url(${BannerImage})` }}*/
      >
        <div className="headerContainer">
          <h1>Healthcare System</h1>
          <br></br>
          <p>Improving Lives Together.</p>
          <Link to="/contact">
            <button> BOOK NOW </button>
          </Link>
        </div>
      </div>
      {/* <Footer/> */}
    </div>
  );
}

export default Home;
