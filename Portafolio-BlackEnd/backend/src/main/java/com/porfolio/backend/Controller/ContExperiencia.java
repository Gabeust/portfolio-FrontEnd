package com.porfolio.backend.Controller;

import com.porfolio.backend.Dto.DtoExperiencia;
import com.porfolio.backend.Entity.Experiencia;
import com.porfolio.backend.Security.Controller.Mensaje;
import com.porfolio.backend.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200/")
public class ContExperiencia {

    @Autowired
    SExperiencia sExperiencia;
    

   @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
     @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
            Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
      
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExp) {
        if (StringUtils.isBlank(dtoExp.getEmpresa())){
            return new ResponseEntity(new Mensaje("la empresa es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (sExperiencia.existsByEmpresa(dtoExp.getEmpresa())) {
            return new ResponseEntity(new Mensaje("Experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = new Experiencia(
                dtoExp.getEmpresa(),
                dtoExp.getDescripcion(),
                dtoExp.getIngreso(),
                dtoExp.getEgreso());
        
        sExperiencia.save(experiencia);
      
        

        return new ResponseEntity(new Mensaje("Experiencia agregada con exito"), HttpStatus.OK);

    }
   
     @PutMapping("/editar/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoExp) {
        if (!sExperiencia.existsById(id)) 
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if (sExperiencia.existsByEmpresa(dtoExp.getEmpresa()) && sExperiencia.getByEmpresa(dtoExp.getEmpresa()).get().getId() != id) 
            return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(dtoExp.getEmpresa())) 
            return new ResponseEntity(new Mensaje("La empresa es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setEmpresa(dtoExp.getEmpresa());
        experiencia.setDescripcion(dtoExp.getDescripcion());
        experiencia.setIngreso(dtoExp.getIngreso());
        experiencia.setEgreso(dtoExp.getEgreso());
                
                
            sExperiencia.save(experiencia);
             return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
             
    }             
   
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Delete(@PathVariable("id") int id) {
        if (!sExperiencia.existsById(id)) 
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        
             return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
             
    }   


} 

