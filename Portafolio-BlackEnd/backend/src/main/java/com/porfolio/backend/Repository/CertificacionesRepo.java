package com.porfolio.backend.Repository;

import com.porfolio.backend.Entity.Certificaciones;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificacionesRepo extends JpaRepository<Certificaciones,Integer>{
    public Optional<Certificaciones> findByTitulo(String titulo);
    public boolean existsByTitulo(String titulo);
    
}
