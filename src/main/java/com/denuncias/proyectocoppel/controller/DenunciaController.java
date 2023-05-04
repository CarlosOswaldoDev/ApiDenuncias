package com.denuncias.proyectocoppel.controller;
import com.denuncias.proyectocoppel.entity.Denuncia;
import com.denuncias.proyectocoppel.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//manejar las solicitudes HTTP y las respuestas de una aplicación web.

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    //Obtener Todos
    @GetMapping(value="/denuncia")
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Denuncia> list  = denunciaService.findAll(); //Traer todos los datos
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una sola denuncia por ID
    @GetMapping(value="/denuncia/{idFolio}")
    public ResponseEntity<Object> getById(@PathVariable int idFolio){
        try {
            Denuncia data  = denunciaService.findById(idFolio); //Traer solo un dato
            return new ResponseEntity<Object>(data,HttpStatus.OK);
        }
        catch (Exception e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Crear una denuncia nueva
    // Nota: El argumento @Valid indica que se validará el objeto Denuncia utilizando anotaciones de validación
    // como @NotNull, @Size, etc.
    @PostMapping(value="/denuncia")
    public ResponseEntity<Object> create(@Valid @RequestBody Denuncia denuncia, BindingResult result){
        Map<String, Object> map = new HashMap<>();
        if(result.hasErrors()){
            map.put("message", "La información proporcionada no es válida");
            map.put("errors", result.getAllErrors());
            return new ResponseEntity<>( map, HttpStatus.BAD_REQUEST);
        }
        try {
            Denuncia res = denunciaService.save(denuncia);
            return new ResponseEntity<Object>(res,HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Actualizar una denuncia existente
    @PutMapping("/denuncia/{idFolio}")
    public ResponseEntity<Object> update(@PathVariable int idFolio, @Valid @RequestBody Denuncia denuncia, BindingResult result){
        Map<String, Object> map = new HashMap<>();
        if(result.hasErrors()){
            map.put("errors", result.getAllErrors());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        try {
            Denuncia currentDenuncia = denunciaService.findById(idFolio);
            if(currentDenuncia == null){
                map.put("message", "Denuncia no encontrada con id " + idFolio);
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }

            //Actualiza los campos de la denuncia existente con los valores recibidos en el cuerpo de la petición
            currentDenuncia.setEmpresa  (denuncia.getEmpresa  ());
            currentDenuncia.setPais     (denuncia.getPais     ());
            currentDenuncia.setEstado   (denuncia.getEstado   ());
            currentDenuncia.setNumcentro(denuncia.getNumcentro());
            currentDenuncia.setAnonimo  (denuncia.getAnonimo  ());
            currentDenuncia.setDetalleDenuncia(denuncia.getDetalleDenuncia());
            currentDenuncia.setPassword (denuncia.getPassword ());

            Denuncia res = denunciaService.save(currentDenuncia);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Eliminar con ID
    @DeleteMapping("/denuncia/{idFolio}")
    public ResponseEntity<Object> delete(@PathVariable int idFolio){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Denuncia currentPerson = denunciaService.findById(idFolio);
            denunciaService.delete(currentPerson);
            map.put("deleted", true);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

