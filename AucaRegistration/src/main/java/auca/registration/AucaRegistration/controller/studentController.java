package auca.registration.AucaRegistration.controller;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import auca.registration.AucaRegistration.domain.Semester;
import auca.registration.AucaRegistration.domain.Student;
import auca.registration.AucaRegistration.domain.StudentRegistration;
import auca.registration.AucaRegistration.repository.StudentRepository;
import auca.registration.AucaRegistration.service.SemesterService;
import auca.registration.AucaRegistration.service.StudentService;
import auca.registration.AucaRegistration.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(
        value = "/student",
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE }
)

public class studentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository repo;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private UnitService academicUnitService;


    @PostMapping(value = "/saveStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if (student != null) {
            String message = studentService.saveStudent(student);
            if (message != null) {
                return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        "student not created" + student.getStudentName(),
                        HttpStatus.CREATED
                );
            }
        } else {
            return new ResponseEntity<>("something is wrong", HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping(value = "/listStudents")
    public ResponseEntity<java.util.List<Student>> listStudents() {
        List<Student> students = studentService.listStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping(value = "/updateStudent/{registrationNumber}")
    public ResponseEntity<String> updateStudent(@PathVariable Long StudentId, @RequestBody Student student) {
        if (student != null) {
            String message = studentService.updateStudent(StudentId, student);
            if (message != null) {
                return new ResponseEntity<>("Student Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteStudent/{registrationNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long StudentId) {
        if (StudentId != null) {
            String message = studentService.deleteStudent(StudentId);
            if (message != null) {
                return new ResponseEntity<>("Student Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = academicUnitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> students = studentService.getStudentsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsBySemester(@PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> students = studentService.getStudentsBySemester(semester);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


