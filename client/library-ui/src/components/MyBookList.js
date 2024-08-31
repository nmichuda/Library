import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import "./MyBookList.css";
import { DropdownButton, DropdownItem } from "react-bootstrap";


function MyBookList() {             //to do: consolodate sort functions into single function
  const [books, setBooks] = useState([]);
  const [updating, setUpdating] = useState(false);
  const [errors, setErrors] = useState([]);
  const [titleSorted, setTitleSorted] = useState(false);
  const [authorSorted, setAuthorSorted] = useState(false);
  const [dateSorted, setDateSorted] = useState(false);
  const [statusSorted, setStatusSorted] = useState(false);
  const [currentUser, setCurrentUser] = useState(
    localStorage.getItem("user_id")
  );
  const url = "http://localhost:8080/books";
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`${url}/${localStorage.getItem("user_id")}`)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then((data) => {
        console.log(data);
        setBooks(data);
      })
      .catch(console.log);
  }, []);

  const sortByTitle = () => {
    let tempBooks = [...books];
    if (titleSorted) {
      tempBooks.sort((a, b) => {
        if (a.bookTitle > b.bookTitle) {
          return -1;
        }
        if (a.bookTitle < b.bookTitle) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setTitleSorted(false);
    } else {
      tempBooks.sort((a, b) => {
        if (a.bookTitle < b.bookTitle) {
          return -1;
        }
        if (a.bookTitle > b.bookTitle) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setTitleSorted(true);
    }
    
  };

  const sortByAuthor = () => {
    let tempBooks = [...books];
    if (authorSorted) {
      tempBooks.sort((a, b) => {
        if (a.author > b.author) {
          return -1;
        }
        if (a.author < b.author) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setAuthorSorted(false);
    } else {
      tempBooks.sort((a, b) => {
        if (a.author < b.author) {
          return -1;
        }
        if (a.author > b.author) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setAuthorSorted(true);
    }
    
  };


  const sortByDate = () => {
    console.log("working")
    let tempBooks = [...books];
    if (dateSorted) {
      tempBooks.sort((a, b) => {
        if (a.timeAdded > b.timeAdded ) {
          return -1;
        }
        if (a.timeAdded  < b.timeAdded ) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setDateSorted(false);
    } else {
      tempBooks.sort((a, b) => {
        if (a.timeAdded < b.timeAdded) {
          return -1;
        }
        if (a.timeAdded > b.timeAdded) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setDateSorted(true);
    }
    
  };

  const sortByStatus = () => {
    
    let tempBooks = [...books];
    if (statusSorted) {
      tempBooks.sort((a, b) => {
        if (a.status > b.status ) {
          return -1;
        }
        if (a.status < b.status  ) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setStatusSorted(false);
    } else {
      tempBooks.sort((a, b) => {
        if (a.status < b.status ) {
          return -1;
        }
        if (a.status > b.status) {
          return 1;
        }
        return 0;
      });
      setBooks(tempBooks);
      setStatusSorted(true);
    }
    
  };

  const handleRead = (selected, input) => {
    console.log(selected);
    setUpdating(true);
    let updatedBook = selected;
    updatedBook.status = input;
    const init = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedBook)
    };
    fetch(`${url}/${selected.bookId}`,init)
    .then(response => {
        if(response.status === 204){
            return null
        } else if(response.status === 400){
            return response.json()
        } else {
            return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
    })
    .then(data => {
        if(data){
            setErrors(data);
        } else {
            setUpdating(false);
        }
    })
    .catch(console.log)
  }

  const handleDelete = (book) => {
    if (
        window.confirm(
          `Delete ${book.bookTitle} from your list?`
        )
      ) {
        const init = {
          method: "DELETE",
        };
        fetch(`${url}/${book.bookId}`, init)
          .then((response) => {
            if (response.status === 204) {
              const newBooks = books.filter(
                (b) => b.bookId !== book.bookId
              );
  
              setBooks(newBooks);
            } else {
              return Promise.reject(`Unexpected Status Code: ${response.status}`);
            }
          })
          .catch(console.log);
      }
    };
  

  return (
    <>
      <section className="container booksList">
        <h2>My Books</h2>

        {currentUser ? (
          <table className="table table-striped table-hover">
            <thead className="thead-dark">
              <tr>
                <th onClick={sortByTitle}>Title</th>
                <th onClick={sortByAuthor}>Author</th>
                <th onClick={sortByStatus}>Status</th>
                <th>Description</th>
                <th onClick={sortByDate}>Date Added</th>
                <th>Cover</th>
                <th>&nbsp;</th>
              </tr>
            </thead>
            <tbody>
              {books.map((book, index) => (
                <tr key={index}>
                  <td>{book.bookTitle}</td>
                  <td>{book.author}</td>
                  <td>{book.status}</td>
                  <td>
                    {book.description.length > 260
                      ? book.description.slice(0, 260) + "..."
                      : book.description}
                  </td>
                  <td>{book.timeAdded}</td>
                  <td>
                    <img
                      className="listImages"
                      src={book.imageUrl}
                      alt={book.title}
                    />
                  </td>
                  <td>
                    <div>
                  
                  <button className="btn btn-outline-danger" onClick={()=>handleDelete(book)}>Delete</button>
                  <DropdownButton onChange={(event)=>console.log(event.target.value)} id="dropdown-basic-button" title="Change Status">
                    <DropdownItem  onClick={()=>handleRead(book,"Read")}>Read</DropdownItem>
                    <DropdownItem  onClick={()=>handleRead(book,"Not Started")}>Not Started</DropdownItem>
                    <DropdownItem  onClick={()=>handleRead(book,"Reading")}>Reading</DropdownItem>
                  </DropdownButton>
                  </div>
                  </td>
                </tr>
                
              ))}
            </tbody>
          </table>
        ) : (
          <div className="container loginForm">
            <h2>Log in to view your books</h2>
            <div className="mt-3">
              <p>You need to be logged in to see your books.</p>
              <button
                className="btn btn-outline-info loginBtn"
                onClick={() => navigate("/login")}
              >
                Log In
              </button>
            </div>
          </div>
        )}
      </section>
    </>
  );
}

export default MyBookList;
