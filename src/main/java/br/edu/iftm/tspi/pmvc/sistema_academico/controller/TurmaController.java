package br.edu.iftm.tspi.pmvc.sistema_academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Turma;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.DisciplinaRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.ProfessorRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.TurmaRepository;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public TurmaController(TurmaRepository turmaRepository, DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }


    @GetMapping
    public String listarTurmas(Model model) {
        model.addAttribute("turmas", turmaRepository.listar());
        return "turmas/lista";
    }

    @GetMapping("/novo")
    public String novaTurmaForm(Model model) {
        model.addAttribute("turma", new Turma(null, "", null, null));
        model.addAttribute("disciplinas", disciplinaRepository.listar());
        model.addAttribute("professores", professorRepository.listar());
        return "turmas/novo";
    }
    @PostMapping("/salvar")
    public String salvarTurma(@ModelAttribute Turma turma) {
        if (turma.getCd_disciplina() != null && turma.getCd_disciplina().getCd_disciplina() != null) {
            turma.setCd_disciplina(disciplinaRepository.buscarPorCodigo(turma.getCd_disciplina().getCd_disciplina()));
        }

        if (turma.getCd_professor() != null && turma.getCd_professor().getCd_professor() != null) {
            professorRepository.buscarPorCodigo(turma.getCd_professor().getCd_professor())
                    .ifPresent(turma::setCd_professor);
        }
        turmaRepository.salvar(turma);
        return "redirect:/turmas";
    }


    @GetMapping("/editar/{cd_turma}")
    public String editarTurmaForm(@PathVariable Integer cd_turma, Model model) {
        Turma turma = turmaRepository.buscarPorCodigo(cd_turma);
        if (turma == null) {
            return "redirect:/turmas";
        }
        model.addAttribute("turma", turma);
        model.addAttribute("disciplinas", disciplinaRepository.listar());
        model.addAttribute("professores", professorRepository.listar());

        return "turmas/editar";
    }
    @PostMapping("/editar/{cd_turma}")
    public String atualizarTurma(@PathVariable Integer cd_turma, @ModelAttribute Turma turmaAtualizada) {
        turmaAtualizada.setCd_turma(cd_turma);
        if (turmaAtualizada.getCd_disciplina() != null && turmaAtualizada.getCd_disciplina().getCd_disciplina() != null) {
            turmaAtualizada.setCd_disciplina(disciplinaRepository.buscarPorCodigo(turmaAtualizada.getCd_disciplina().getCd_disciplina()));
        }

        if (turmaAtualizada.getCd_professor() != null && turmaAtualizada.getCd_professor().getCd_professor() != null) {
            professorRepository.buscarPorCodigo(turmaAtualizada.getCd_professor().getCd_professor())
                    .ifPresent(turmaAtualizada::setCd_professor);
        }

        turmaRepository.atualizar(turmaAtualizada);
        return "redirect:/turmas";
    }

    @GetMapping("/excluir/{cd_turma}")
    public String excluirTurma(@PathVariable Integer cd_turma, RedirectAttributes redirectAttributes) {
        try{
            turmaRepository.excluir(cd_turma);
            return "redirect:/turmas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o curso, pois ele está associado a uma matrícula.");
            return "redirect:/turmas";
        }
    }
}