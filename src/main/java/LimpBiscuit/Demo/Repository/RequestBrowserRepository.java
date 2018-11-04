package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.RequestBrowser;
import org.springframework.data.repository.CrudRepository;

public interface RequestBrowserRepository extends CrudRepository<RequestBrowser, Long> {
    <S extends RequestBrowserRepository> S save(S entity);

    RequestBrowser findByNameAndVersion(String name, String version);
}
