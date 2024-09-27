package com.algorian.prueba.audisoft.controller;

import com.algorian.prueba.audisoft.dto.NotaDTO;
import com.algorian.prueba.audisoft.service.INotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final INotaService _notaService;

    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNBotas(){
        return _notaService.findAllNota();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getByIdNota(@PathVariable Long id){
        return _notaService.findByIdNota(id);
    }

    @PostMapping
    public ResponseEntity<NotaDTO> createNota(@RequestBody NotaDTO notaDTO){
        return _notaService.saveNota(notaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO){
        return _notaService.updateNota(id, notaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdNota(@PathVariable Long id){
        return _notaService.deleteByIdNota(id);
    }

}
