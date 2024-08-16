package learn.library.data;

import learn.library.models.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository {


    List<Author> findAll();

    List<Author> findByName(String name);

    Author findById(int authorId);

    @Transactional
    Author add(Author author);

    @Transactional
    boolean deleteById(int authorId);

}
