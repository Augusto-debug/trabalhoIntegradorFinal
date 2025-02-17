package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Disciplina;

@Repository
public class DisciplinaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CursoRepository cursoRepository;

    public DisciplinaRepository(JdbcTemplate jdbcTemplate, CursoRepository cursoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.cursoRepository = cursoRepository;
    }

    public List<Disciplina> listar() {
        String sql = "SELECT * FROM disciplina";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setCd_disciplina(rs.getInt("cd_disciplina"));
            disciplina.setNome_disciplina(rs.getString("nome_disciplina"));
            disciplina.setSigla_disciplina(rs.getString("sigla_disciplina"));
            disciplina.setEmenta_disciplina(rs.getString("ementa_disciplina"));
            disciplina.setCarga_horaria_disciplina(rs.getInt("carga_horaria_disciplina"));

            Integer cd_curso = rs.getInt("cd_curso");
            if (cd_curso != null) {
                disciplina.setCd_curso(cursoRepository.buscarPorCodigo(cd_curso).orElse(null));
            }

            return disciplina;
        });
    }

    public Disciplina buscarPorCodigo(Integer cd_disciplina) {
        String sql = "SELECT * FROM disciplina WHERE cd_disciplina = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setCd_disciplina(rs.getInt("cd_disciplina"));
            disciplina.setNome_disciplina(rs.getString("nome_disciplina"));
            disciplina.setSigla_disciplina(rs.getString("sigla_disciplina"));
            disciplina.setEmenta_disciplina(rs.getString("ementa_disciplina"));
            disciplina.setCarga_horaria_disciplina(rs.getInt("carga_horaria_disciplina"));

            Integer cd_curso = rs.getInt("cd_curso");
            if (cd_curso != null) {
                disciplina.setCd_curso(cursoRepository.buscarPorCodigo(cd_curso).orElse(null));
            }

            return disciplina;
        }, cd_disciplina);
    }

    public void salvar(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (nome_disciplina, sigla_disciplina, ementa_disciplina, carga_horaria_disciplina, cd_curso) VALUES (?, ?, ?, ?, ?)";
        
        Integer cd_curso = (disciplina.getCd_curso() != null) ? disciplina.getCd_curso().getCd_curso() : null;
    
        jdbcTemplate.update(sql, disciplina.getNome_disciplina(), disciplina.getSigla_disciplina(), disciplina.getEmenta_disciplina(), disciplina.getCarga_horaria_disciplina(), cd_curso);
    }

    public void atualizar(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET nome_disciplina = ?, sigla_disciplina = ?, ementa_disciplina = ?, carga_horaria_disciplina = ?, cd_curso = ? WHERE cd_disciplina = ?";
        jdbcTemplate.update(sql, disciplina.getNome_disciplina(), disciplina.getSigla_disciplina(), disciplina.getEmenta_disciplina(), disciplina.getCarga_horaria_disciplina(), disciplina.getCd_curso().getCd_curso(), disciplina.getCd_disciplina());
    }

    public void excluir(Integer cd_disciplina) {
        try {
            String sqlExcluir = "DELETE FROM disciplina WHERE cd_disciplina = ?";
            jdbcTemplate.update(sqlExcluir, cd_disciplina);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir a disciplina, pois ela está associada a uma matrícula.");
        }
    }
}