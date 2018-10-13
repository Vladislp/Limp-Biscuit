package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.Routine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends CrudRepository<Routine, Long> {
    <S extends Routine> S save(S entity);

    List<Routine> findAll();

    //Näitab mitu rutiini on tegemata
    @Query(value = "SELECT COUNT(*) FROM  routine WHERE routine.done = 0", nativeQuery = true)
    List<Routine> countimine();
}