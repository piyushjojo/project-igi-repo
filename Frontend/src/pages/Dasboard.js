import { Route, Routes, Link, BrowserRouter, Switch } from "react-router-dom";
import PatientLogin from "./PatientLogin";
import Home from "./Home";

import Sidebar from "../Components/Sidebar.js";

function Dashboard() {
  return (
    <div>
      <Sidebar />

      <h1>Welcome Page</h1>
    </div>
  );
}

export default Dashboard;
