package br.inter.dti.gmoraes.remittance.domain.model;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioCommand;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_usuario_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_usuario_cpf", columnNames = "cpf"),
                @UniqueConstraint(name = "uk_usuario_cnpj", columnNames = "cnpj")
        }
)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    private String cpf;

    private String cnpj;

    @Embedded
    private Carteira carteira;

    public Usuario() {}

    public Usuario(
            String nomeCompleto,
            String email,
            String senha,
            TipoUsuario tipoUsuario,
            String cpf,
            String cnpj,
            Carteira carteira
    ) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.carteira = carteira;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public static Usuario criar(CriarUsuarioCommand command) {
        return new Usuario(
                command.nomeCompleto(),
                command.email(),
                command.senha(),
                command.tipoUsuario(),
                command.cpf(),
                command.cnpj(),
                new Carteira(BigDecimal.ZERO, BigDecimal.ZERO)
        );
    }
}
