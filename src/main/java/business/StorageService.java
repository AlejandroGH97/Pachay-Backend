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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${upload.path}")
    private String path;

    public ArrayList<String> uploadFile(String file){
        InputStream decodedFile = new ByteArrayInputStream(Base64.getDecoder().decode(file));
        ArrayList<String> response = new ArrayList<>();
        try{
            String filename = UUID.randomUUID().toString().concat(".pdf");
            Files.copy(decodedFile, Paths.get(path.concat(filename)), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved.");
            response.add(filename);
            return response;
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Couldn't save file.");
            response.add("-1");
            return response;
        }
    }

    public Resource downloadFile(String filename){
        String fullpath = path.concat(filename);
        Resource file = null;

        try {
            Path path = Paths.get(fullpath);
            file = new UrlResource(path.toUri());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }

        return file;
    }
}
