package learn.library.data;

import learn.library.models.AppUser;

public interface UserRepository {

    AppUser findByUsername(String username);
    AppUser findById(Integer userId);
    AppUser findByEmail(String email);
    AppUser create(AppUser user);
    AppUser update(AppUser user);
    boolean deleteById(Integer userId);

}
