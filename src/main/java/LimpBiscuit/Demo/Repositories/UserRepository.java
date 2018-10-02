package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    <S extends User> S save(S entity);

    Iterable<User> findAll();
    User findByEmail(String email);
//    Iterable<User> findAllByEmail(String email);
}
