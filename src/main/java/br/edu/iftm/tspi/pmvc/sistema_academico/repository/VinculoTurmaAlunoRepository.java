package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Aluno;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Turma;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.VinculoTurmaAluno;

@Repository
public class VinculoTurmaAlunoRepository {
    private final JdbcTemplate jdbcTemplate;

    public VinculoTurmaAlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VinculoTurmaAluno> listar() {
        String sql = "SELECT v.*, a.nome_aluno, t.ano_semestre " +
                "FROM VinculoTurmaAluno v " +
                "JOIN aluno a ON v.cd_aluno = a.cd_aluno " +
                "JOIN turma t ON v.cd_turma = t.cd_turma " +
                "ORDER BY v.cd_vinculo ASC";

        try {
            List<VinculoTurmaAluno> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
                VinculoTurmaAluno vinculo = new VinculoTurmaAluno();
                vinculo.setCd_vinculo(rs.getInt("cd_vinculo"));
                vinculo.setNota(rs.getDouble("nota"));
                vinculo.setFrequencia(rs.getDouble("frequencia"));

                Turma turma = new Turma();
                turma.setCd_turma(rs.getInt("cd_turma"));
                turma.setAno_semestre(rs.getString("ano_semestre"));
                vinculo.setCd_turma(turma);

                Aluno aluno = new Aluno();
                aluno.setCd_aluno(rs.getInt("cd_aluno"));
                aluno.setNome_aluno(rs.getString("nome_aluno"));
                vinculo.setCd_aluno(aluno);

                return vinculo;
            });
            System.out.println("Result from listar(): " + result);
            return result;
        } catch (DataAccessException e) {
            System.err.println("Error executing query: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    public VinculoTurmaAluno buscarPorCodigo(Integer cd_vinculo) {
        String sql = "SELECT v.*, a.nome_aluno " +
                "FROM VinculoTurmaAluno v " +
                "JOIN aluno a ON v.cd_aluno = a.cd_aluno " +
                "WHERE v.cd_vinculo = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            VinculoTurmaAluno vinculo = new VinculoTurmaAluno();
            vinculo.setCd_vinculo(rs.getInt("cd_vinculo"));
            vinculo.setNota(rs.getDouble("nota"));
            vinculo.setFrequencia(rs.getDouble("frequencia"));

            Turma turma = new Turma();
            turma.setCd_turma(rs.getInt("cd_turma"));
            vinculo.setCd_turma(turma);

            Aluno aluno = new Aluno();
            aluno.setCd_aluno(rs.getInt("cd_aluno"));
            aluno.setNome_aluno(rs.getString("nome_aluno"));
            vinculo.setCd_aluno(aluno);

            return vinculo;
        }, cd_vinculo);
    }

    public void salvar(VinculoTurmaAluno vinculoTurmaAluno) {
        String sql = "INSERT INTO VinculoTurmaAluno (nota, frequencia, cd_turma, cd_aluno) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, vinculoTurmaAluno.getNota(), vinculoTurmaAluno.getFrequencia(), vinculoTurmaAluno.getCd_turma().getCd_turma(), vinculoTurmaAluno.getCd_aluno().getCd_aluno());
    }

    public void atualizar(VinculoTurmaAluno vinculoTurmaAluno) {
        String sql = "UPDATE VinculoTurmaAluno SET nota = ?, frequencia = ?, cd_turma = ?, cd_aluno = ? WHERE cd_vinculo = ?";
        jdbcTemplate.update(sql, vinculoTurmaAluno.getNota(), vinculoTurmaAluno.getFrequencia(), vinculoTurmaAluno.getCd_turma().getCd_turma(), vinculoTurmaAluno.getCd_aluno().getCd_aluno(), vinculoTurmaAluno.getCd_vinculo());
    }

    public void excluir(Integer cd_vinculo) {
        String sql = "DELETE FROM VinculoTurmaAluno WHERE cd_vinculo = ?";
        jdbcTemplate.update(sql, cd_vinculo);
    }
}
