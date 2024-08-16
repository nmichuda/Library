package learn.library.data.mappers;

import learn.library.models.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author  = new Author();
        author.setAuthorId(resultSet.getInt("author_id"));
        author.setFirstName(resultSet.getString("author_firstName"));
        author.setLastName(resultSet.getString("author_lastName"));
        return author;
    }
}

