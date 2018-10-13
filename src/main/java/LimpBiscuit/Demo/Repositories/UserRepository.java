package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Entities.User;
import org.hibernate.hql.internal.antlr.SqlTokenTypes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    <S extends User> S save(S entity);

    Iterable<User> findAll();

    User findByEmail(String email);

    //JOIN Näita iga kasutaja emaili ja rutiinide descriptioni ja kuupäeva
    @Query(value = "SELECT user.id, user.email,routine.text, routine.date FROM user JOIN routine ON user.id = routine.user_id", nativeQuery = true)
    List<Object> join();

//    Iterable<User> findAllByEmail(String email);
}
