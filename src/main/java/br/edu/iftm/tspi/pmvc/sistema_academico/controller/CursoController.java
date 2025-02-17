package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Curso;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.CursoRepository;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private CursoRepository cursoRepository;


    CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoRepository.listar());
        return "cursos/lista";
    }

    @GetMapping("/novo")
    public String novoCursoForm(Model model) {
        model.addAttribute("curso", new Curso(null, "", "", null, ""));
        return "cursos/novo";
    }

    @PostMapping("/salvar")
    public String salvarCurso(@ModelAttribute Curso curso) {
        cursoRepository.salvar(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{cd_curso}")
    public String editarCursoForm(@PathVariable Integer cd_curso, Model model) {
        var cursoOptional = cursoRepository.buscarPorCodigo(cd_curso);
        if (cursoOptional.isEmpty()) {
            return "redirect:/cursos";
        }
        model.addAttribute("curso", cursoOptional.get());
        return "cursos/editar";
    }

    @PostMapping("/editar/{cd_curso}")
    public String atualizarCurso(@PathVariable Integer cd_curso, @ModelAttribute Curso cursoAtualizado) {
        cursoAtualizado.setCd_curso(cd_curso);
        cursoRepository.atualizar(cursoAtualizado);
        return "redirect:/cursos";
    }

    @GetMapping("/excluir/{cd_curso}")
    public String excluirCurso(@PathVariable Integer cd_curso, RedirectAttributes redirectAttributes) {
        try {
            cursoRepository.excluir(cd_curso);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso excluído com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o curso, pois ele está associado a outras entidades.");
        }
        return "redirect:/cursos";
    }
}
