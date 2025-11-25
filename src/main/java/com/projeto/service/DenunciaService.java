package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.Denuncia;
import com.projeto.model.Usuario;
import com.projeto.repository.DenunciaRepository;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public Denuncia criarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public Optional<Denuncia> buscarPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    public List<Denuncia> listarTodas() {
        return denunciaRepository.findAll();
    }

    public List<Denuncia> listarPorAutor(Usuario autor) {
        return denunciaRepository.findByAutor(autor);
    }

    public List<Denuncia> listarPorStatus(Boolean resolvido) {
        return denunciaRepository.findByResolvido(resolvido);
    }

    public List<Denuncia> listarPorTipoFraude(String tipoFraude) {
        return denunciaRepository.findByTipoFraude(tipoFraude);
    }

    public Denuncia atualizarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public void marcarComoResolvida(Long id) {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id);
        if (denuncia.isPresent()) {
            Denuncia d = denuncia.get();
            d.setResolvido(true);
            denunciaRepository.save(d);
        }
    }

    public void adicionarCurtida(Long id) {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id);
        if (denuncia.isPresent()) {
            Denuncia d = denuncia.get();
            d.setCurtidas(d.getCurtidas() + 1);
            denunciaRepository.save(d);
        }
    }

    public void deletarDenuncia(Long id) {
        denunciaRepository.deleteById(id);
    }
}
