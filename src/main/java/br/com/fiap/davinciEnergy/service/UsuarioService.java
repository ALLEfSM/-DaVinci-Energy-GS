package br.com.fiap.davinciEnergy.service;

import br.com.fiap.davinciEnergy.model.Role;
import br.com.fiap.davinciEnergy.model.Usuario;
import br.com.fiap.davinciEnergy.repository.RoleRepository;
import br.com.fiap.davinciEnergy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void salvar(String username, String password, List<String> roles) {
        Set<Role> listaRoles = new HashSet<>();
        for (String name : roles) {
            roleRepository.findByName(name).ifPresent(listaRoles::add);
        }
        Usuario usuario = new Usuario(username, password, listaRoles);
        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));
        Set<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                authorities);
    }
}
