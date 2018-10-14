package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.Statistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Long> {
}
