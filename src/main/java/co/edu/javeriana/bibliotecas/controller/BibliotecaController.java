package co.edu.javeriana.bibliotecas.controller;

import co.edu.javeriana.bibliotecas.dto.BibliotecaRequestDTO;
import co.edu.javeriana.bibliotecas.dto.BibliotecaResponseDTO;
import co.edu.javeriana.bibliotecas.service.BibliotecaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/bibliotecas")
@RequiredArgsConstructor
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    @PostMapping
    public ResponseEntity<BibliotecaResponseDTO> registrar(@Valid @RequestBody BibliotecaRequestDTO request) {
        BibliotecaResponseDTO creada = bibliotecaService.registrar(request);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();

        return ResponseEntity.created(ubicacion).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaResponseDTO>> listar() {
        return ResponseEntity.ok(bibliotecaService.listar());
    }
}
