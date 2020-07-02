package controller;

import business.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fileupload")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping
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
