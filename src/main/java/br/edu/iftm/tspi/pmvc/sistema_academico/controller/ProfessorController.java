package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Professor;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.ProfessorRepository;

@Controller
@RequestMapping("/professores")
public class ProfessorController {
    private final ProfessorRepository professorRepository;
    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    @GetMapping
    public String listarProfessores(Model model) {
        model.addAttribute("professores", professorRepository.listar());
        return "professores/lista";
    }
    @GetMapping("/novo")
    public String novoProfessorForm(Model model) {
        model.addAttribute("professor", new Professor(null, "", "", "", "", ""));
        return "professores/novo";
    }
    @PostMapping("/salvar")
    public String salvarProfessor(@ModelAttribute Professor professor) {
        professorRepository.salvar(professor);
        return "redirect:/professores";
    }
    @GetMapping("/editar/{cd_professor}")
    public String editarProfessorForm(@PathVariable Integer cd_professor, Model model) {
        Professor professor = professorRepository.buscarPorCodigo(cd_professor).orElse(null);
        if (professor == null) {
            return "redirect:/professores";
        }
        model.addAttribute("professor", professor);
        return "professores/editar";
    }
    @PostMapping("/editar/{cd_professor}")
    public String atualizarProfessor(@PathVariable Integer cd_professor, @ModelAttribute Professor professorAtualizado) {
        professorAtualizado.setCd_professor(cd_professor);
        professorRepository.atualizar(professorAtualizado);
        return "redirect:/professores";
    }
    @GetMapping("/excluir/{cd_professor}")
    public String excluirProfessor(@PathVariable Integer cd_professor, RedirectAttributes redirectAttributes) {
        try {
            professorRepository.excluir(cd_professor);
            return "redirect:/professores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o professor, pois ele está associado a turma ou coordenação.");
            return "redirect:/professores";
        }
    }
}
