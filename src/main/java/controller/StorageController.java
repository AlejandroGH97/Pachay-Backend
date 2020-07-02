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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<?> upload(@RequestBody Map<String,List<String>> files){

        String ejerciciosPath = "-1";
        String solucionarioPath = "-1";
        String soportePath = "-1";

        if(files.containsKey("ejercicios")) ejerciciosPath = storageService.uploadFile(files.get("ejercicios").get(0));
        if(files.containsKey("solucionario"))  solucionarioPath = storageService.uploadFile(files.get("solucionario").get(0));
        if(files.containsKey("soporte"))  soportePath = storageService.uploadFile(files.get("soporte").get(0));

        HashMap<String,String> response = new HashMap<>();

        if(!ejerciciosPath.equals("-1")) response.put("ejercicios",ejerciciosPath);
        if(!solucionarioPath.equals("-1")) response.put("solucionario",solucionarioPath);
        if(!soportePath.equals("-1")) response.put("soporte",soportePath);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
