package br.com.fiap.davinciEnergy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "USUARIO")
@AllArgsConstructor @NoArgsConstructor
public class Usuario {

    @Id
    @Column(name="usuario_id")
    @GeneratedValue
    private Long id;

    @Email
    @NotNull(message = "E-mail invalido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Senha invalida")
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name="role_id") )
    public Set<Role> roles;

    public Usuario(String email, String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
