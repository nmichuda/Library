package learn.library.domain;


import learn.library.data.AuthorRepository;
import learn.library.models.Author;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author findById(int authorId){
        return repository.findById(authorId);

    }

    public List<Author> findAll(){
        return repository.findAll();
    }

    public List<Author> findByName(String name){
        return repository.findByName(name);
    }

    public Result<Author> add(Author author){
        Result<Author> result = new Result<>();
        if(author == null){
            result.addMessage("Author cannot be null", ResultType.INVALID);
            return result;
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Author> violation : violations) {
                result.addMessage(violation.getMessage(),ResultType.INVALID);
            }
            return result;
        }

        if(author.getAuthorId() != 0){
            result.addMessage("recipeId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }


        return result;
    }

    public boolean deleteById(int authorId){
        return repository.deleteById(authorId);
    }



}
