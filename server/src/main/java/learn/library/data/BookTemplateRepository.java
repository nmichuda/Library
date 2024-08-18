package learn.library.data;

import learn.library.data.mappers.BookMapper;
import learn.library.models.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.List;


@Repository
public class BookTemplateRepository implements BookRepository{

    private final JdbcTemplate jdbcTemplate;

    public BookTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Book> findAll() {
        final String sql = "select * "
                + "from books limit 1000";
        return jdbcTemplate.query(sql,new BookMapper());
    }

    @Override
    public List<Book> findByAuthor(int authorId) {
        final String sql = "select * "
                + "from books "
                + "where author_id = ?;";
        return jdbcTemplate.query(sql,new BookMapper(),authorId);
    }

    @Override
    public List<Book> findByUser(int userId) {
        final String sql = "select * "
                + "from books "
                + "where user_id = ?;";
        return jdbcTemplate.query(sql,new BookMapper(),userId);
    }

    @Override
    public List<Book> findByTitle(String title) {
        final String sql = "select * "
                + "from books "
                + "where book_title LIKE ?;";
        String queryText = "%" + title + "%";
        return jdbcTemplate.query(sql,new BookMapper(),queryText);
    }

    @Override
    public List<Book> findByTag(int tagId) {
        return List.of();
    }

    @Override
    public Book findById(int bookId) {
        final String sql = "select * "
                + "from books "
                + "where book_id = ?;";
        return jdbcTemplate.query(sql,new BookMapper(),bookId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book findByISBN(String isbn) {
        final String sql = "select * "
                + "from books "
                + "where book_isbn = ?;";
        return jdbcTemplate.query(sql,new BookMapper(),isbn).stream().findFirst().orElse(null);
    }

    @Override
    public Book add(Book book) {

        final String sql = "insert into books (book_title, book_isbn, author_id, image_link, book_description) "
                + "values(?,?,?,?,?) ;";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,book.getBookTitle());
            ps.setString(2,book.getIsbn());
            ps.setInt(3,book.getAuthorId());
            ps.setString(4,book.getImageUrl());
            ps.setString(5,book.getDescription());

            return ps;
        }, keyHolder);
        if (rowsAffected <= 0){
            return null;
        }

        book.setBookId(keyHolder.getKey().intValue());

        return book;
    }

    @Override
    public boolean update(Book book) {

        final String sql = "update books set "
                + "book_title = ?, "
                + "book_isbn = ?, "
                + "author_id = ?, "
                + "book_description = ?, "
                + "image_link = ?, ;";

        boolean updated = jdbcTemplate.update(sql,
                book.getBookTitle(),
                book.getIsbn(),
                book.getAuthorId(),
                book.getDescription(),
                book.getImageUrl()

        ) > 0;


        return updated;
    }

    @Override
    public boolean delete(int bookId) {
        return jdbcTemplate.update("delete from books where book_id = ?;",bookId) > 0;
    }
}
