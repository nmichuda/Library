package learn.library.models;

public class UserBook {


    private int userId;
    private int bookId;


    public UserBook(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public UserBook() {
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }




}
