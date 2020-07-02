package controller;

import business.StorageService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/download/{filename}")
    public ResponseEntity download(@PathVariable String filename){
        Resource file = storageService.downloadFile(filename);
        try {
            return ResponseEntity.ok()
                    .contentLength(file.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(file);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest()
                .body("Invalid request.");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody Map<String,Object> files){
        String ejerciciosPath = "-1";
        String solucionarioPath = "-1";
        String soportePath = "-1";
        if(files.containsKey("ejercicios")) ejerciciosPath = storageService.uploadFile((String)files.get("ejercicios"));
        if(files.containsKey("solucionario"))  solucionarioPath = storageService.uploadFile((String)files.get("solucionario"));
        if(files.containsKey("soporte"))  soportePath = storageService.uploadFile((String)files.get("soporte"));
        HashMap<String,String> response = new HashMap<>();
        if(!ejerciciosPath.equals("-1")) response.put("ejercicios",ejerciciosPath);
        if(!solucionarioPath.equals("-1")) response.put("solucionario",solucionarioPath);
        if(!soportePath.equals("-1")) response.put("soporte",soportePath);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
