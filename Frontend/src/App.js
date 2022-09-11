import logo from './logo.svg';
import './App.css';
import {Route,Routes,Link,BrowserRouter, Switch } from 'react-router-dom';
import PatientLogin from './pages/PatientLogin';
import Home from './pages/Home';
// import Navbar from './components/Navbar';
import Navbar from './Components/Navbar';
import Dashboard from './pages/Dasboard';

function App() {
  return (
    <div className="App">
      
      <BrowserRouter>
      <Navbar/>
      <Routes>
        
        <Route exact path='/' element={<Home/>}/>
        <Route exact path="patient/signin"  element={<PatientLogin/>} />
        <Route exact path="dashboard"  element={<Dashboard/>} />

          
          
    
      </Routes>
      </BrowserRouter>
    </div>
  );
}


export default App;
