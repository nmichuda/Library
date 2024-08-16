import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Home from "./components/Home";
import MyBookList from "./components/MyBookList";
import NavBar from "./components/NavBar";
import SearchBooks from "./components/SearchBooks";

function App() {



  return (



    <Router>
      <NavBar/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/books" element={<MyBookList/>}/>
        <Route path="/books/search/:search" element={<SearchBooks/>}/>


      </Routes>

    </Router>
    
  
  );
}

export default App;
