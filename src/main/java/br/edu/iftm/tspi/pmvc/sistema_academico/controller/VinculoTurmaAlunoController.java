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

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.VinculoTurmaAluno;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.AlunoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.TurmaRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.VinculoTurmaAlunoRepository;

@Controller
@RequestMapping("/vinculo_turma_aluno")
public class VinculoTurmaAlunoController {

    private final VinculoTurmaAlunoRepository vinculoAlunoTurmaRepository;
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;

    public VinculoTurmaAlunoController(VinculoTurmaAlunoRepository vinculoAlunoTurmaRepository,
                                       TurmaRepository turmaRepository,
                                       AlunoRepository alunoRepository) {
        this.vinculoAlunoTurmaRepository = vinculoAlunoTurmaRepository;
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public String listarVinculosAlunosTurmas(Model model) {
        List<VinculoTurmaAluno> vinculos = vinculoAlunoTurmaRepository.listar();
        model.addAttribute("vinculo_turma_aluno", vinculos);
        return "vinculo_turma_aluno/lista";
    }

    @GetMapping("/novo")
    public String novoVinculoAlunoTurmaForm(Model model) {
        model.addAttribute("vinculo_turma_aluno", new VinculoTurmaAluno());
        model.addAttribute("turmas", turmaRepository.listar());
        model.addAttribute("alunos", alunoRepository.listar());
        return "vinculo_turma_aluno/novo";
    }

    @PostMapping("/salvar")
    public String salvarVinculoAlunoTurma(@ModelAttribute VinculoTurmaAluno vinculoAlunoTurma, RedirectAttributes redirectAttributes) {
        try {
            if (vinculoAlunoTurma.getCd_turma() != null && vinculoAlunoTurma.getCd_turma().getCd_turma() != null) {
                vinculoAlunoTurma.setCd_turma(turmaRepository.buscarPorCodigo(vinculoAlunoTurma.getCd_turma().getCd_turma()));
            }
            if (vinculoAlunoTurma.getCd_aluno() != null && vinculoAlunoTurma.getCd_aluno().getCd_aluno() != null) {
                alunoRepository.buscarPorCodigo(vinculoAlunoTurma.getCd_aluno().getCd_aluno())
                        .ifPresent(vinculoAlunoTurma::setCd_aluno);
            }
            vinculoAlunoTurmaRepository.salvar(vinculoAlunoTurma);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Vínculo de aluno com turma salvo com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao salvar o vínculo.");
        }
        return "redirect:/vinculo_turma_aluno";
    }

    @GetMapping("/editar/{cd_vinculo}")
    public String editarVinculoAlunoTurmaForm(@PathVariable Integer cd_vinculo, Model model, RedirectAttributes redirectAttributes) {
        VinculoTurmaAluno vinculoAlunoTurma = vinculoAlunoTurmaRepository.buscarPorCodigo(cd_vinculo);
        if (vinculoAlunoTurma == null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Vínculo não encontrado.");
            return "redirect:/vinculo_turma_aluno";
        }
        model.addAttribute("vinculo_turma_aluno", vinculoAlunoTurma);
        model.addAttribute("turmas", turmaRepository.listar());
        model.addAttribute("alunos", alunoRepository.listar());
        return "vinculo_turma_aluno/editar";
    }

    @PostMapping("/editar/{cd_vinculo}")
    public String atualizarVinculoAlunoTurma(@PathVariable Integer cd_vinculo,
                                             @ModelAttribute VinculoTurmaAluno vinculoAlunoTurmaAtualizado, RedirectAttributes redirectAttributes) {
        try {
            vinculoAlunoTurmaAtualizado.setCd_vinculo(cd_vinculo);

            if (vinculoAlunoTurmaAtualizado.getCd_turma() != null && vinculoAlunoTurmaAtualizado.getCd_turma().getCd_turma() != null) {
                vinculoAlunoTurmaAtualizado.setCd_turma(turmaRepository.buscarPorCodigo(vinculoAlunoTurmaAtualizado.getCd_turma().getCd_turma()));
            }

            if (vinculoAlunoTurmaAtualizado.getCd_aluno() != null && vinculoAlunoTurmaAtualizado.getCd_aluno().getCd_aluno() != null) {
                alunoRepository.buscarPorCodigo(vinculoAlunoTurmaAtualizado.getCd_aluno().getCd_aluno())
                        .ifPresent(vinculoAlunoTurmaAtualizado::setCd_aluno);
            }

            vinculoAlunoTurmaRepository.atualizar(vinculoAlunoTurmaAtualizado);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Vínculo atualizado com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao atualizar o vínculo.");
        }
        return "redirect:/vinculo_turma_aluno";
    }

    @GetMapping("/excluir/{cd_vinculo}")
    public String excluirVinculoAlunoTurma(@PathVariable Integer cd_vinculo, RedirectAttributes redirectAttributes) {
        try {
            vinculoAlunoTurmaRepository.excluir(cd_vinculo);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Vínculo excluído com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o vínculo, pois ele está associado a outra classe.");
        }
        return "redirect:/vinculo_turma_aluno";
    }
}