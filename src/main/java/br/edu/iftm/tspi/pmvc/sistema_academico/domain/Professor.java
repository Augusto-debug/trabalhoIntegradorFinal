package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class Professor {
    private Integer cd_professor;
    private String nome_professor;
    private String cpf_professor;
    private String tel_professor;
    private String email_professor;
    private String senha_professor;

    public Professor() {}

    public Professor(Integer cd_professor, String nome_professor, String cpf_professor, String tel_professor, String email_professor, String senha_professor) {
        this.cd_professor = cd_professor;
        this.nome_professor = nome_professor;
        this.cpf_professor = cpf_professor;
        this.tel_professor = tel_professor;
        this.email_professor = email_professor;
        this.senha_professor = senha_professor;
    }

    public Integer getCd_professor() {
        return cd_professor;
    }

    public void setCd_professor(Integer cd_professor) {
        this.cd_professor = cd_professor;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public String getCpf_professor() {
        return cpf_professor;
    }

    public void setCpf_professor(String cpf_professor) {
        this.cpf_professor = cpf_professor;
    }

    public String getTel_professor() {
        return tel_professor;
    }

    public void setTel_professor(String tel_professor) {
        this.tel_professor = tel_professor;
    }

    public String getEmail_professor() {
        return email_professor;
    }

    public void setEmail_professor(String email_professor) {
        this.email_professor = email_professor;
    }

    public String getSenha_professor() {
        return senha_professor;
    }

    public void setSenha_professor(String senha_professor) {
        this.senha_professor = senha_professor;
    }
}
