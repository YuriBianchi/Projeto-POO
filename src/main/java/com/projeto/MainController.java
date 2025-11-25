package com.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.model.Usuario;
import com.projeto.service.UsuarioService;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String procesarCadastro(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String cpf,
            @RequestParam String senha,
            @RequestParam String confirmaSenha,
            Model model) {

        // Validar se as senhas coincidem
        if (!senha.equals(confirmaSenha)) {
            model.addAttribute("mensagem", "As senhas não coincidem!");
            model.addAttribute("tipo", "erro");
            return "cadastro";
        }

        // Validar se o email já existe
        if (usuarioService.usuarioExiste(email)) {
            model.addAttribute("mensagem", "Este email já está cadastrado!");
            model.addAttribute("tipo", "erro");
            return "cadastro";
        }

        // Validar se o CPF já existe
        if (usuarioService.cpfJaExiste(cpf)) {
            model.addAttribute("mensagem", "Este CPF já está cadastrado!");
            model.addAttribute("tipo", "erro");
            return "cadastro";
        }

        // Criar novo usuário
        try {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(nome);
            novoUsuario.setEmail(email);
            novoUsuario.setCpf(cpf);
            novoUsuario.setSenha(senha);
            novoUsuario.setTipo("usuario");

            usuarioService.criarUsuario(novoUsuario);

            model.addAttribute("mensagem", "Cadastro realizado com sucesso! Faça login para continuar.");
            model.addAttribute("tipo", "sucesso");
            return "login";
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao realizar o cadastro: " + e.getMessage());
            model.addAttribute("tipo", "erro");
            return "cadastro";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
