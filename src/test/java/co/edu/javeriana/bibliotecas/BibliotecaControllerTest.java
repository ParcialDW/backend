package co.edu.javeriana.bibliotecas;

import static org.hamcrest.Matchers.hasItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BibliotecaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBibliotecasDevuelveLasBibliotecasConSusLibros() throws Exception {
        mockMvc.perform(get("/bibliotecas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Biblioteca Central"))
                .andExpect(jsonPath("$[0].libros[*].titulo").value(hasItem("Cien años de soledad")))
                .andExpect(jsonPath("$[1].nombre").value("Biblioteca Norte"))
                .andExpect(jsonPath("$[1].libros[*].titulo").value(hasItem("El coronel no tiene quien le escriba")))
                .andExpect(jsonPath("$[0].libros[0].biblioteca").doesNotExist());
    }

    @Test
    void postBibliotecasRegistraUnaBiblioteca() throws Exception {
        String json = """
                {"nombre": "Biblioteca Sur", "direccion": "Avenida 68 # 20-10", "ciudad": "Bogotá"}
                """;

        mockMvc.perform(post("/bibliotecas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nombre").value("Biblioteca Sur"))
                .andExpect(jsonPath("$.libros").isEmpty());
    }

    @Test
    void postBibliotecasConDatosVaciosDevuelve400() throws Exception {
        String json = """
                {"nombre": "", "direccion": "", "ciudad": ""}
                """;

        mockMvc.perform(post("/bibliotecas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.nombre").exists())
                .andExpect(jsonPath("$.errores.direccion").exists())
                .andExpect(jsonPath("$.errores.ciudad").exists());
    }
}
