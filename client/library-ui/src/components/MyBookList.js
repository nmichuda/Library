import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./MyBookList.css";

function MyBookList() {
  const [books, setBooks] = useState([]);
  const [currentUser, setCurrentUser] = useState(
    localStorage.getItem("user_id")
  );
  const url = "http://localhost:8080/books";
  const navigate = useNavigate();

  useEffect(() => {
    fetch(url)
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

  return (
    <>
      <section className="container booksList">
        <h2>My Books</h2>

        {currentUser ? (
          <table className="table table-striped table-hover">
            <thead className="thead-dark">
              <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Status</th>
                <th>&nbsp;</th>
              </tr>
            </thead>
            <tbody>
              {books.map((book) => (
                <tr key={book.bookId}>
                  <td>{book.bookTitle}</td>
                  <td>{book.authorId}</td>
                  <td>to do</td>
                  <td>
                    <img
                      className="listImages"
                      src={book.imageUrl}
                      alt={book.title}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <div className="container loginForm">
            <h2>Log in to view your books</h2>
            <div className="mt-3">
              <p>
                You need to be logged in to see your books.
              </p>
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
