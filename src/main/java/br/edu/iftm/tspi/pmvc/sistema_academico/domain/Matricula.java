package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

import java.time.LocalDate;

public class Matricula {
    private Integer cd_matricula;
    private LocalDate dt_matricula;
    private Integer cd_aluno;
    private Integer cd_curso;
    private Aluno aluno;
    private Curso curso;

    public Matricula() {}

    public Matricula(Integer cd_matricula, LocalDate dt_matricula, Integer cd_aluno, Integer cd_curso) {
        this.cd_matricula = cd_matricula;
        this.dt_matricula = dt_matricula;
        this.cd_aluno = cd_aluno;
        this.cd_curso = cd_curso;
    }

    public Integer getCd_matricula() {
        return cd_matricula;
    }

    public void setCd_matricula(Integer cd_matricula) {
        this.cd_matricula = cd_matricula;
    }

    public LocalDate getDt_matricula() {
        return dt_matricula;
    }

    public void setDt_matricula(LocalDate dt_matricula) {
        this.dt_matricula = dt_matricula;
    }

    public Integer getCd_aluno() {
        return cd_aluno;
    }

    public void setCd_aluno(Integer cd_aluno) {
        this.cd_aluno = cd_aluno;
    }

    public Integer getCd_curso() {
        return cd_curso;
    }

    public void setCd_curso(Integer cd_curso) {
        this.cd_curso = cd_curso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}