package com.algorian.prueba.audisoft.repository;

import com.algorian.prueba.audisoft.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesorRepository extends JpaRepository<Profesor, Long> {
}
