package alura.java.projetocurso001.Domain.dto;

import alura.java.projetocurso001.Domain.endereco.Endereco;

public record DadosAtualizacaoMedico(

        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
