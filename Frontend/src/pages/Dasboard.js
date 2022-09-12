import axios from "axios";
import { toast, ToastContainer } from "react-toastify";

function Dashboard() {
  // const [email, setEmail] = useState("");
  // const [password, setPassword] = useState("");

  const handleClick = () => {
    //   const login = { email, password };

    axios.post(`http://localhost:8080/patient/signout`).then(
      (response) => {
        //   console.log(email);
        //   console.log(password);
        // toast.success('login successfull');
        console.log("success");
        console.log(response);
        window.location.href = "/patient/signin";
        //localStorage.setItem("c_id", response.data.c_id);
      },
      (error) => {
        //alert("Invalid Login Details", error);
        toast.error("invalid login");
        console.log(error);
        console.log("Error");
      }
    );
  };

  return (
    <div>
      <h1>Welcome Page</h1>
      <button className="btn" onClick={handleClick}>
        Log Out
      </button>
    </div>
  );
}

export default Dashboard;
