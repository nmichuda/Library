package learn.library.domain;


import learn.library.data.BookRepository;
import learn.library.models.Book;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository repository;


    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll(){
        return repository.findAll();
    }

    public List<Book> findByAuthor(int authorId){
        return repository.findByAuthor(authorId);
    }

    public List<Book> findByTitle(String title){
        return repository.findByTitle(title);
    }

    public List<Book> findByUserId(int userId){
        return repository.findByUser(userId);
    }

    public Book findById(int bookId){
        return repository.findById(bookId);
    }

    public Result<Book> add(Book book){
        Result<Book> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Book> violation : violations) {
                result.addMessage(violation.getMessage(),ResultType.INVALID);
            }
            return result;
        }

        if(book.getBookId() != 0){
            result.addMessage("bookID cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        if(repository.findByISBN(book.getIsbn())!= null && repository.findByISBN(book.getIsbn()).getUserId() == book.getUserId()){
            result.addMessage("book already in list!", ResultType.INVALID);
            return result;
        }


        book = repository.add(book);
        result.setPayload(book);
        return result;
    }


    public Result<Book> update(Book book){
        Result<Book> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Book> violation : violations) {
                result.addMessage(violation.getMessage(),ResultType.INVALID);
            }
            return result;
        }

        if(book.getBookId() <= 0){
            result.addMessage("bookID must be valid to update", ResultType.INVALID);
            return result;
        }

        if(!repository.update(book)){
            String msg = String.format("recipeId: %s, not found", book.getBookId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int bookId) {
        return repository.delete(bookId);
    }
}
