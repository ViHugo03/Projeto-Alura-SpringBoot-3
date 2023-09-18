package alura.java.projetocurso001.Domain.dto;

import alura.java.projetocurso001.Domain.eum.Especialidade;
import alura.java.projetocurso001.Domain.medico.Medico;

public record DadosListagemMedico(Long id,String nome,String email, String crm, Especialidade especialidade ) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
