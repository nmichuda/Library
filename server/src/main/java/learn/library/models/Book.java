package learn.library.models;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Book {


    private int bookId;

    @NotBlank(message = "title cannot be empty")
    private String bookTitle;

    private String isbn;

    private String description;

    private int authorId;

    private String imageUrl;

    private int userId;

    private String author;

    private LocalDate timeAdded;

    private String status;



    public Book(){

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDate timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book(int authorId, int bookId, String bookTitle, String imageUrl, String isbn, String description, int userId, String author, LocalDate timeAdded, String status) {
        this.authorId = authorId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.imageUrl = imageUrl;
        this.isbn = isbn;
        this.description = description;
        this.userId = userId;
        this.author = author;
        this.timeAdded = timeAdded;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && authorId == book.authorId && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(isbn, book.isbn) && Objects.equals(imageUrl, book.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookTitle, isbn, authorId, imageUrl);
    }
}
