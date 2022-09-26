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
        <div className="headerContainer bg-black">
          <h1>Healthcare System</h1>
          <br></br>
          <p className="bg-danger">Improving Lives Together.</p>

          <Link to="/patient/signin">
            <button className="bg-primary"> SignIn</button>{" "}
          </Link>
          <Link to="/register">
            <button className="bg-primary"> Register</button>{" "}
          </Link>
        </div>
      </div>
      {/* <Footer/> */}
    </div>
  );
}

export default Home;
