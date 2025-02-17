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

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Matricula;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.AlunoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.CursoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.MatriculaRepository;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public MatriculaController(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public String listarMatriculas(Model model) {
        model.addAttribute("matriculas", matriculaRepository.listar());
        return "matriculas/lista";
    }

    @GetMapping("/novo")
    public String novaMatriculaForm(Model model) {
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("alunos", alunoRepository.listar());
        model.addAttribute("cursos", cursoRepository.listar());
        return "matriculas/novo";
    }

    @PostMapping("/salvar")
    public String salvarMatricula(@ModelAttribute Matricula matricula) {
        if (matricula.getCd_aluno() != null) {
            var alunoOptional = alunoRepository.buscarPorCodigo(matricula.getCd_aluno());
            alunoOptional.ifPresent(matricula::setAluno);
        }

        if (matricula.getCd_curso() != null) {
            var cursoOptional = cursoRepository.buscarPorCodigo(matricula.getCd_curso());
            cursoOptional.ifPresent(matricula::setCurso);
        }

        matriculaRepository.salvar(matricula);
        return "redirect:/matriculas";
    }

    @GetMapping("/editar/{cd_matricula}")
    public String editarMatriculaForm(@PathVariable Integer cd_matricula, Model model) {
        Optional<Matricula> matriculaOptional = matriculaRepository.buscarPorCodigo(cd_matricula);

        if (matriculaOptional.isEmpty()) {
            return "redirect:/matriculas";
        }

        model.addAttribute("matricula", matriculaOptional.get());
        model.addAttribute("alunos", alunoRepository.listar());
        model.addAttribute("cursos", cursoRepository.listar());
        return "matriculas/editar";
    }

    @PostMapping("/editar/{cd_matricula}")
    public String atualizarMatricula(@PathVariable Integer cd_matricula, @ModelAttribute Matricula matriculaAtualizada) {
        matriculaAtualizada.setCd_matricula(cd_matricula);

        if (matriculaAtualizada.getCd_aluno() != null) {
            var alunoOptional = alunoRepository.buscarPorCodigo(matriculaAtualizada.getCd_aluno());
            alunoOptional.ifPresent(matriculaAtualizada::setAluno);
        }

        if (matriculaAtualizada.getCd_curso() != null) {
            var cursoOptional = cursoRepository.buscarPorCodigo(matriculaAtualizada.getCd_curso());
            cursoOptional.ifPresent(matriculaAtualizada::setCurso);
        }

        matriculaRepository.atualizar(matriculaAtualizada);
        return "redirect:/matriculas";
    }

    @GetMapping("/excluir/{cd_matricula}")
    public String excluirMatricula(@PathVariable Integer cd_matricula, RedirectAttributes redirectAttributes) {
        try {
            matriculaRepository.excluir(cd_matricula);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Matrícula excluída com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir a matrícula, pois ela está associada a outras entidades.");
        }
        return "redirect:/matriculas";
    }
}