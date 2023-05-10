package com.porfolio.backend.Controller;

import com.porfolio.backend.Dto.DtoCompetencias;
import com.porfolio.backend.Entity.Competencias;
import com.porfolio.backend.Security.Controller.Mensaje;
import com.porfolio.backend.Service.CompetenciasServ;
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
@RequestMapping("/Competencias")
@CrossOrigin(origins = "http://localhost:4200/")

public class CompetenciasController {

    @Autowired
    CompetenciasServ competenciasS;

    @GetMapping("/lista")
    public ResponseEntity<List<Competencias>> list() {
        List<Competencias> list = competenciasS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Competencias> getById(@PathVariable("id") int id) {
        if (!competenciasS.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }
        Competencias competencias = competenciasS.getOne(id).get();
        return new ResponseEntity(competencias, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoCompetencias dtocomp) {
        if (StringUtils.isBlank(dtocomp.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        if (competenciasS.existsByNombre(dtocomp.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Competencias competencias = new Competencias(
                dtocomp.getNombre(),
                dtocomp.getPorcentaje(),
                dtocomp.getUrl());

        competenciasS.save(competencias);

        return new ResponseEntity(new Mensaje("Competencia creada con exito"), HttpStatus.OK);

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") int id, @RequestBody DtoCompetencias dtocomp) {
        if (!competenciasS.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        if (competenciasS.existsByNombre(dtocomp.getNombre()) && competenciasS.getByNombre(dtocomp.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtocomp.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Competencias competencias = competenciasS.getOne(id).get();
        competencias.setNombre(dtocomp.getNombre());
        competencias.setPorcentaje(dtocomp.getPorcentaje());
        competencias.setUrl(dtocomp.getUrl());

        competenciasS.save(competencias);
        return new ResponseEntity(new Mensaje("Certificacion actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Delete(@PathVariable("id") int id) {
        if (!competenciasS.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        competenciasS.delete(id);

        return new ResponseEntity(new Mensaje("Certificacion eliminada"), HttpStatus.OK);

    }

}
