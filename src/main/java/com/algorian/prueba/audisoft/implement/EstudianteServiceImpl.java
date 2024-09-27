package com.algorian.prueba.audisoft.implement;

import com.algorian.prueba.audisoft.dto.EstudianteDTO;
import com.algorian.prueba.audisoft.entity.Estudiante;
import com.algorian.prueba.audisoft.repository.IEstudianteRepository;
import com.algorian.prueba.audisoft.repository.INotaRepository;
import com.algorian.prueba.audisoft.service.IEstudianteService;
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
public class EstudianteServiceImpl implements IEstudianteService {

    private final IEstudianteRepository _estudianteRepository;
    private final INotaRepository _notaRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<EstudianteDTO>> findAllEstudiante() {
        List<Estudiante> estudiantes = _estudianteRepository.findAll();
        List<EstudianteDTO> estudianteDTOS = estudiantes.stream()
                .map(this::convertToDTOEstudiante)
                .collect(Collectors.toList());
        return ResponseEntity.ok(estudianteDTOS);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EstudianteDTO> findByIdEstudiante(Long id) {
        Optional<Estudiante> find = _estudianteRepository.findById(id);
        return find.map(this::convertToDTOEstudiante)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<EstudianteDTO> saveEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntityEstudiante(estudianteDTO);
        Estudiante save = _estudianteRepository.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTOEstudiante(save));
    }

    @Override
    @Transactional
    public ResponseEntity<EstudianteDTO> updateEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Optional<Estudiante> find = _estudianteRepository.findById(id);
        if (find.isPresent()){
            Estudiante estudiante = find.get();
            estudiante.setNombre(estudianteDTO.getNombre());
            Estudiante update = _estudianteRepository.save(estudiante);
            return ResponseEntity.ok(convertToDTOEstudiante(update));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteByIdEstudiante(Long id) {
        if (!_estudianteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (_notaRepository.existsByEstudianteId(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Error", "No se puede eliminar el estudiante porque tiene notas asociadas.")
                    .build();
        }

        _estudianteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param estudianteDTO
     * @return
     */
    private Estudiante convertToEntityEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        return estudiante;
    }

    /**
     *
     * @param estudiante
     * @return
     */
    private EstudianteDTO convertToDTOEstudiante(Estudiante estudiante) {
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        estudianteDTO.setId(estudiante.getId());
        estudianteDTO.setNombre(estudiante.getNombre());
        return estudianteDTO;
    }

}
