package com.porfolio.backend.Repository;

import com.porfolio.backend.Entity.Competencias;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompetenciasRepo extends JpaRepository<Competencias, Integer>{
       public Optional<Competencias> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}

