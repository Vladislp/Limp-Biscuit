package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.Routine;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RoutineRepository extends Repository<Routine, Long> {
    <S extends Routine> S save(S entity);

    List<Routine> findAll();
}