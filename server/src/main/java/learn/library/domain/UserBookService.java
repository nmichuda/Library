package learn.library.domain;


import learn.library.data.UserBookRepository;
import learn.library.models.UserBook;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookService {


    private final UserBookRepository repository;

    public UserBookService(UserBookRepository repository) {
        this.repository = repository;
    }


    public List<UserBook> findByUserId(int userId){
        if(userId < 0){
            return null;
        }
        return repository.findByUserId(userId);
    }

    public Result<UserBook> add(UserBook userBook){
        Result<UserBook> result = new Result<>();
        if(userBook == null){
            result.addMessage("UserBook cannot be null", ResultType.INVALID);
            return result;
        }
        if(userBook.getUserId() <= 0 || userBook.getBookId() <= 0){
            result.addMessage("Book and user ID must be valid", ResultType.INVALID);
            return result;
        }
        result.setPayload(repository.add(userBook));
        return result;
    }
}
