
package com.porfolio.backend.Repository;

import com.porfolio.backend.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    public Optional<Persona> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);

    public Optional<Persona> findById(int id);

    public void deleteById(int id);

    public boolean existsById(int id);
}