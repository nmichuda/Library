package learn.library.data;

import learn.library.models.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public class UserTemplateRepository implements UserRepository{
    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findById(Integer userId) {
        return null;
    }

    @Override
    public AppUser findByEmail(String email) {
        return null;
    }

    @Override
    public AppUser create(AppUser user) {
        return null;
    }

    @Override
    public AppUser update(AppUser user) {
        return null;
    }

    @Override
    public boolean deleteById(Integer userId) {
        return false;
    }
}
