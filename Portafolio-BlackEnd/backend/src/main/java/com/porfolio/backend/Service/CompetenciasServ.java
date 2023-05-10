package com.porfolio.backend.Service;

import com.porfolio.backend.Entity.Certificaciones;
import com.porfolio.backend.Entity.Competencias;
import com.porfolio.backend.Repository.CompetenciasRepo;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CompetenciasServ {
    @Autowired
    
    CompetenciasRepo competenciasR;
    
    public List<Competencias>list(){
      return competenciasR.findAll();
  }  
  public Optional <Competencias> getOne(int id){
      return competenciasR.findById(id);
  }
   public Optional <Competencias> getByNombre(String nombre){
       return competenciasR.findByNombre(nombre);
   }
  public void save(Competencias competencia){
      competenciasR.save(competencia);
  }
  public void delete(int id){
      competenciasR.deleteById(id);
  }
  public boolean existsById(int id){
      return competenciasR.existsById(id); 
  }
  public boolean existsByNombre(String nombre){
      return competenciasR.existsByNombre(nombre);
      
  }
}
