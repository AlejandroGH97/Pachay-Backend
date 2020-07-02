package controller;

import business.StorageService;
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
            return ResponseEntity.badRequest()
                    .body("Invalid request.");
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody Map<String,List<String>> files){

        ArrayList<String> ejerciciosPath = new ArrayList<>();
        ArrayList<String> solucionarioPath = new ArrayList<>();
        ArrayList<String> soportePath = new ArrayList<>();

        if(files.containsKey("ejercicios")) ejerciciosPath = storageService.uploadFile(files.get("ejercicios").get(0));
        if(files.containsKey("solucionario"))  solucionarioPath = storageService.uploadFile(files.get("solucionario").get(0));
        if(files.containsKey("soporte"))  soportePath = storageService.uploadFile(files.get("soporte").get(0));

        HashMap<String,List<String>> response = new HashMap<>();

        if(!ejerciciosPath.get(0).equals("-1")) response.put("ejercicios",ejerciciosPath);
        if(!solucionarioPath.get(0).equals("-1")) response.put("solucionario",solucionarioPath);
        if(!soportePath.get(0).equals("-1")) response.put("soporte",soportePath);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
