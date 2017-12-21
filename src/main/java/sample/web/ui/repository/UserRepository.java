package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
