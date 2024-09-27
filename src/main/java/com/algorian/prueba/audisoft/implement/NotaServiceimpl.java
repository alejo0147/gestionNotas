package com.algorian.prueba.audisoft.implement;

import com.algorian.prueba.audisoft.dto.NotaDTO;
import com.algorian.prueba.audisoft.entity.Estudiante;
import com.algorian.prueba.audisoft.entity.Nota;
import com.algorian.prueba.audisoft.entity.Profesor;
import com.algorian.prueba.audisoft.repository.IEstudianteRepository;
import com.algorian.prueba.audisoft.repository.INotaRepository;
import com.algorian.prueba.audisoft.repository.IProfesorRepository;
import com.algorian.prueba.audisoft.service.INotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotaServiceimpl implements INotaService {

    private final INotaRepository _notaRepository;
    private final IEstudianteRepository _estudianteRepository;
    private final IProfesorRepository _profesorRepository;

    @Override
    public ResponseEntity<List<NotaDTO>> findAllNota() {
        List<Nota> notas = _notaRepository.findAll();
        List<NotaDTO> notasDTOS = notas.stream()
                .map(this::convertToDTONota)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notasDTOS);
    }

    @Override
    public ResponseEntity<NotaDTO> findByIdNota(Long id) {
        Optional<Nota> find = _notaRepository.findById(id);
        return find.map(this::convertToDTONota)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<NotaDTO> saveNota(NotaDTO notaDTO) {
        Nota nota = convertToEntityNota(notaDTO);
        Nota savedNota = _notaRepository.save(nota);
        return ResponseEntity.ok(convertToDTONota(savedNota));
    }

    @Override
    public ResponseEntity<NotaDTO> updateNota(Long id, NotaDTO notaDTO) {
        return _notaRepository.findById(id)
                .map(existingNota -> {
                    existingNota.setNombre(notaDTO.getNombre());
                    existingNota.setValor(notaDTO.getValor());

                    // Actualizar el Profesor
                    Profesor profesor = _profesorRepository.findById(notaDTO.getIdProfesor())
                            .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
                    existingNota.setProfesor(profesor);

                    // Actualizar el Estudiante
                    Estudiante estudiante = _estudianteRepository.findById(notaDTO.getIdEstudiante())
                            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
                    existingNota.setEstudiante(estudiante);

                    Nota updatedNota = _notaRepository.save(existingNota);
                    return ResponseEntity.ok(convertToDTONota(updatedNota));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<Void> deleteByIdNota(Long id) {
        if (_notaRepository.existsById(id)) {
            _notaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    /**
     *
     * @param notaDTO
     * @return
     */
    private Nota convertToEntityNota(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setNombre(notaDTO.getNombre());
        nota.setValor(notaDTO.getValor());

        Profesor profesor = _profesorRepository.findById(notaDTO.getIdProfesor())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Estudiante estudiante = _estudianteRepository.findById(notaDTO.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        nota.setProfesor(profesor);
        nota.setEstudiante(estudiante);

        return nota;
    }


    /**
     *
     * @param nota
     * @return
     */
    private NotaDTO convertToDTONota(Nota nota) {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setId(nota.getId());
        notaDTO.setNombre(nota.getNombre());
        notaDTO.setValor(nota.getValor());
        notaDTO.setIdEstudiante(nota.getEstudiante().getId());
        notaDTO.setIdProfesor(nota.getProfesor().getId());
        return notaDTO;
    }

}
