package com.algorian.prueba.audisoft.service;

import com.algorian.prueba.audisoft.dto.EstudianteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEstudianteService {

    ResponseEntity<List<EstudianteDTO>> findAllEstudiante();

    ResponseEntity<EstudianteDTO> findByIdEstudiante(Long id);

    ResponseEntity<EstudianteDTO> saveEstudiante(EstudianteDTO estudianteDTO);

    ResponseEntity<EstudianteDTO> updateEstudiante(Long id, EstudianteDTO estudianteDTO);

    ResponseEntity<Void> deleteByIdEstudiante(Long id);

}
