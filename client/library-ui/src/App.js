import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Home from "./components/Home";
import MyBookList from "./components/MyBookList";
import NavBar from "./components/NavBar";
import SearchBooks from "./components/SearchBooks";
import { useEffect, useState } from "react";
import SignUp from "./components/SignUp";
import Login from "./components/Login";

function App() {
  const [loggedIn,setLoggedIn] = useState(false);
  const [user, setUser] = useState(null);
  const [token,setToken] = useState('');

  useEffect(() => {
    if(localStorage.getItem('token')) {
      setLoggedIn(true);
      setUser(localStorage.getItem('user_id'));
    }
  }, []);
 


  return (


    
    <Router>
      
      <NavBar loggedIn={loggedIn}/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/books" element={<MyBookList/>}/>
        <Route path="/books/search/:search" element={<SearchBooks/>}/>
        <Route path="/login" element={<Login setLoggedIn={setLoggedIn} loggedIn={loggedIn} setUser={setUser} setToken={setToken}/>}/>
        <Route path="/signup" element={<SignUp/>}/>


      </Routes>

    </Router>
    
  
  );
}

export default App;
