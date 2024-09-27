package com.algorian.prueba.audisoft.controller;

import com.algorian.prueba.audisoft.dto.EstudianteDTO;
import com.algorian.prueba.audisoft.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService _estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        return _estudianteService.findAllEstudiante();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id){
        return _estudianteService.findByIdEstudiante(id);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> createEstudiante(@RequestBody EstudianteDTO estudianteDTO){
        return _estudianteService.saveEstudiante(estudianteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO){
        return _estudianteService.updateEstudiante(id, estudianteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id){
        return _estudianteService.deleteByIdEstudiante(id);
    }

}
