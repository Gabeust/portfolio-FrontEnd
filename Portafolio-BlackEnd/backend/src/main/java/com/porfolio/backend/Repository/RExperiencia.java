

package com.porfolio.backend.Repository;

import com.porfolio.backend.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperiencia  extends JpaRepository <Experiencia, Integer>{
   public Optional<Experiencia> findByEmpresa(String empresa);
   public boolean existsByEmpresa(String empresa);
    
}
