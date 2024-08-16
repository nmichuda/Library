package learn.library.data.mappers;

import learn.library.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setBookTitle(resultSet.getString("book_title"));
        book.setIsbn(resultSet.getString("book_isbn"));
        book.setAuthorId(resultSet.getInt("author_id"));
        book.setImageUrl(resultSet.getString("image_link"));
        return book;
    }
}
