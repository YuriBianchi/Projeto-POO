package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.model.Denuncia;
import com.projeto.model.Usuario;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    List<Denuncia> findByAutor(Usuario autor);
    List<Denuncia> findByResolvido(Boolean resolvido);
    List<Denuncia> findByTipoFraude(String tipoFraude);
}
