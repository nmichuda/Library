package learn.library.data;

import learn.library.data.mappers.UserBookMapper;
import learn.library.models.UserBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserBookTemplateRepository implements UserBookRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserBookTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UserBook> findByUserId(int userId) {
        final String sql = "select user_id, book_id " +
                "from user_books " +
                "where user_id = ?;";
        return jdbcTemplate.query(sql,new UserBookMapper(), userId);
    }



    @Override
    public UserBook add(UserBook userBook) {
       final String sql = "insert into user_books " +
               "values(?,?) ;";


        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userBook.getUserId());
            ps.setInt(2, userBook.getBookId());
            return ps;
        });

        if (rowsAffected <= 0){
            return null;
        }

        return userBook;
    }
}
