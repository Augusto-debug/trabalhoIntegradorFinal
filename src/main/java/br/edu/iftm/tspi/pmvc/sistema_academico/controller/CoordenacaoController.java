package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Coordenacao;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.CoordenacaoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.CursoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.ProfessorRepository;

@Controller
@RequestMapping("/coordenacao")
public class CoordenacaoController {

    private final CoordenacaoRepository coordenacaoRepository;
    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;

    public CoordenacaoController(CoordenacaoRepository coordenacaoRepository,
                                CursoRepository cursoRepository,
                                ProfessorRepository professorRepository) {
        this.coordenacaoRepository = coordenacaoRepository;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public String listarCoordenacoes(Model model) {
        List<Coordenacao> coordenacoes = coordenacaoRepository.listar();
        
        
        coordenacoes.forEach(coordenacao -> {
            
            if (coordenacao.getCd_curso() != null) {
                var curso = cursoRepository.buscarPorCodigo(coordenacao.getCd_curso()).orElse(null);
                coordenacao.setCurso(curso);
            }
            
            if (coordenacao.getCd_professor() != null) {
                var professor = professorRepository.buscarPorCodigo(coordenacao.getCd_professor()).orElse(null);
                coordenacao.setProfessor(professor);
            }
        });
        
        model.addAttribute("coordenacoes", coordenacoes);
        return "coordenacao/lista";
    }
    

    @GetMapping("/novo")
    public String novaCoordenacaoForm(Model model) {
        model.addAttribute("coordenacao", new Coordenacao(null, "", "", null, null, null, null));
        model.addAttribute("cursos", cursoRepository.listar());
        model.addAttribute("professores", professorRepository.listar());
        return "coordenacao/novo";
    }

    @PostMapping("/salvar")
    public String salvarCoordenacao(@ModelAttribute Coordenacao coordenacao) {
        if (coordenacao.getCd_curso() != null) {
            var cursoOptional = cursoRepository.buscarPorCodigo(coordenacao.getCd_curso());
            cursoOptional.ifPresent(coordenacao::setCurso);
        }
        if (coordenacao.getCd_professor() != null) {
            var professorOptional = professorRepository.buscarPorCodigo(coordenacao.getCd_professor());
            professorOptional.ifPresent(coordenacao::setProfessor);
        }
        coordenacaoRepository.salvar(coordenacao);
        return "redirect:/coordenacao";
    }


    @GetMapping("/editar/{cd_coordenacao}")
    public String editarCoordenacaoForm(@PathVariable Integer cd_coordenacao, Model model) {
        Coordenacao coordenacao = coordenacaoRepository.buscarPorCodigo(cd_coordenacao).orElse(null);
        if (coordenacao == null) {
            return "redirect:/coordenacao";
        }
        model.addAttribute("coordenacao", coordenacao);
        model.addAttribute("cursos", cursoRepository.listar());
        model.addAttribute("professores", professorRepository.listar());
        return "coordenacao/editar";
    }
    
    @PostMapping("/editar/{cd_coordenacao}")
    public String atualizarCoordenacao(@PathVariable Integer cd_coordenacao, @ModelAttribute Coordenacao coordenacaoAtualizada) {
        coordenacaoAtualizada.setCd_coordenacao(cd_coordenacao);
    
        if (coordenacaoAtualizada.getCd_curso() != null) {
            var cursoOptional = cursoRepository.buscarPorCodigo(coordenacaoAtualizada.getCd_curso());
            if (cursoOptional.isPresent()) {
                coordenacaoAtualizada.setCurso(cursoOptional.get());
            } else {
                throw new RuntimeException("Curso não encontrado para o código: " + coordenacaoAtualizada.getCd_curso());
            }
        }
    
        if (coordenacaoAtualizada.getCd_professor() != null) {
            var professorOptional = professorRepository.buscarPorCodigo(coordenacaoAtualizada.getCd_professor());
            if (professorOptional.isPresent()) {
                coordenacaoAtualizada.setProfessor(professorOptional.get());
            } else {
                throw new RuntimeException("Professor não encontrado para o código: " + coordenacaoAtualizada.getCd_professor());
            }
        }
    
        coordenacaoRepository.atualizar(coordenacaoAtualizada);
        return "redirect:/coordenacao";
    }
    
    @GetMapping("/excluir/{cd_coordenacao}")
    public String excluirCoordenacao(@PathVariable Integer cd_coordenacao, RedirectAttributes redirectAttributes) {
        try {
            coordenacaoRepository.excluir(cd_coordenacao);
            return "redirect:/coordenacao";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir a coordenação, pois ela está associada a algum atendimento.");
            return "redirect:/coordenacao";
        }
    }
}