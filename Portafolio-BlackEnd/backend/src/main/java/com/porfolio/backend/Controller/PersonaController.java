package com.porfolio.backend.Controller;

import com.porfolio.backend.Dto.DtoPersona;
import com.porfolio.backend.Entity.Persona;
import com.porfolio.backend.Security.Controller.Mensaje;
import com.porfolio.backend.Service.ImpPersonaService;
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
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired 
    ImpPersonaService impPersonaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = impPersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
     @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }       
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
      
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtopers) {
        if (StringUtils.isBlank(dtopers.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatoria"), HttpStatus.BAD_REQUEST);
     
        if (impPersonaService.existsByNombre(dtopers.getNombre())) 
            return new ResponseEntity(new Mensaje("nombre ya existe"), HttpStatus.BAD_REQUEST);
        

        Persona persona = new Persona(
                dtopers.getNombre(),
                dtopers.getApellido(),
                dtopers.getDescripcion(),
                     dtopers.getImg());
        
        impPersonaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona creada con exito"), HttpStatus.OK);

    }
   
       @PutMapping("/editar/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") int id, @RequestBody DtoPersona dtopers) {
        if (!impPersonaService.existsById(id)) 
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
        if (impPersonaService.existsByNombre(dtopers.getNombre()) && impPersonaService.getByNombre(dtopers.getNombre()).get().getId() != id) 
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(dtopers.getNombre())) 
            return new ResponseEntity(new Mensaje("El titulo es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Persona persona = impPersonaService.getOne(id).get();
        persona.setNombre(dtopers.getNombre());
        persona.setApellido(dtopers.getApellido());
        persona.setDescripcion(dtopers.getDescripcion());
        persona.setImg(dtopers.getImg());
                
            impPersonaService.save(persona);
             return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
             
    }             
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Delete(@PathVariable("id") int id) {
        if (!impPersonaService.existsById(id)) 
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        impPersonaService.delete(id);
        
             return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
             
    }   


} 


