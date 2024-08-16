package learn.library.data.mappers;

import learn.library.models.UserBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBookMapper implements RowMapper<UserBook> {


    @Override
    public UserBook mapRow(ResultSet resultSet, int i) throws SQLException {
        UserBook userBook = new UserBook();
        userBook.setBookId(resultSet.getInt("book_id"));
        userBook.setUserId(resultSet.getInt("user_id"));
        return userBook;
    }
}


