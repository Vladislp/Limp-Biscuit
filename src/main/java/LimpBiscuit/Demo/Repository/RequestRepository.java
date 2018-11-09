package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    @Query(value = "SELECT name, COUNT(name) as count FROM request JOIN request_operating_system system ON request.operating_system_id = system.id GROUP BY name ORDER BY count DESC;", nativeQuery = true)
    List<Object> getOperatingSystemStats();
}
