package co.edu.javeriana.bibliotecas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private LocalDateTime fecha;
    private int estado;
    private String mensaje;
    private Map<String, String> errores;
}
