package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class VinculoTurmaAluno {
    private Integer cd_vinculo;
    private Double nota;
    private Double frequencia;
    private Turma cd_turma;
    private Aluno cd_aluno;

    public VinculoTurmaAluno() {}

    public VinculoTurmaAluno(Integer cd_vinculo, Double nota, Double frequencia, Turma cd_turma, Aluno cd_aluno) {
        this.cd_vinculo = cd_vinculo;
        this.nota = nota;
        this.frequencia = frequencia;
        this.cd_turma = cd_turma;
        this.cd_aluno = cd_aluno;
    }

    public Integer getCd_vinculo() {
        return cd_vinculo;
    }

    public void setCd_vinculo(Integer cd_vinculo) {
        this.cd_vinculo = cd_vinculo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public Turma getCd_turma() {
        return cd_turma;
    }

    public void setCd_turma(Turma cd_turma) {
        this.cd_turma = cd_turma;
    }

    public Aluno getCd_aluno() {
        return cd_aluno;
    }

    public void setCd_aluno(Aluno cd_aluno) {
        this.cd_aluno = cd_aluno;
    }
}