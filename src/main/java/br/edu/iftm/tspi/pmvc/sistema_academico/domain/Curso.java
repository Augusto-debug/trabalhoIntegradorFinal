package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class Curso {
    private Integer cd_curso;
    private String nome_curso;
    private String area_curso;
    private Integer carga_horaria_curso;
    private String modalidade_curso;

    public void setModalidade_curso(String modalidade_curso) {
        this.modalidade_curso = modalidade_curso;
    }

    public Curso() {
    }

    public Curso(Integer cd_curso, String nome_curso, String area_curso, Integer carga_horaria_curso, String modalidade_curso) {
        this.cd_curso = cd_curso;
        this.nome_curso = nome_curso;
        this.area_curso = area_curso;
        this.carga_horaria_curso = carga_horaria_curso;
        this.modalidade_curso = modalidade_curso;
    }

    public Integer getCd_curso() {
        return cd_curso;
    }

    public void setCd_curso(Integer cd_curso) {
        this.cd_curso = cd_curso;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public String getArea_curso() {
        return area_curso;
    }

    public void setArea_curso(String area_curso) {
        this.area_curso = area_curso;
    }

    public Integer getCarga_horaria_curso() {
        return carga_horaria_curso;
    }

    public void setCarga_horaria_curso(Integer carga_horaria_curso) {
        this.carga_horaria_curso = carga_horaria_curso;
    }

    public String getModalidade_curso() {
        return modalidade_curso;
    }
}