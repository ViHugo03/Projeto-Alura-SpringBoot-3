package alura.java.projetocurso001.controller;


import alura.java.projetocurso001.Domain.dto.DadosAtualizacaoMedico;
import alura.java.projetocurso001.Domain.dto.DadosCadastroMedico;
import alura.java.projetocurso001.Domain.dto.DadosListagemMedico;
import alura.java.projetocurso001.Domain.medico.Medico;
import alura.java.projetocurso001.Domain.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizarMedico(@PathVariable Long id, @RequestBody DadosAtualizacaoMedico dados) {
        Optional<Medico> medico = Optional.of(medicoRepository.getReferenceById(id));
        medico.ifPresent(m -> m.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void removerMedico(@PathVariable Long id) {
        Optional<Medico> medico = Optional.of(medicoRepository.getReferenceById(id));
        medico.ifPresent(m -> m.excluir());
    }
}
