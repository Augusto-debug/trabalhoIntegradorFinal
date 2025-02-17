package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Turma;

@Repository
public class TurmaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public TurmaRepository(JdbcTemplate jdbcTemplate, DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public List<Turma> listar() {
        String sql = "SELECT * FROM Turma";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Turma turma = new Turma();
            turma.setCd_turma(rs.getInt("cd_turma"));
            turma.setAno_semestre(rs.getString("ano_semestre"));
            Integer cd_disciplina = rs.getInt("cd_disciplina");
            if (cd_disciplina != null) {
                turma.setCd_disciplina(disciplinaRepository.buscarPorCodigo(cd_disciplina));
            }
            Integer cd_professor = rs.getInt("cd_professor");
            if (cd_professor != null) {
                turma.setCd_professor(professorRepository.buscarPorCodigo(cd_professor).orElse(null));
            }

            return turma;
        });
    }

    public Turma buscarPorCodigo(Integer cd_turma) {
        String sql = "SELECT * FROM Turma WHERE cd_turma = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Turma turma = new Turma();
            turma.setCd_turma(rs.getInt("cd_turma"));
            turma.setAno_semestre(rs.getString("ano_semestre"));

            Integer cd_disciplina = rs.getInt("cd_disciplina");
            if (cd_disciplina != null) {
                turma.setCd_disciplina(disciplinaRepository.buscarPorCodigo(cd_disciplina));
            }

            Integer cd_professor = rs.getInt("cd_professor");
            if (cd_professor != null) {
                turma.setCd_professor(professorRepository.buscarPorCodigo(cd_professor).orElse(null));
            }

            return turma;
        }, cd_turma);
    }

    public void salvar(Turma turma) {
        String sql = "INSERT INTO Turma (ano_semestre, cd_disciplina, cd_professor) VALUES (?, ?, ?)";
        

        Integer cd_disciplina = (turma.getCd_disciplina() != null) ? turma.getCd_disciplina().getCd_disciplina() : null;
        Integer cd_professor = (turma.getCd_professor() != null) ? turma.getCd_professor().getCd_professor() : null;

        jdbcTemplate.update(sql, turma.getAno_semestre(), cd_disciplina, cd_professor);
    }

    public void atualizar(Turma turma) {
        String sql = "UPDATE Turma SET ano_semestre = ?, cd_disciplina = ?, cd_professor = ? WHERE cd_turma = ?";

        Integer cd_disciplina = (turma.getCd_disciplina() != null) ? turma.getCd_disciplina().getCd_disciplina() : null;
        Integer cd_professor = (turma.getCd_professor() != null) ? turma.getCd_professor().getCd_professor() : null;

        jdbcTemplate.update(sql, turma.getAno_semestre(), cd_disciplina, cd_professor, turma.getCd_turma());
    }

    public void excluir(Integer cd_turma) {
        String sql = "DELETE FROM Turma WHERE cd_turma = ?";
        jdbcTemplate.update(sql, cd_turma);
    }
}