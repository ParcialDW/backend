package co.edu.javeriana.bibliotecas.service;

import co.edu.javeriana.bibliotecas.dto.BibliotecaRequestDTO;
import co.edu.javeriana.bibliotecas.dto.BibliotecaResponseDTO;
import co.edu.javeriana.bibliotecas.entity.Biblioteca;
import co.edu.javeriana.bibliotecas.repository.BibliotecaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public BibliotecaResponseDTO registrar(BibliotecaRequestDTO request) {
        Biblioteca biblioteca = modelMapper.map(request, Biblioteca.class);
        Biblioteca guardada = bibliotecaRepository.save(biblioteca);
        return modelMapper.map(guardada, BibliotecaResponseDTO.class);
    }


    @Transactional(readOnly = true)
    public List<BibliotecaResponseDTO> listar() {
        return bibliotecaRepository.findAllByOrderByIdAsc()
                .stream()
                .map(biblioteca -> modelMapper.map(biblioteca, BibliotecaResponseDTO.class))
                .toList();
    }
}
