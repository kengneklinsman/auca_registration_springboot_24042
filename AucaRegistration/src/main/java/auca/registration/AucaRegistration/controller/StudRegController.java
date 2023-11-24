package auca.registration.AucaRegistration.controller;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import auca.registration.AucaRegistration.domain.Semester;
import auca.registration.AucaRegistration.domain.StudentRegistration;
import auca.registration.AucaRegistration.service.SemesterService;
import auca.registration.AucaRegistration.service.StudRegistrationService;
import auca.registration.AucaRegistration.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/studentRegistration" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))

public class StudRegController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudRegistrationService regService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private SemesterService semesterService;
    //creating
    @PostMapping(value = "/saveRegistration")
    public ResponseEntity<?> createReg(@RequestBody StudentRegistration studentReg) {
        if (studentReg != null) {
            String message = regService.saveRegistration(studentReg);
            if (message != null && message.startsWith("Student Registered Successfully")) {
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            } else if (message != null && message.startsWith("Student with ID")) {
                return new ResponseEntity<>(message, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>("Student Not Registered", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping(value = "/listRegistrations")
    public ResponseEntity<List<StudentRegistration>> listRegs() {
        List<StudentRegistration> studentReg = regService.listStudentsReg();
        return new ResponseEntity<>(studentReg, HttpStatus.OK);
    }

    @PutMapping(value = "/updateRegistration/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentRegistration regStudent) {
        if (regStudent != null) {
            String message = regService.updateStudentReg(id, regStudent);
            if (message != null) {
                return new ResponseEntity<>("Student Registration Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Registration Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteRegistration/{id}")
    public ResponseEntity<String> deleteStudReg(@PathVariable Integer id) {
        if (id != null) {
            String message = regService.deleteStudentReg(id);
            if (message != null) {
                return new ResponseEntity<>("Student Registration Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Registration Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> registrations = regService.getRegistrationsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsBySemester(

            @PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> registrations = regService.getRegistrationsBySemester(semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
