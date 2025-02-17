package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Aluno;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Curso;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Matricula;

@Repository
public class MatriculaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public MatriculaRepository(JdbcTemplate jdbcTemplate, AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Matricula> listar() {
        String sql = "SELECT cd_matricula, dt_matricula, cd_aluno, cd_curso FROM Matricula";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Matricula matricula = new Matricula();
            matricula.setCd_matricula(rs.getInt("cd_matricula"));
            matricula.setDt_matricula(rs.getDate("dt_matricula").toLocalDate());
            matricula.setCd_aluno(rs.getInt("cd_aluno"));
            matricula.setCd_curso(rs.getInt("cd_curso"));

            Optional<Aluno> alunoOptional = alunoRepository.buscarPorCodigo(rs.getInt("cd_aluno"));
            Optional<Curso> cursoOptional = cursoRepository.buscarPorCodigo(rs.getInt("cd_curso"));

            if (alunoOptional.isPresent()) {
                matricula.setAluno(alunoOptional.get());
            } else {
                throw new RuntimeException("Aluno não encontrado para o código: " + rs.getInt("cd_aluno"));
            }

            if (cursoOptional.isPresent()) {
                matricula.setCurso(cursoOptional.get());
            } else {
                throw new RuntimeException("Curso não encontrado para o código: " + rs.getInt("cd_curso"));
            }

            return matricula;
        });
    }

    public Optional<Matricula> buscarPorCodigo(Integer cd_matricula) {
        String sql = "SELECT cd_matricula, dt_matricula, cd_aluno, cd_curso FROM Matricula WHERE cd_matricula = ?";
        try {
            Matricula matricula = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Matricula m = new Matricula();
                m.setCd_matricula(rs.getInt("cd_matricula"));
                m.setDt_matricula(rs.getDate("dt_matricula").toLocalDate());
                m.setCd_aluno(rs.getInt("cd_aluno"));
                m.setCd_curso(rs.getInt("cd_curso"));

                Optional<Aluno> alunoOptional = alunoRepository.buscarPorCodigo(rs.getInt("cd_aluno"));
                Optional<Curso> cursoOptional = cursoRepository.buscarPorCodigo(rs.getInt("cd_curso"));

                if (alunoOptional.isPresent()) {
                    m.setAluno(alunoOptional.get());
                } else {
                    throw new RuntimeException("Aluno não encontrado para o código: " + rs.getInt("cd_aluno"));
                }

                if (cursoOptional.isPresent()) {
                    m.setCurso(cursoOptional.get());
                } else {
                    throw new RuntimeException("Curso não encontrado para o código: " + rs.getInt("cd_curso"));
                }

                return m;
            }, cd_matricula);

            return Optional.ofNullable(matricula);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void salvar(Matricula matricula) {
        String sql = "INSERT INTO Matricula (dt_matricula, cd_aluno, cd_curso) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, matricula.getDt_matricula(), matricula.getCd_aluno(), matricula.getCd_curso());
    }

    public void atualizar(Matricula matricula) {
        String sql = "UPDATE Matricula SET dt_matricula = ?, cd_aluno = ?, cd_curso = ? WHERE cd_matricula = ?";
        jdbcTemplate.update(sql, matricula.getDt_matricula(), matricula.getCd_aluno(), matricula.getCd_curso(), matricula.getCd_matricula());
    }

    public void excluir(Integer cd_matricula) {
        try {
            String sql = "DELETE FROM Matricula WHERE cd_matricula = ?";
            jdbcTemplate.update(sql, cd_matricula);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir a matrícula. Verifique se há dependências associadas.");
        }
    }
}