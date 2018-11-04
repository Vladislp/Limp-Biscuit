package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
