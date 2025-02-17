package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Aluno;


@Repository
public class AlunoRepository {
    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Aluno> listar() {
        String sql = "SELECT * FROM aluno";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Aluno.class));
    }

    public Optional<Aluno> buscarPorCodigo(Integer cd_aluno) {
        String sql = "SELECT * FROM aluno WHERE cd_aluno = ?";
        try {
            Aluno aluno = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Aluno.class), cd_aluno);
            return Optional.ofNullable(aluno);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome_aluno, email_aluno, senha_aluno, cpf_aluno, tel_aluno, data_nascimento, sexo_aluno) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, aluno.getNome_aluno(), aluno.getEmail_aluno(), aluno.getSenha_aluno(), aluno.getCpf_aluno(), aluno.getTel_aluno(), aluno.getData_nascimento(), aluno.getSexo_aluno());
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome_aluno = ?, email_aluno = ?, senha_aluno = ?, cpf_aluno = ?, tel_aluno = ?, data_nascimento = ?, sexo_aluno = ? WHERE cd_aluno = ?";
        jdbcTemplate.update(sql, aluno.getNome_aluno(), aluno.getEmail_aluno(), aluno.getSenha_aluno(), aluno.getCpf_aluno(), aluno.getTel_aluno(), aluno.getData_nascimento(), aluno.getSexo_aluno(), aluno.getCd_aluno());
    }

    public void excluir(Integer cd_aluno) {
        try{
            String sql = "DELETE FROM aluno WHERE cd_aluno = ?";
            jdbcTemplate.update(sql, cd_aluno);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o aluno, pois ela está associada a uma matrícula.");
        }

    }
}