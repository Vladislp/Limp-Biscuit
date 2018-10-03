package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.Routine;
import org.springframework.data.repository.Repository;

public interface RoutineRepository extends Repository<Routine, Long> {
    <S extends Routine> S save(S entity);

    Iterable<Routine> findAll();
}