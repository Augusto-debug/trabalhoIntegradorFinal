package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

import java.time.LocalDate;

public class Aluno {
    private Integer cd_aluno;
    private String nome_aluno;
    private String email_aluno;
    private String senha_aluno;
    private String cpf_aluno;
    private String tel_aluno;
    private LocalDate data_nascimento;
    private String sexo_aluno;

    public Aluno() {}

    public Aluno(Integer cd_aluno, String nome_aluno, String email_aluno, String senha_aluno, String cpf_aluno, String tel_aluno, LocalDate data_nascimento, String sexo_aluno) {
        this.cd_aluno = cd_aluno;
        this.nome_aluno = nome_aluno;
        this.email_aluno = email_aluno;
        this.senha_aluno = senha_aluno;
        this.cpf_aluno = cpf_aluno;
        this.tel_aluno = tel_aluno;
        this.data_nascimento = data_nascimento;
        this.sexo_aluno = sexo_aluno;
    }

    public Integer getCd_aluno() {
        return cd_aluno;
    }

    public void setCd_aluno(Integer cd_aluno) {
        this.cd_aluno = cd_aluno;
    }

    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getEmail_aluno() {
        return email_aluno;
    }

    public void setEmail_aluno(String email_aluno) {
        this.email_aluno = email_aluno;
    }

    public String getSenha_aluno() {
        return senha_aluno;
    }

    public void setSenha_aluno(String senha_aluno) {
        this.senha_aluno = senha_aluno;
    }

    public String getCpf_aluno() {
        return cpf_aluno;
    }

    public void setCpf_aluno(String cpf_aluno) {
        this.cpf_aluno = cpf_aluno;
    }

    public String getTel_aluno() {
        return tel_aluno;
    }

    public void setTel_aluno(String tel_aluno) {
        this.tel_aluno = tel_aluno;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo_aluno() {
        return sexo_aluno;
    }

    public void setSexo_aluno(String sexo_aluno) {
        this.sexo_aluno = sexo_aluno;
    }
}