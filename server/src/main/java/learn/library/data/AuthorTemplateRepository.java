package learn.library.data;

import learn.library.data.mappers.AuthorMapper;
import learn.library.models.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class AuthorTemplateRepository implements AuthorRepository{
    private final JdbcTemplate jdbcTemplate;

    public AuthorTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Author> findAll() {
        final String sql = "select * "
                + "from authors limit 1000";
        return jdbcTemplate.query(sql,new AuthorMapper());
    }

    @Override
    public List<Author> findByName(String name) {
        final String sql = "select * "
                + "from authors "
                + "where author_lastName LIKE ?;";
        String queryText = "%" + name + "%";
        return jdbcTemplate.query(sql,new AuthorMapper(),name);
    }

    @Override
    public Author findById(int authorId) {
        return null;
    }

    @Override
    public Author add(Author author) {

        final String sql =  "insert into authors (author_firstName, author_lastName) "
                + "values(?,?) ;";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,author.getFirstName());
            ps.setString(2,author.getLastName());

            return ps;
        }, keyHolder);
        if (rowsAffected <= 0){
            return null;
        }

        author.setAuthorId(keyHolder.getKey().intValue());

        return author;

    }

    @Override
    public boolean deleteById(int authorId) {
        return jdbcTemplate.update("delete from authors where author_id = ?;",authorId) > 0;
    }
}
