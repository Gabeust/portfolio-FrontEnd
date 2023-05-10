
package com.porfolio.backend.Controller;

import com.porfolio.backend.Dto.DtoCertificaciones;
import com.porfolio.backend.Entity.Certificaciones;
import com.porfolio.backend.Security.Controller.Mensaje;
import com.porfolio.backend.Service.CertificacionesServ;
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
@RequestMapping("/certificaciones")
@CrossOrigin(origins = "http://localhost:4200/")
public class CertificacionesController {
    @Autowired
    CertificacionesServ certificacionesS;
    
   @GetMapping("/lista")
    public ResponseEntity<List<Certificaciones>> list(){
        List<Certificaciones> list = certificacionesS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
     @GetMapping("/detail/{id}")
    public ResponseEntity<Certificaciones> getById(@PathVariable("id") int id){
        if(!certificacionesS.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }       
        Certificaciones certificaciones = certificacionesS.getOne(id).get();
        return new ResponseEntity(certificaciones, HttpStatus.OK);
    }
    
      
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoCertificaciones dtocert) {
        if (StringUtils.isBlank(dtocert.getTitulo()))
            return new ResponseEntity(new Mensaje("El titulo es obligatoria"), HttpStatus.BAD_REQUEST);
     
        if (certificacionesS.existsByTitulo(dtocert.getTitulo())) 
            return new ResponseEntity(new Mensaje("Titulo ya existe"), HttpStatus.BAD_REQUEST);
        

        Certificaciones certificaciones = new Certificaciones(
                dtocert.getTitulo(),
                dtocert.getInstitucion(),
                dtocert.getAnio());
        
        certificacionesS.save(certificaciones);

        return new ResponseEntity(new Mensaje("Certificacion creada con exito"), HttpStatus.OK);

    }
   
       @PutMapping("/editar/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") int id, @RequestBody DtoCertificaciones dtocert) {
        if (!certificacionesS.existsById(id)) 
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
        if (certificacionesS.existsByTitulo(dtocert.getTitulo()) && certificacionesS.getByTitulo(dtocert.getTitulo()).get().getId() != id) 
            return new ResponseEntity(new Mensaje("El titulo ya existe"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(dtocert.getTitulo())) 
            return new ResponseEntity(new Mensaje("El titulo es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Certificaciones certificaciones = certificacionesS.getOne(id).get();
        certificaciones.setTitulo(dtocert.getTitulo());
        certificaciones.setInstitucion(dtocert.getInstitucion());
        certificaciones.setAnio(dtocert.getAnio());
                
            certificacionesS.save(certificaciones);
             return new ResponseEntity(new Mensaje("Certificacion actualizada"), HttpStatus.OK);
             
    }             
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Delete(@PathVariable("id") int id) {
        if (!certificacionesS.existsById(id)) 
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        certificacionesS.delete(id);
        
             return new ResponseEntity(new Mensaje("Certificacion eliminada"), HttpStatus.OK);
             
    }   


} 


