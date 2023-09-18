package alura.java.projetocurso001.Domain.medico;

import alura.java.projetocurso001.Domain.dto.DadosAtualizacaoMedico;
import alura.java.projetocurso001.Domain.dto.DadosCadastroMedico;
import alura.java.projetocurso001.Domain.endereco.Endereco;
import alura.java.projetocurso001.Domain.eum.Especialidade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Medico
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // indica que a classe Endereco é uma classe embutida na classe Medico, ou seja,
    // não é uma entidade por si
    // só e sim um conjunto de atributos que serão embutidos na tabela Medico
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizar(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizar(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
