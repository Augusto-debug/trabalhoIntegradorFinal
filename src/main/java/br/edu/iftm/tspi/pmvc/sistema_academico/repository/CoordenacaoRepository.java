package br.edu.iftm.tspi.pmvc.sistema_academico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Coordenacao;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Curso;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Professor;

@Repository
public class CoordenacaoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;

    public CoordenacaoRepository(JdbcTemplate jdbcTemplate, CursoRepository cursoRepository, ProfessorRepository professorRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
    }

    public List<Coordenacao> listar() {
        String sql = "SELECT cd_coordenacao, tel_coordenacao, email_coordenacao, dt_inicio, dt_fim, cd_curso, cd_professor FROM Coordenacao";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Coordenacao coordenacao = new Coordenacao();
            coordenacao.setCd_coordenacao(rs.getInt("cd_coordenacao"));
            coordenacao.setTel_coordenacao(rs.getString("tel_coordenacao"));
            coordenacao.setEmail_coordenacao(rs.getString("email_coordenacao"));
    
            java.sql.Date sqlDtInicio = rs.getDate("dt_inicio");
            coordenacao.setDt_inicio(sqlDtInicio != null ? sqlDtInicio.toLocalDate() : null);
    
            java.sql.Date sqlDtFim = rs.getDate("dt_fim");
            coordenacao.setDt_fim(sqlDtFim != null ? sqlDtFim.toLocalDate() : null);
    
            coordenacao.setCd_curso(rs.getInt("cd_curso"));
            coordenacao.setCd_professor(rs.getInt("cd_professor"));
    
            Optional<Curso> cursoOptional = cursoRepository.buscarPorCodigo(rs.getInt("cd_curso"));
            Optional<Professor> professorOptional = professorRepository.buscarPorCodigo(rs.getInt("cd_professor"));
    
            if (cursoOptional.isPresent()) {
                coordenacao.setCurso(cursoOptional.get());
            } else {
                throw new RuntimeException("Curso não encontrado para o código: " + rs.getInt("cd_curso"));
            }
    
            if (professorOptional.isPresent()) {
                coordenacao.setProfessor(professorOptional.get());
            } else {
                throw new RuntimeException("Professor não encontrado para o código: " + rs.getInt("cd_professor"));
            }
    
            return coordenacao;
        });
    }
    
    public Optional<Coordenacao> buscarPorCodigo(Integer cd_coordenacao) {
        String sql = "SELECT cd_coordenacao, tel_coordenacao, email_coordenacao, dt_inicio, dt_fim, cd_curso, cd_professor FROM Coordenacao WHERE cd_coordenacao = ?";
        try {
            Coordenacao coordenacao = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Coordenacao c = new Coordenacao();
                c.setCd_coordenacao(rs.getInt("cd_coordenacao"));
                c.setTel_coordenacao(rs.getString("tel_coordenacao"));
                c.setEmail_coordenacao(rs.getString("email_coordenacao"));
    
                java.sql.Date sqlDtInicio = rs.getDate("dt_inicio");
                c.setDt_inicio(sqlDtInicio != null ? sqlDtInicio.toLocalDate() : null);
    
                java.sql.Date sqlDtFim = rs.getDate("dt_fim");
                c.setDt_fim(sqlDtFim != null ? sqlDtFim.toLocalDate() : null);
    
                c.setCd_curso(rs.getInt("cd_curso"));
                c.setCd_professor(rs.getInt("cd_professor"));
    
                Optional<Curso> cursoOptional = cursoRepository.buscarPorCodigo(rs.getInt("cd_curso"));
                Optional<Professor> professorOptional = professorRepository.buscarPorCodigo(rs.getInt("cd_professor"));
    
                if (cursoOptional.isPresent()) {
                    c.setCurso(cursoOptional.get());
                } else {
                    throw new RuntimeException("Curso não encontrado para o código: " + rs.getInt("cd_curso"));
                }
    
                if (professorOptional.isPresent()) {
                    c.setProfessor(professorOptional.get());
                } else {
                    throw new RuntimeException("Professor não encontrado para o código: " + rs.getInt("cd_professor"));
                }
    
                return c;
            }, cd_coordenacao);
    
            return Optional.ofNullable(coordenacao);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    

    public void salvar(Coordenacao coordenacao) {
        String sql = "INSERT INTO Coordenacao (tel_coordenacao, email_coordenacao, dt_inicio, dt_fim, cd_curso, cd_professor) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, coordenacao.getTel_coordenacao(), coordenacao.getEmail_coordenacao(), coordenacao.getDt_inicio(), coordenacao.getDt_fim(), coordenacao.getCd_curso(), coordenacao.getCd_professor());
    }

    public void atualizar(Coordenacao coordenacao) {
        String sql = "UPDATE Coordenacao SET tel_coordenacao = ?, email_coordenacao = ?, dt_inicio = ?, dt_fim = ?, cd_curso = ?, cd_professor = ? WHERE cd_coordenacao = ?";
        jdbcTemplate.update(sql, coordenacao.getTel_coordenacao(), coordenacao.getEmail_coordenacao(), coordenacao.getDt_inicio(), coordenacao.getDt_fim(), coordenacao.getCd_curso(), coordenacao.getCd_professor(), coordenacao.getCd_coordenacao());
    }

    public void excluir(Integer cd_coordenacao) {
        String sql = "DELETE FROM Coordenacao WHERE cd_coordenacao = ?";
        jdbcTemplate.update(sql, cd_coordenacao);
    }
}