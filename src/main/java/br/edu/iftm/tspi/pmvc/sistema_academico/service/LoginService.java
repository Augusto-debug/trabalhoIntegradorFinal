package br.edu.iftm.tspi.pmvc.sistema_academico.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Login;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.LoginRepository;


@Service
public class LoginService {
   private LoginRepository repository;
   private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   public LoginService(LoginRepository repository) {
       this.repository = repository;
   }
   public boolean verificaLogin(Login loginDigitado) {
       try {
           Login loginBanco = repository.validarLogin(loginDigitado);
           return encoder.matches(loginDigitado.getSenha(),loginBanco.getSenha());
       } catch(EmptyResultDataAccessException e) {
           return false;
       }
   }

   public void salvar(Login login) {
    login.setSenha(encoder.encode(login.getSenha()));
    repository.salvar(login);
    }
   
}