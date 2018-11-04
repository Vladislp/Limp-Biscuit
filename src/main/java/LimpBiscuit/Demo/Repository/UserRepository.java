package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.Routine;
import LimpBiscuit.Demo.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    <S extends User> S save(S entity);

    Iterable<User> findAll();

    User findByEmail(String email);

    @Query(value = "SELECT user.*, COUNT(*) FROM user JOIN routine ON user.id = routine.user_id WHERE routine.done = 0 GROUP BY user.id", nativeQuery = true)
    List<Routine> findUsersNotDoneRoutines();

    Iterable<User> findAllByEmail(String email);
}
