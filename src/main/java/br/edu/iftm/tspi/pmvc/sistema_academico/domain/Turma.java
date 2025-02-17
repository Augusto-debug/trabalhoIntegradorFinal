package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class Turma {
    private Integer cd_turma;
    private String ano_semestre;
    private Disciplina cd_disciplina;
    private Professor cd_professor;

    public Turma() {}

    public Turma(Integer cd_turma, String ano_semestre, Disciplina cd_disciplina, Professor cd_professor) {
        this.cd_turma = cd_turma;
        this.ano_semestre = ano_semestre;
        this.cd_disciplina = cd_disciplina;
        this.cd_professor = cd_professor;
    }

    public Integer getCd_turma() {
        return cd_turma;
    }

    public void setCd_turma(Integer cd_turma) {
        this.cd_turma = cd_turma;
    }

    public String getAno_semestre() {
        return ano_semestre;
    }

    public void setAno_semestre(String ano_semestre) {
        this.ano_semestre = ano_semestre;
    }

    public Disciplina getCd_disciplina() {
        return cd_disciplina;
    }

    public void setCd_disciplina(Disciplina cd_disciplina) {
        this.cd_disciplina = cd_disciplina;
    }

    public Professor getCd_professor() {
        return cd_professor;
    }

    public void setCd_professor(Professor cd_professor) {
        this.cd_professor = cd_professor;
    }
}