package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Login;
import br.edu.iftm.tspi.pmvc.sistema_academico.service.LoginService;

@Controller
public class LoginController {
   
   private final LoginService service;

   public LoginController(LoginService service) {
       this.service = service;
   }

    @GetMapping("/")
    public String loginPage() {
        return "login/login";
    }

    @PostMapping("/login/entrar")
    public String entrar(@RequestParam String usuario, @RequestParam String senha, Model model) {
        if (validarUsuario(usuario, senha)) {
            return "redirect:/menu";
        } else {
            model.addAttribute("mensagem", "Usuário ou senha inválidos.");
            return "login/login";
        }
    }

    private boolean validarUsuario(String usuario, String senha) {
        Login loginDigitado = new Login(usuario, senha);
        return service.verificaLogin(loginDigitado);
    }


    @GetMapping("/menu")
    public String menuPage() {
        return "menu";
    }
    

   @GetMapping("/login/telaNovoUsuario")
   public String novo(Model model) {
       return "login/cadastro";
   }

   @PostMapping("/login/novoUsuario")
   public String novoUsuario(Login loginDigitado, Model model) {
       service.salvar(loginDigitado);
       model.addAttribute("mensagem", "Usuário " + loginDigitado.getUsuario() + " cadastrado com sucesso");
       return "login/login";
   }
}
