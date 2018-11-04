package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.RequestOperatingSystem;
import org.springframework.data.repository.CrudRepository;

public interface RequestOperatingSystemRepository extends CrudRepository<RequestOperatingSystem, Long> {
    <S extends RequestOperatingSystemRepository> S save(S entity);

    RequestOperatingSystem findByNameAndAndFamily(String name, String family);
}
