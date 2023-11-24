package auca.registration.AucaRegistration.controller;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import auca.registration.AucaRegistration.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unit" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class UnitController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private UnitService unitService;

    @PostMapping(value = "/saveUnit")
    public ResponseEntity<?> createUnit(@RequestBody AcademicUnit unit){
        if(unit != null ){

            String message = unitService.saveUnit(unit);
            if(message != null){
                return new ResponseEntity<>("Unit Saved ", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Unit Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/listUnits")
    public ResponseEntity<List<AcademicUnit>> listUnits() {
        List<AcademicUnit> units = unitService.listUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUnit/{code}")
    public ResponseEntity<String> updateUnit(@PathVariable String code, @RequestBody AcademicUnit unit) {
        if (unit != null) {
            String message = unitService.updateUnit(code, unit);
            if (message != null) {
                return new ResponseEntity<>("Unit Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unit Not Updated", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteUnit/{code}")
    public ResponseEntity<String> deleteUnit(@PathVariable String code) {
        if (code != null) {
            String message = unitService.deleteUnit(code);
            if (message != null) {
                return new ResponseEntity<>("Unit Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unit Not Deleted", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}

