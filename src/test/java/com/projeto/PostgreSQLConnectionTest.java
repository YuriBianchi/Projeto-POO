package com.projeto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.projeto.model.Usuario;
import com.projeto.repository.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class PostgreSQLConnectionTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testConexaoComPostgreSQL() {
        // Teste básico de conexão
        assertNotNull(usuarioRepository);
    }

    @Test
    void testSalvarUsuarioComCPFValido() {
        // CPF válido: 111.444.777-35
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@example.com");
        usuario.setNome("João Silva");
        usuario.setSenha("senha123");
        usuario.setCpf("11144477735");
        usuario.setTipo("usuario");

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        assertNotNull(usuarioSalvo.getId());
        assertEquals("teste@example.com", usuarioSalvo.getEmail());
        assertEquals("11144477735", usuarioSalvo.getCpf());
        assertTrue(usuarioRepository.existsById(usuarioSalvo.getId()));
    }

    @Test
    void testBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("buscar@example.com");
        usuario.setNome("Maria Santos");
        usuario.setSenha("senha456");
        usuario.setCpf("12345678909");
        usuario.setTipo("usuario");

        usuarioRepository.save(usuario);

        var usuarioEncontrado = usuarioRepository.findByEmail("buscar@example.com");
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("Maria Santos", usuarioEncontrado.get().getNome());
    }

    @Test
    void testBuscarUsuarioPorCPF() {
        Usuario usuario = new Usuario();
        usuario.setEmail("cpf@example.com");
        usuario.setNome("Pedro Costa");
        usuario.setSenha("senha789");
        usuario.setCpf("98765432100");
        usuario.setTipo("admin");

        usuarioRepository.save(usuario);

        var usuarioEncontrado = usuarioRepository.findByCpf("98765432100");
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("admin", usuarioEncontrado.get().getTipo());
    }
}
