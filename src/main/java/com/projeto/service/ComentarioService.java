package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.Comentario;
import com.projeto.model.Denuncia;
import com.projeto.model.Usuario;
import com.projeto.repository.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario criarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public List<Comentario> listarPorDenuncia(Denuncia denuncia) {
        return comentarioRepository.findByDenuncia(denuncia);
    }

    public List<Comentario> listarPorAutor(Usuario autor) {
        return comentarioRepository.findByAutor(autor);
    }

    public Comentario atualizarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void adicionarCurtida(Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        if (comentario.isPresent()) {
            Comentario c = comentario.get();
            c.setCurtidas(c.getCurtidas() + 1);
            comentarioRepository.save(c);
        }
    }

    public void deletarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    public int contarComentariosPorDenuncia(Long denunciaId) {
        return comentarioRepository.findByDenuncia(
            new Denuncia() {{ setId(denunciaId); }}
        ).size();
    }
}
