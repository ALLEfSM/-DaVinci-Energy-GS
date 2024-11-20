package br.com.fiap.davinciEnergy.repository;

import br.com.fiap.davinciEnergy.model.Medidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidorRepository extends JpaRepository<Medidor, Long> {
}
