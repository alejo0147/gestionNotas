package com.algorian.prueba.audisoft.repository;

import com.algorian.prueba.audisoft.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotaRepository extends JpaRepository<Nota, Long> {

    boolean existsByEstudianteId(Long idEstudiante);

    boolean existsByProfesorId(Long idProfesor);

}
