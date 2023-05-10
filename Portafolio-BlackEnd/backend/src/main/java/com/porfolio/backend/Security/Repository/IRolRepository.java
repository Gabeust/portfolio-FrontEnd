
package com.porfolio.backend.Security.Repository;

import com.porfolio.backend.Security.Entity.Rol;
import com.porfolio.backend.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRolRepository extends JpaRepository<Rol, Integer>  {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
    
