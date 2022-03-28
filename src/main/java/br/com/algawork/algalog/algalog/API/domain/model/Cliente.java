package br.com.algawork.algalog.algalog.API.domain.model;

import br.com.algawork.algalog.algalog.API.domain.ValidationGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity

public class Cliente {

    @NotNull(groups = ValidationGroup.ClienteId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @Email
    @Size(max = 255)
    private String email;

    @Column(name = "fone")
    @Size(max = 20)
    @NotBlank
    private String telefone;

}
