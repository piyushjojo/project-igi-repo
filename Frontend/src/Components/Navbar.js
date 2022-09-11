import React, { useState } from "react";
import Logo from "../assets/trucklogo.png";
import { Link } from "react-router-dom";

import "../styles/Navbar.css";

function Navbar() {
  const [openLinks, setOpenLinks] = useState(false);

  const toggleNavbar = () => {
    setOpenLinks(!openLinks);
  };
  return (
    <div className="navbar">
      <div className="leftSide" id={openLinks ? "open" : "close"}>
        <img src={Logo} />
        <div className="hiddenLinks">
          <Link to="/"> Home </Link>
          <Link to="/about"> About </Link>
          <Link to="/menu"> Services </Link>
          <Link to="/patient/signin"> SignIn </Link>
          <Link to="/register"> Register </Link>
          <Link to="/custLogin">custLogin</Link>
        </div>
      </div>
      <div className="rightSide">
        <Link to="/"> Home </Link>
        <Link to="/about"> About </Link>
        <Link to="/menu"> Services </Link>
        <Link to="/patient/signin"> SignIn </Link>
        <Link to="/register"> Register </Link>
        <button onClick={toggleNavbar}>
          Reorder
        </button>
      </div>
    </div>
  );
}

export default Navbar;