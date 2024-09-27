package com.algorian.prueba.audisoft.service;

import com.algorian.prueba.audisoft.dto.NotaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotaService {

    ResponseEntity<List<NotaDTO>> findAllNota();

    ResponseEntity<NotaDTO> findByIdNota(Long id);

    ResponseEntity<NotaDTO> saveNota(NotaDTO notaDTO);

    ResponseEntity<NotaDTO> updateNota(Long id, NotaDTO notaDTO);

    ResponseEntity<Void> deleteByIdNota(Long id);

}
