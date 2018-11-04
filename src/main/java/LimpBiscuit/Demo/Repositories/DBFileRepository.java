package LimpBiscuit.Demo.Repositories;

import LimpBiscuit.Demo.Entities.DBFile;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DBFileRepository extends Repository<DBFile, String> {
    <S extends DBFile> S save(S entity);

    List<DBFile> findAll();

    DBFile findById(int id);

    void deleteById(int id);
}