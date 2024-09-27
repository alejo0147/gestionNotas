package com.algorian.prueba.audisoft.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaDTO {
    private Long id;
    private String nombre;
    private Double valor;
    private Long idProfesor;
    private Long idEstudiante;
}
