import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";



function SearchBooks(){
    
    const [books,setBooks] = useState([]);
    const {search} = useParams();
    const navigate = useNavigate();
    const url = 'https://goodreads12.p.rapidapi.com/searchBooks?keyword=';

    useEffect(()=>{
        
        fetch(`${url}${search}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'x-rapidapi-ua': 'RapidAPI-Playground',
                'x-rapidapi-key': '21962f3e6dmshc4f1e77bf20a012p160e93jsn6a9382cfb62c'
                
            }
        })
        .then(response => {
            if(response.status === 200){
                return response.json()
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => setBooks(data))
        .catch(console.log)
    },[search])





    return(
        <>
        <section className="container booksList">
          <h2>Search Results</h2>
          <ul>
              {books.map((book, index) => (
                  <li key={index} className="bookListElement" onClick={() =>  navigate(`/`)}>
                      <h2 className='allBooksHeader'>{book.title}</h2>
                  
                      <img className="listImages" src={book.imageUrl ? book.imageUrl : "https://news.mit.edu/sites/default/files/images/202312/MIT_Food-Diabetes-01.jpg"} alt={book.title} />
                  </li>
              ))}
          </ul>
        </section>
      </>
    )
}

export default SearchBooks;