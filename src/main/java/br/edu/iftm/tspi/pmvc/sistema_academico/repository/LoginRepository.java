package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

 import org.springframework.jdbc.core.BeanPropertyRowMapper;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Repository;

 import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Login;
 
 @Repository
 public class LoginRepository {
    private JdbcTemplate db;

    public LoginRepository(JdbcTemplate db) {
        this.db = db;
    }
   public Login validarLogin(Login loginDigitado) {
        String sql = "select usuario,senha from login where usuario = ?";
        return db.queryForObject(
                            sql,
                            new BeanPropertyRowMapper<>(Login.class),
                            loginDigitado.getUsuario());
    }

    public void salvar(Login login) {
        String sql = "insert into login(usuario,senha) values(?,?)";
        db.update(sql,login.getUsuario(),login.getSenha());
    }
 }

