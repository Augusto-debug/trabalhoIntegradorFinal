package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Professor;

@Repository
public class ProfessorRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProfessorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Professor> listar() {
        String sql = "SELECT * FROM professor";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Professor.class));
    }

    public Optional<Professor> buscarPorCodigo(Integer cd_professor) {
        String sql = "SELECT * FROM professor WHERE cd_professor = ?";
        try {
            Professor professor = jdbcTemplate.queryForObject(sql, new Object[]{cd_professor}, new BeanPropertyRowMapper<>(Professor.class));
            return Optional.ofNullable(professor);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void salvar(Professor professor) {
        String sql = "INSERT INTO professor (nome_professor, cpf_professor, tel_professor, email_professor, senha_professor) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, professor.getNome_professor(), professor.getCpf_professor(), professor.getTel_professor(), professor.getEmail_professor(), professor.getSenha_professor());
    }

    public void atualizar(Professor professor) {
        String sql = "UPDATE professor SET nome_professor = ?, cpf_professor = ?, tel_professor = ?, email_professor = ?, senha_professor = ? WHERE cd_professor = ?";
        jdbcTemplate.update(sql, professor.getNome_professor(), professor.getCpf_professor(), professor.getTel_professor(), professor.getEmail_professor(), professor.getSenha_professor(), professor.getCd_professor());
    }

    public void excluir(Integer cd_professor) {
        String sql = "DELETE FROM professor WHERE cd_professor = ?";
        jdbcTemplate.update(sql, cd_professor);
    }
}
