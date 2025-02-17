package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Curso;

@Repository
public class CursoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CursoRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Curso> listar() {
        String sql = "SELECT cd_curso, nome_curso, area_curso, carga_horaria_curso, modalidade_curso FROM Curso";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Curso curso = new Curso();
            curso.setCd_curso(rs.getInt("cd_curso"));
            curso.setNome_curso(rs.getString("nome_curso"));
            curso.setArea_curso(rs.getString("area_curso"));
            curso.setCarga_horaria_curso(rs.getInt("carga_horaria_curso"));
            curso.setModalidade_curso(rs.getString("modalidade_curso"));
            return curso;
        });
    }

    public Optional<Curso> buscarPorCodigo(Integer cd_curso) {
        String sql = "SELECT * FROM curso WHERE cd_curso = ?";
        try {
            Curso curso = jdbcTemplate.queryForObject(sql, new Object[]{cd_curso}, new BeanPropertyRowMapper<>(Curso.class));
            return Optional.ofNullable(curso);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    

    public void salvar(Curso curso) {
        String sql = "INSERT INTO curso (nome_curso, area_curso, carga_horaria_curso, modalidade_curso) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, curso.getNome_curso(), curso.getArea_curso(), curso.getCarga_horaria_curso(), curso.getModalidade_curso());
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome_curso = ?, area_curso = ?, carga_horaria_curso = ?, modalidade_curso = ? WHERE cd_curso = ?";
        jdbcTemplate.update(sql, curso.getNome_curso(), curso.getArea_curso(), curso.getCarga_horaria_curso(), curso.getModalidade_curso(), curso.getCd_curso());
    }

    public void excluir(Integer cd_curso) {
        String sql = "DELETE FROM curso WHERE cd_curso = ?";
        jdbcTemplate.update(sql, cd_curso);
    }

    public List<Curso> listarComParametros(String area, Integer cargaHoraria) {
        String sql = "SELECT * FROM curso WHERE area_curso = :area AND carga_horaria_curso = :cargaHoraria";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("area", area)
                .addValue("cargaHoraria", cargaHoraria);

        return namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<>(Curso.class));
    }
}
