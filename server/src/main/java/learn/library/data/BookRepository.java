package learn.library.data;

import learn.library.models.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository {

    @Transactional
    List<Book> findAll();

    @Transactional
    List<Book> findByAuthor(int authorId);

    @Transactional
    List<Book> findByUser(int userId);

    @Transactional
    List<Book> findByTitle(String title);

    @Transactional
    List<Book> findByTag(int tagId);

    @Transactional
    Book findById(int bookId);

    @Transactional
    Book findByISBN(String isbn);

    @Transactional
    Book add(Book book);

    @Transactional
    boolean update(Book book);


    @Transactional
    boolean delete(int bookId);


}
