package controller;

import business.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    public ResponseEntity<?> download(@PathVariable String filename){
        Resource file = storageService.downloadFile(filename);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(file.contentLength());
            headers.setContentDispositionFormData("attachment","file.pdf");
            return  new ResponseEntity<>(file,headers,HttpStatus.OK);

        }
        catch (IOException e){
            return new ResponseEntity<>("Bad request.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody Map<String, ArrayList<String>> files){

        ArrayList<String> ejerciciosPath = new ArrayList<>();
        ArrayList<String> solucionarioPath = new ArrayList<>();
        ArrayList<String> soportePath = new ArrayList<>();

        if(files.containsKey("ejercicios") && files.get("ejercicios").size() > 0){
            ejerciciosPath = storageService.uploadFile(files.get("ejercicios").get(0));
        }
        else{
            ejerciciosPath.add("-1");
        }
        if(files.containsKey("solucionario") && files.get("solucionario").size() > 0) {
            solucionarioPath = storageService.uploadFile(files.get("solucionario").get(0));
        }
        else{
            solucionarioPath.add("-1");
        }
        if(files.containsKey("soporte") && files.get("soporte").size() > 0){
            soportePath = storageService.uploadFile(files.get("soporte").get(0));
        }
        else{
            soportePath.add("-1");
        }

        HashMap<String,ArrayList<String>> response = new HashMap<>();

        if(!ejerciciosPath.get(0).equals("-1")) response.put("ejercicios",ejerciciosPath);
        if(!solucionarioPath.get(0).equals("-1")) response.put("solucionario",solucionarioPath);
        if(!soportePath.get(0).equals("-1")) response.put("soporte",soportePath);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
