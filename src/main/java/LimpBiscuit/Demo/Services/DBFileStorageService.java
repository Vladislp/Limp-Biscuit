package LimpBiscuit.Demo.Services;

import LimpBiscuit.Demo.Entities.DBFile;
import LimpBiscuit.Demo.FileStorageException;
import LimpBiscuit.Demo.Repositories.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(int fileId) {
        return dbFileRepository.findById(fileId);
//                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public void deleteFile(int fileId){
        dbFileRepository.deleteById(fileId);
    }

    public List<DBFile> findFiles(){
        return dbFileRepository.findAll();
    }
}
