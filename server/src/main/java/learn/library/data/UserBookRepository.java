package learn.library.data;

import learn.library.models.UserBook;

import java.util.List;

public interface UserBookRepository {

    List<UserBook> findByUserId(int userId);


    UserBook add(UserBook userBook);
}
