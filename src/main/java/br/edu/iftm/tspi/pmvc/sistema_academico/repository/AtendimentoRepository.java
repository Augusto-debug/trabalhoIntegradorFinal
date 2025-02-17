package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Aluno;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Atendimento;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Coordenacao;

@Repository
public class AtendimentoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AlunoRepository alunoRepository;
    private final CoordenacaoRepository coordenacaoRepository;

    public AtendimentoRepository(JdbcTemplate jdbcTemplate,
                                 AlunoRepository alunoRepository,
                                 CoordenacaoRepository coordenacaoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.alunoRepository = alunoRepository;
        this.coordenacaoRepository = coordenacaoRepository;
    }

    public List<Atendimento> listar() {
        String sql = "SELECT * FROM Atendimento";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Atendimento atendimento = new Atendimento();
            atendimento.setCd_atendimento(rs.getInt("cd_atendimento"));
            atendimento.setAssunto_atendimento(rs.getString("assunto_atendimento"));
            atendimento.setStatus(rs.getString("status"));

            Integer cdAluno = rs.getInt("cd_aluno");
            Aluno aluno = alunoRepository.buscarPorCodigo(cdAluno).orElse(null);
            atendimento.setCd_aluno(aluno);

            Integer cdCoordenacao = rs.getInt("cd_coordenacao");
            Coordenacao coordenacao = coordenacaoRepository.buscarPorCodigo(cdCoordenacao).orElse(null);
            atendimento.setCd_coordenacao(coordenacao);

            return atendimento;
        });
    }

    public Atendimento buscarPorCodigo(Integer cd_atendimento) {
        String sql = "SELECT * FROM Atendimento WHERE cd_atendimento = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Atendimento atendimento = new Atendimento();
            atendimento.setCd_atendimento(rs.getInt("cd_atendimento"));
            atendimento.setAssunto_atendimento(rs.getString("assunto_atendimento"));
            atendimento.setStatus(rs.getString("status"));
    
            Integer cdAluno = rs.getInt("cd_aluno");
            Aluno aluno = alunoRepository.buscarPorCodigo(cdAluno).orElse(null);
            atendimento.setCd_aluno(aluno);
    
            Integer cdCoordenacao = rs.getInt("cd_coordenacao");
            Coordenacao coordenacao = coordenacaoRepository.buscarPorCodigo(cdCoordenacao).orElse(null);
            atendimento.setCd_coordenacao(coordenacao);
    
            return atendimento;
        }, cd_atendimento);
    }
    

    public void salvar(Atendimento atendimento) {
        String sql = "INSERT INTO Atendimento (assunto_atendimento, status, cd_aluno, cd_coordenacao) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                atendimento.getAssunto_atendimento(),
                atendimento.getStatus(),
                atendimento.getCd_aluno().getCd_aluno(),
                atendimento.getCd_coordenacao().getCd_coordenacao()
        );
    }

    public void atualizar(Atendimento atendimento) {
        String sql = "UPDATE Atendimento SET assunto_atendimento = ?, status = ?, cd_aluno = ?, cd_coordenacao = ? WHERE cd_atendimento = ?";
        jdbcTemplate.update(sql,
                atendimento.getAssunto_atendimento(),
                atendimento.getStatus(),
                atendimento.getCd_aluno().getCd_aluno(),
                atendimento.getCd_coordenacao().getCd_coordenacao(),
                atendimento.getCd_atendimento()
        );
    }

    public void excluir(Integer cd_atendimento) {
        String sql = "DELETE FROM Atendimento WHERE cd_atendimento = ?";
        jdbcTemplate.update(sql, cd_atendimento);
    }
}