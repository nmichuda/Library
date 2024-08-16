package learn.library.data;

import learn.library.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookTemplateRepositoryTest {

    @Autowired
    BookRepository repository;
    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}


    @Test
    void findAll() {

        List<Book> books = repository.findAll();
        assertNotNull(books);
        assertEquals(books.size(),2);

    }

    @Test
    void findByAuthor() {
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findByTag() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByISBN() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}