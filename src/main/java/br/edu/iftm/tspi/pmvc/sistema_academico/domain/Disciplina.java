package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class Disciplina {
    private Integer cd_disciplina;
    private String nome_disciplina;
    private String sigla_disciplina;
    private String ementa_disciplina;
    private Integer carga_horaria_disciplina;
    private Curso cd_curso;

    public Disciplina() {}

    public Disciplina(Integer cd_disciplina, String nome_disciplina, String sigla_disciplina, String ementa_disciplina, Integer carga_horaria_disciplina, Curso cd_curso) {
        this.cd_disciplina = cd_disciplina;
        this.nome_disciplina = nome_disciplina;
        this.sigla_disciplina = sigla_disciplina;
        this.ementa_disciplina = ementa_disciplina;
        this.carga_horaria_disciplina = carga_horaria_disciplina;
        this.cd_curso = cd_curso;
    }

    public Integer getCd_disciplina() {
        return cd_disciplina;
    }

    public void setCd_disciplina(Integer cd_disciplina) {
        this.cd_disciplina = cd_disciplina;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public String getSigla_disciplina() {
        return sigla_disciplina;
    }

    public void setSigla_disciplina(String sigla_disciplina) {
        this.sigla_disciplina = sigla_disciplina;
    }

    public String getEmenta_disciplina() {
        return ementa_disciplina;
    }

    public void setEmenta_disciplina(String ementa_disciplina) {
        this.ementa_disciplina = ementa_disciplina;
    }

    public Integer getCarga_horaria_disciplina() {
        return carga_horaria_disciplina;
    }

    public void setCarga_horaria_disciplina(Integer carga_horaria_disciplina) {
        this.carga_horaria_disciplina = carga_horaria_disciplina;
    }

    public Curso getCd_curso() {
        return cd_curso;
    }

    public void setCd_curso(Curso cd_curso) {
        this.cd_curso = cd_curso;
    }
}