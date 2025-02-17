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

import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Aluno;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Atendimento;
import br.edu.iftm.tspi.pmvc.sistema_academico.domain.Coordenacao;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.AlunoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.AtendimentoRepository;
import br.edu.iftm.tspi.pmvc.sistema_academico.repository.CoordenacaoRepository;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoRepository atendimentoRepository;
    private final AlunoRepository alunoRepository;
    private final CoordenacaoRepository coordenacaoRepository;

    public AtendimentoController(AtendimentoRepository atendimentoRepository,
                                 AlunoRepository alunoRepository,
                                 CoordenacaoRepository coordenacaoRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.alunoRepository = alunoRepository;
        this.coordenacaoRepository = coordenacaoRepository;
    }

    @GetMapping
    public String listarAtendimentos(Model model) {
        model.addAttribute("atendimentos", atendimentoRepository.listar());
        return "atendimentos/lista";
    }

    @GetMapping("/novo")
    public String novoAtendimentoForm(Model model) {
        model.addAttribute("atendimento", new Atendimento(null, "", "", null, null));
        model.addAttribute("alunos", alunoRepository.listar());
        model.addAttribute("coordenacoes", coordenacaoRepository.listar());
        return "atendimentos/novo";
    }

    @PostMapping("/salvar")
    public String salvarAtendimento(@ModelAttribute Atendimento atendimento, RedirectAttributes redirectAttributes) {
        try {
            if (atendimento.getAssunto_atendimento() == null || atendimento.getAssunto_atendimento().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensagemErro", "O campo 'Assunto' é obrigatório.");
                return "redirect:/atendimentos/novo";
            }
    
            if (atendimento.getCd_aluno() != null && atendimento.getCd_aluno().getCd_aluno() != null) {
                Optional<Aluno> alunoOptional = alunoRepository.buscarPorCodigo(atendimento.getCd_aluno().getCd_aluno());
                if (alunoOptional.isPresent()) {
                    atendimento.setCd_aluno(alunoOptional.get());
                } else {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Aluno não encontrado.");
                    return "redirect:/atendimentos/novo";
                }
            }
    
            if (atendimento.getCd_coordenacao() != null && atendimento.getCd_coordenacao().getCd_coordenacao() != null) {
                Optional<Coordenacao> coordenacaoOptional = coordenacaoRepository.buscarPorCodigo(atendimento.getCd_coordenacao().getCd_coordenacao());
                if (coordenacaoOptional.isPresent()) {
                    atendimento.setCd_coordenacao(coordenacaoOptional.get());
                } else {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Coordenação não encontrada.");
                    return "redirect:/atendimentos/novo";
                }
            }
    
            atendimentoRepository.salvar(atendimento);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Atendimento salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar atendimento: " + e.getMessage());
        }
        return "redirect:/atendimentos";
    }



    @GetMapping("/editar/{cd_atendimento}")
    public String editarAtendimentoForm(@PathVariable Integer cd_atendimento, Model model, RedirectAttributes redirectAttributes) {
        try {
            Atendimento atendimento = atendimentoRepository.buscarPorCodigo(cd_atendimento);
            if (atendimento == null) {
                redirectAttributes.addFlashAttribute("mensagemErro", "Atendimento não encontrado.");
                return "redirect:/atendimentos";
            }
            model.addAttribute("atendimento", atendimento);
            model.addAttribute("alunos", alunoRepository.listar());
            model.addAttribute("coordenacoes", coordenacaoRepository.listar());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao carregar atendimento: " + e.getMessage());
            return "redirect:/atendimentos";
        }
        return "atendimentos/editar";
    }

    @PostMapping("/editar/{cd_atendimento}")
    public String atualizarAtendimento(@PathVariable Integer cd_atendimento, @ModelAttribute Atendimento atendimentoAtualizado, RedirectAttributes redirectAttributes) {
        try {
            atendimentoAtualizado.setCd_atendimento(cd_atendimento);

            if (atendimentoAtualizado.getAssunto_atendimento() == null || atendimentoAtualizado.getAssunto_atendimento().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensagemErro", "O campo 'Assunto' é obrigatório.");
                return "redirect:/atendimentos/editar/" + cd_atendimento;
            }

            if (atendimentoAtualizado.getCd_aluno() != null && atendimentoAtualizado.getCd_aluno().getCd_aluno() != null) {
                Optional<Aluno> alunoOptional = alunoRepository.buscarPorCodigo(atendimentoAtualizado.getCd_aluno().getCd_aluno());
                if (alunoOptional.isPresent()) {
                    atendimentoAtualizado.setCd_aluno(alunoOptional.get());
                } else {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Aluno não encontrado.");
                    return "redirect:/atendimentos/editar/" + cd_atendimento;
                }
            }

            if (atendimentoAtualizado.getCd_coordenacao() != null && atendimentoAtualizado.getCd_coordenacao().getCd_coordenacao() != null) {
                Optional<Coordenacao> coordenacaoOptional = coordenacaoRepository.buscarPorCodigo(atendimentoAtualizado.getCd_coordenacao().getCd_coordenacao());
                if (coordenacaoOptional.isPresent()) {
                    atendimentoAtualizado.setCd_coordenacao(coordenacaoOptional.get());
                } else {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Coordenação não encontrada.");
                    return "redirect:/atendimentos/editar/" + cd_atendimento;
                }
            }

            atendimentoRepository.atualizar(atendimentoAtualizado);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Atendimento atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar atendimento: " + e.getMessage());
        }
        return "redirect:/atendimentos";
    }

    @GetMapping("/excluir/{cd_atendimento}")
    public String excluirAtendimento(@PathVariable Integer cd_atendimento, RedirectAttributes redirectAttributes) {
        try {
            atendimentoRepository.excluir(cd_atendimento);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Atendimento excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir atendimento: " + e.getMessage());
        }
        return "redirect:/atendimentos";
    }
}