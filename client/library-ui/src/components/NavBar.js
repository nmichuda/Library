import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";




function NavBar({loggedIn}){

    const navigate = useNavigate();
    const [search, setSearch] = useState("");



    const handleSubmit = (event) =>{
        event.preventDefault();
        //console.log(location.pathname)
        if(search.length > 0){
            navigate(`/books/search/${search}`)
            setSearch(''); 
            
        }
    }

    const handleChange = (event) =>{
        setSearch(event.target.value);
    }



    return(
        <nav className="navbar navbar-light bg-light">
            <Link to={'/'}>Home</Link>
            <Link to={'/books'}>My Books</Link>
            <form className="form-inline" onSubmit={handleSubmit}>
                <input value={search} onChange={handleChange} className="form-control mr-sm-2  searchForm" type="search" placeholder="Search Books" aria-label="Search"/>
                <button className="searchBtn" >Search</button>
            </form>
            <Link to={'/login'}>{loggedIn ? `Log Out` : `Log In`}</Link>
            {loggedIn ? null : <Link to={'/signup'}>Sign Up</Link>}




        </nav>
    )
}


export default NavBar;