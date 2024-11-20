package br.com.fiap.davinciEnergy.repository;

import br.com.fiap.davinciEnergy.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {


    Optional<Dispositivo> findById(Double whatts);
}
