package com.algorian.prueba.audisoft.controller;

import com.algorian.prueba.audisoft.dto.ProfesorDTO;
import com.algorian.prueba.audisoft.service.IProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final IProfesorService _profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesores(){
        return _profesorService.findAllProfesor();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getByIdProfesor(@PathVariable Long id){
        return _profesorService.findByIdProfesor(id);
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> createProfesor(@RequestBody ProfesorDTO profesorDTO){
        return _profesorService.saveProfesor(profesorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> updateProfesor(@PathVariable Long id, @RequestBody ProfesorDTO profesorDTO){
        return _profesorService.updateProfesor(id, profesorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        return _profesorService.deleteByIdProfesor(id);
    }

}
