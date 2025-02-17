package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Aluno;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.AlunoRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoRepository.listar());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novoAlunoForm(Model model) {
        model.addAttribute("aluno", new Aluno(null, "", "", "", "", "", LocalDate.now(), ""));
        return "alunos/novo";
    }

    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        alunoRepository.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{cd_aluno}")
    public String editarAlunoForm(@PathVariable Integer cd_aluno, Model model) {
        Optional<Aluno> alunoOpt = alunoRepository.buscarPorCodigo(cd_aluno);
        if (alunoOpt.isEmpty()) {
            return "redirect:/alunos";
        }
        model.addAttribute("aluno", alunoOpt.get());
        return "alunos/editar";
    }
    

    @PostMapping("/editar/{cd_aluno}")
    public String atualizarAluno(@PathVariable Integer cd_aluno, @ModelAttribute Aluno alunoAtualizado) {
        alunoAtualizado.setCd_aluno(cd_aluno);
        alunoRepository.atualizar(alunoAtualizado);
        return "redirect:/alunos";
    }

    @GetMapping("/excluir/{cd_aluno}")
    public String excluirAluno(@PathVariable Integer cd_aluno,  RedirectAttributes redirectAttributes) {
        try {
            alunoRepository.excluir(cd_aluno);
            return "redirect:/alunos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o aluno, pois ele está associado a uma matrícula.");
            return "redirect:/alunos";
        }
    }
}