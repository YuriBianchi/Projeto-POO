package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.model.Comentario;
import com.projeto.model.Denuncia;
import com.projeto.model.Usuario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByDenuncia(Denuncia denuncia);
    List<Comentario> findByAutor(Usuario autor);
}
