package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Disciplina;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.CursoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.DisciplinaRepository;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;
    private final CursoRepository cursoRepository;

    public DisciplinaController(DisciplinaRepository disciplinaRepository, CursoRepository cursoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public String listarDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.listar());
        return "disciplinas/lista";
    }

    @GetMapping("/novo")
    public String novaDisciplinaForm(Model model) {
        model.addAttribute("disciplina", new Disciplina(null, "", "", "", null, null));
        model.addAttribute("cursos", cursoRepository.listar());
        return "disciplinas/novo";
    }

    @PostMapping("/salvar")
    public String salvarDisciplina(@ModelAttribute Disciplina disciplina) {
        if (disciplina.getCd_curso() != null && disciplina.getCd_curso().getCd_curso() != null) {
            var cursoOptional = cursoRepository.buscarPorCodigo(disciplina.getCd_curso().getCd_curso());
            cursoOptional.ifPresent(disciplina::setCd_curso);
        } else {
            disciplina.setCd_curso(null);
        }

        disciplinaRepository.salvar(disciplina);
        return "redirect:/disciplinas";
    }
    @GetMapping("/editar/{cd_disciplina}")
    public String editarDisciplinaForm(@PathVariable Integer cd_disciplina, Model model) {
        Optional<Disciplina> disciplinaOptional = Optional.ofNullable(disciplinaRepository.buscarPorCodigo(cd_disciplina));
        
        if (!disciplinaOptional.isPresent()) {
            return "redirect:/disciplinas";  
        }
        
        model.addAttribute("disciplina", disciplinaOptional.get());
        model.addAttribute("cursos", cursoRepository.listar());
        return "disciplinas/editar";
    }


    @PostMapping("/editar/{cd_disciplina}")
    public String atualizarDisciplina(@PathVariable Integer cd_disciplina, @ModelAttribute Disciplina disciplinaAtualizada) {
        disciplinaAtualizada.setCd_disciplina(cd_disciplina);
        

        if (disciplinaAtualizada.getCd_curso() != null && disciplinaAtualizada.getCd_curso().getCd_curso() != null) {
            var cursoOptional = cursoRepository.buscarPorCodigo(disciplinaAtualizada.getCd_curso().getCd_curso());
            cursoOptional.ifPresent(disciplinaAtualizada::setCd_curso);
        }
        
        disciplinaRepository.atualizar(disciplinaAtualizada);
        return "redirect:/disciplinas";
    }

    @GetMapping("/excluir/{cd_disciplina}")
    public String excluirDisciplina(@PathVariable Integer cd_disciplina, RedirectAttributes redirectAttributes) {
        try {
            disciplinaRepository.excluir(cd_disciplina);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Disciplina excluída com sucesso!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/disciplinas";
    }
}
