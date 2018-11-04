package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.Routine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends CrudRepository<Routine, Long> {
    <S extends Routine> S save(S entity);

    List<Routine> findAll();

    List<Routine> findByEmail(String email);
}