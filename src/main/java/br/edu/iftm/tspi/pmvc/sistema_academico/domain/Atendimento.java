package br.edu.iftm.tspi.pmvc.sistema_academico.domain;

public class Atendimento {
    private Integer cd_atendimento;
    private String assunto_atendimento;
    private String status;
    private Aluno cd_aluno;
    private Coordenacao cd_coordenacao;

    public Atendimento() {}

    public Atendimento(Integer cd_atendimento, String assunto_atendimento, String status, Aluno cd_aluno, Coordenacao cd_coordenacao) {
        this.cd_atendimento = cd_atendimento;
        this.assunto_atendimento = assunto_atendimento;
        this.status = status;
        this.cd_aluno = cd_aluno;
        this.cd_coordenacao = cd_coordenacao;
    }

    public Integer getCd_atendimento() {
        return cd_atendimento;
    }

    public void setCd_atendimento(Integer cd_atendimento) {
        this.cd_atendimento = cd_atendimento;
    }

    public String getAssunto_atendimento() {
        return assunto_atendimento;
    }

    public void setAssunto_atendimento(String assunto_atendimento) {
        this.assunto_atendimento = assunto_atendimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Aluno getCd_aluno() {
        if (cd_aluno == null) {
            cd_aluno = new Aluno();
        }
        return cd_aluno;
    }

    public void setCd_aluno(Aluno cd_aluno) {
        this.cd_aluno = cd_aluno;
    }

    public Coordenacao getCd_coordenacao() {
        if (cd_coordenacao == null) {
            cd_coordenacao = new Coordenacao();
        }
        return cd_coordenacao;
    }

    public void setCd_coordenacao(Coordenacao cd_coordenacao) {
        this.cd_coordenacao = cd_coordenacao;
    }

    public void setProfessor(Professor professor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}