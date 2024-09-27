package com.algorian.prueba.audisoft.service;

import com.algorian.prueba.audisoft.dto.ProfesorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProfesorService {

    ResponseEntity<List<ProfesorDTO>> findAllProfesor();

    ResponseEntity<ProfesorDTO> findByIdProfesor(Long id);

    ResponseEntity<ProfesorDTO> saveProfesor(ProfesorDTO profesorDTO);

    ResponseEntity<ProfesorDTO> updateProfesor(Long id, ProfesorDTO profesorDTO);

    ResponseEntity<Void> deleteByIdProfesor(Long id);

}
