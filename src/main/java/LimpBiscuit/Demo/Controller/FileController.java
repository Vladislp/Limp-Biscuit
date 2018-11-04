package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entity.DBFile;
import LimpBiscuit.Demo.Service.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private DBFileStorageService dbFileStorageService;

    @GetMapping("/uploadfile")
    public String file() {
        return "File";
    }

    @PostMapping("/uploadfile")
    public String uploadfile(@RequestParam("file") MultipartFile file) {

        dbFileStorageService.storeFile(file);

        return "Files";
    }

    @GetMapping("/downloadfile/{fileId}")
    public ResponseEntity<Resource> downloadfile(@PathVariable int fileId) {
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @GetMapping("/delete/{fileId}")
    public String delete(@PathVariable int fileId) {
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        try {
            dbFile.getId();
            dbFileStorageService.deleteFile(fileId);
        } catch (Exception e) {
            System.out.println("aa");
        }
        return "Files";
    }

    @GetMapping("/files")
    public ModelAndView filess() {
        List<DBFile> files = dbFileStorageService.findFiles();

        ModelAndView modelAndView = new ModelAndView("Files");
        modelAndView.addObject("files", files);

        return modelAndView;
    }
}
