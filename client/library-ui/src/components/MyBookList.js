import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";



function MyBookList(){

    const[books,setBooks] = useState([]);
    const url = 'http://localhost:8080/books'
    const navigate = useNavigate();
    


    useEffect(()=>{
        
        fetch(url)
        .then(response => {
            if(response.status === 200){
                return response.json()
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => setBooks(data))
        .catch(console.log)
    },[])


    return (
        <>
          <section className="container booksList">
            <h2>My Books</h2>
            <ul>
                {books.map((book, index) => (
                    <li key={index} className="bookListElement" onClick={() =>  navigate(`/`)}>
                        <h2 className='allBooksHeader'>{book.bookTitle}</h2>
                    
                        <img className="listImages" src={book.imageUrl ? book.imageUrl : "https://news.mit.edu/sites/default/files/images/202312/MIT_Food-Diabetes-01.jpg"} alt={book.bookTitle} />
                    </li>
                ))}
            </ul>
          </section>
        </>
      )



}



export default MyBookList;