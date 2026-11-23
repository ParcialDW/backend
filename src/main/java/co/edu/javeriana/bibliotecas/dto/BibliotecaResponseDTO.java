package co.edu.javeriana.bibliotecas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BibliotecaResponseDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<LibroDTO> libros = new ArrayList<>();
}
