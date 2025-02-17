package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class Login {

   private String usuario;
   private String senha;

   public Login() {
   }

   public Login(String usuario, String senha) {
       this.usuario = usuario;
       this.senha = senha;
   }

   public String getUsuario() {
       return usuario;
   }

   public void setUsuario(String usuario) {
       this.usuario = usuario;
   }

   public String getSenha() {
       return senha;
   }

   public void setSenha(String senha) {
       this.senha = senha;
   }

   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       Login login = (Login) o;
       return usuario.equals(login.usuario) && senha.equals(login.senha);
   }

   @Override
   public int hashCode() {
       return 31 * usuario.hashCode() + senha.hashCode();
   }
}
