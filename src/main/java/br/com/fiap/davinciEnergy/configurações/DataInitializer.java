package br.com.fiap.davinciEnergy.configurações;

import br.com.fiap.davinciEnergy.model.Role;
import br.com.fiap.davinciEnergy.model.Usuario;
import br.com.fiap.davinciEnergy.repository.RoleRepository;
import br.com.fiap.davinciEnergy.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                adminRole.setLabel("Admin");
                roleRepository.save(adminRole);
            }

            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                userRole.setLabel("User");
                roleRepository.save(userRole);
            }

            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("Admin01"));
                admin.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));
                userRepository.save(admin);
            }

            if (userRepository.findByEmail("teste@gmail.com").isEmpty()) {
                Usuario user = new Usuario();
                user.setEmail("teste@gmail.com");
                user.setPassword(passwordEncoder.encode("Teste01"));
                user.setRoles(Set.of(roleRepository.findByName("ROLE_USER").get()));
                userRepository.save(user);
            }
        };
    }
}