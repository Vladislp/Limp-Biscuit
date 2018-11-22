package LimpBiscuit.Demo.Repository;

import LimpBiscuit.Demo.Entity.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    interface StringAndNumber {
        String getString();

        int getNumber();
    }

    @Query(value = "SELECT\n" +
            "    os.name AS string,\n" +
            "    COUNT(*) AS number\n" +
            "FROM\n" +
            "    request\n" +
            "JOIN\n" +
            "    request_operating_system os\n" +
            "ON\n" +
            "    request.operating_system_id = os.id\n" +
            "GROUP BY\n" +
            "    os.id\n" +
            "ORDER BY\n" +
            "    number DESC", nativeQuery = true)
    List<StringAndNumber> getOperatingSystemStats();

    @Query(value = "SELECT\n" +
            "    browser.name AS string,\n" +
            "    COUNT(*) AS number\n" +
            "FROM\n" +
            "    request\n" +
            "JOIN\n" +
            "    request_browser browser \n" +
            "ON\n" +
            "    request.browser_id = browser.id\n" +
            "GROUP BY\n" +
            "    browser.id\n" +
            "ORDER BY\n" +
            "    number DESC", nativeQuery = true)
    List<StringAndNumber> getBrowserStats();

    @Query(value = "SELECT\n" +
            "    DATE_FORMAT(date, '%Y-%m-%dT%H') AS string,\n" +
            "    count(*) AS number\n" +
            "FROM\n" +
            "   request\n" +
            "GROUP BY\n" +
            "   string\n" +
            "ORDER BY" +
            "   string ASC", nativeQuery = true)
    List<StringAndNumber> getDates();
}
