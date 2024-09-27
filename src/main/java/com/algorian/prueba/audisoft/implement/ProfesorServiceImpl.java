package com.algorian.prueba.audisoft.implement;

import com.algorian.prueba.audisoft.dto.ProfesorDTO;
import com.algorian.prueba.audisoft.entity.Profesor;
import com.algorian.prueba.audisoft.repository.INotaRepository;
import com.algorian.prueba.audisoft.repository.IProfesorRepository;
import com.algorian.prueba.audisoft.service.IProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements IProfesorService {

    private final IProfesorRepository _profesorRepository;
    private final INotaRepository _notaRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ProfesorDTO>> findAllProfesor() {
        List<Profesor> profesores = _profesorRepository.findAll();
        List<ProfesorDTO> profesoresDTOS = profesores.stream()
                .map(this::convertToDTOProfesor)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profesoresDTOS);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProfesorDTO> findByIdProfesor(Long id) {
        Optional<Profesor> find = _profesorRepository.findById(id);
        return find.map(this::convertToDTOProfesor)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<ProfesorDTO> saveProfesor(ProfesorDTO profesorDTO) {
        Profesor profesor = convertToEntityProfesor(profesorDTO);
        Profesor save = _profesorRepository.save(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTOProfesor(save));
    }

    @Override
    @Transactional
    public ResponseEntity<ProfesorDTO> updateProfesor(Long id, ProfesorDTO profesorDTO) {
        Optional<Profesor> find = _profesorRepository.findById(id);
        if (find.isPresent()) {
            Profesor profesor = find.get();
            profesor.setNombre(profesorDTO.getNombre());
            Profesor update = _profesorRepository.save(profesor);
            return ResponseEntity.ok(convertToDTOProfesor(update));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteByIdProfesor(Long id) {
        if (!_profesorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (_notaRepository.existsByProfesorId(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Error", "No se puede eliminar el profesor porque tiene notas asociadas.")
                    .build();
        }

        _profesorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param profesorDTO
     * @return
     */
    private Profesor convertToEntityProfesor(ProfesorDTO profesorDTO) {
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorDTO.getNombre());
        return profesor;
    }

    /**
     * @param profesor
     * @return
     */
    private ProfesorDTO convertToDTOProfesor(Profesor profesor) {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setId(profesor.getId());
        profesorDTO.setNombre(profesor.getNombre());
        return profesorDTO;
    }

}
