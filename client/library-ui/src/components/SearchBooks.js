import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./SearchBooks.css";


const BOOK_DEFAULT = {
    bookTitle: '',
    authorId: 1,
    imageUrl: '',
    isbn: ''
}



function SearchBooks() {
  const [books, setBooks] = useState([]);
  const [currentUser, setCurrentUser] = useState(
    localStorage.getItem("user_id")
  );
  const [book,setBook] = useState(BOOK_DEFAULT);
  const { search } = useParams();
  const navigate = useNavigate();
  const url = "https://book-finder1.p.rapidapi.com/api/search?title=";
  const addUrl = 'http://localhost:8080/books'


    const handleAdd = (selected) => {

        const updatedBook = {...book};
        updatedBook.bookTitle = selected.title;
        updatedBook.imageUrl = `https://covers.openlibrary.org/b/isbn/${selected.canonical_isbn}-M.jpg`;
        updatedBook.isbn = selected.canonical_isbn;
        updatedBook.description = selected.summary;
        updatedBook.userId = currentUser;
        updatedBook.author = selected.authors[0];
        updatedBook.status = "Not Started";
        

        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedBook)
        };

        fetch(addUrl,init)
        .then(response => {
            if (response.status === 201 || response.status === 400) {
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            if (data.bookId) {
                setBook(BOOK_DEFAULT);
                //navigate('/')
            } else {
                console.log(data);
            }
        })
        .catch(console.log);

    }


    







  useEffect(() => {
    const searchFormat = search.replace(" ", "+");
    //   console.log(search);
    if (search) {
      fetch(`${url}${searchFormat}&results_per_page=25&page=1`, {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          "x-rapidapi-ua": "RapidAPI-Playground",
          "x-rapidapi-host": "book-finder1.p.rapidapi.com",
          "x-rapidapi-key": `${process.env.REACT_APP_RAPID_KEY}`,
        },
      })
        .then((response) => {
          if (response.status === 200) {
            return response.json();
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .then((data) => {
          console.log(data);
          setBooks(data.results);
        })
        .catch(console.log);
    }
  }, [search]);

  return (
    <>
      <section className="container booksList">
        <h2>Search Results</h2>
        <ul>
          {books.length > 0
            ? books.map((book, index) => (
                <li
                  key={index}
                  className="bookListElement"
                  
                >
                  <div className="bookContent">
                    <h2 className="allBooksHeader" onClick={() => navigate(`/`)}>{book.title}</h2>
                    <h3 className="bookAuthor">{book.authors}</h3>
                    <p className="bookDescription">{book.summary}</p>
                    <div className="buttonHolder">
                    {currentUser ? (<button className="bookAddButton" onClick={() => handleAdd(book)}>Add Book to List</button>) : ""}
                    </div>
                  </div>
                  <img
                    className="listImages"
                    src={`https://covers.openlibrary.org/b/isbn/${book.canonical_isbn}-L.jpg`}
                    alt={book.title}
                  />
                  
                </li>
              ))
            : ""}
        </ul>
      </section>
    </>
  );
}

export default SearchBooks;
