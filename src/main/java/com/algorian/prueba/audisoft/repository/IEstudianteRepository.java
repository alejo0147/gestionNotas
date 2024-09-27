package com.algorian.prueba.audisoft.repository;

import com.algorian.prueba.audisoft.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {
}
