import axios from "axios";
import React, { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import {
  Button,
  Card,
  CardText,
  CardTitle,
  Col,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";

import "../styles/Contact.css";

function PatientLogin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleClick = () => {
    const login = { email, password };

    axios.post(`http://localhost:8080/patient/signin`, login).then(
      (response) => {
        console.log(email);
        console.log(password);
        // toast.success('login successfull');
        console.log("success");
        console.log(response);
        window.location.href = "/dashboard";
        localStorage.setItem("c_id", response.data.c_id);
      },
      (error) => {
        alert("Invalid Login Details", error);
        toast.error("invalid login");
        console.log(error);
        console.log("Error");
      }
    );
  };

  return (
    <div className="col-5 container-fluid border border-3">
      {/* </Navbar> */}
      <div className="contact">
        <div className="rightSide">
          <h1> Patient SignIn</h1>

          <Form inline>
            <FormGroup className="mb-2 me-sm-2 mb-sm-0">
              <Input
                id="email"
                name="email"
                placeholder="Enter Email"
                type="text"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </FormGroup>
            <FormGroup className="my-4 me-sm-2 mb-sm-0">
              <Input
                id="password"
                name="password"
                placeholder="Enter Password"
                type="text"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </FormGroup>
            <div class="text-center">
              <Button
                disabled={email && password ? false : true}
                title="Button"
                onPress={() => {
                  console.log("sdasds");
                }}
                onClick={handleClick}
              >
                Submit
              </Button>
            </div>
          </Form>
        </div>
      </div>
      {/* <Footer/> */}
    </div>
  );
}

export default PatientLogin;
