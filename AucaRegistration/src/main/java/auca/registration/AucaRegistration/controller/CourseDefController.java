package auca.registration.AucaRegistration.controller;

import auca.registration.AucaRegistration.domain.CourseDefinition;
import auca.registration.AucaRegistration.service.CourseDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/crsDef" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class CourseDefController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CourseDefService crsDefService;
    //creating
    @PostMapping(value = "/saveDef")
    public ResponseEntity<?> createDef(@RequestBody CourseDefinition crsDef){
        if(crsDef != null ){
            String message = crsDefService.saveDef(crsDef);
            if(message != null){
                return new ResponseEntity<>("Course Saved Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Course Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //Listing
    @GetMapping(value = "/listDefs")
    public ResponseEntity<List<CourseDefinition>> listDefs() {
        List<CourseDefinition> defs = crsDefService.listDefs();
        return new ResponseEntity<>(defs, HttpStatus.OK);
    }
    //Updating
    @PutMapping(value = "/updateDef/{code}")
    public ResponseEntity<String> updateDef(@PathVariable String code, @RequestBody CourseDefinition crsDef) {
        if (crsDef != null) {
            String message = crsDefService.updateDef(code,crsDef);
            if (message != null) {
                return new ResponseEntity<>("Course Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //Deleting
    @DeleteMapping(value = "/deleteDef/{code}")
    public ResponseEntity<String> deleteDef(@PathVariable String code) {
        if (code != null) {
            String message = crsDefService.deleteDef(code);
            if (message != null) {
                return new ResponseEntity<>("Course Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
