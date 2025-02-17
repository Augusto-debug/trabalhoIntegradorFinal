package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

import java.time.LocalDate;

public class Coordenacao {
    private Integer cd_coordenacao;
    private String tel_coordenacao;
    private String email_coordenacao;
    private LocalDate dt_inicio;
    private LocalDate dt_fim;
    private Integer cd_curso;
    private Integer cd_professor;
    private Curso curso;
    private Professor professor;

    public Coordenacao() {}

    public Coordenacao(Integer cd_coordenacao, String tel_coordenacao, String email_coordenacao, LocalDate dt_inicio, LocalDate dt_fim, Integer cd_curso, Integer cd_professor) {
        this.cd_coordenacao = cd_coordenacao;
        this.tel_coordenacao = tel_coordenacao;
        this.email_coordenacao = email_coordenacao;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
        this.cd_curso = cd_curso;
        this.cd_professor = cd_professor;
    }

    public Integer getCd_coordenacao() {
        return cd_coordenacao;
    }

    public void setCd_coordenacao(Integer cd_coordenacao) {
        this.cd_coordenacao = cd_coordenacao;
    }

    public String getTel_coordenacao() {
        return tel_coordenacao;
    }

    public void setTel_coordenacao(String tel_coordenacao) {
        this.tel_coordenacao = tel_coordenacao;
    }

    public String getEmail_coordenacao() {
        return email_coordenacao;
    }

    public void setEmail_coordenacao(String email_coordenacao) {
        this.email_coordenacao = email_coordenacao;
    }

    public LocalDate getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(LocalDate dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public LocalDate getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(LocalDate dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Integer getCd_curso() {
        return cd_curso;
    }

    public void setCd_curso(Integer cd_curso) {
        this.cd_curso = cd_curso;
    }

    public Integer getCd_professor() {
        return cd_professor;
    }

    public void setCd_professor(Integer cd_professor) {
        this.cd_professor = cd_professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}