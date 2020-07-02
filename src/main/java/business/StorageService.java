package business;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${upload.path}")
    private String path;

    public String uploadFile(String file){
        InputStream decodedFile = new ByteArrayInputStream(Base64.getDecoder().decode(file));

        try{
            String filename = UUID.randomUUID().toString().concat(".pdf");
            Files.copy(decodedFile, Paths.get(path.concat(filename)), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved.");
            return filename;
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Couldn't save file.");
            return "-1";
        }
    }

    public Resource downloadFile(String filename){
        String fullpath = path.concat(filename);
        Path path = Paths.get(fullpath);
        Resource file = null;
        try {
            file = new UrlResource(path.toUri());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return file;
    }
}
