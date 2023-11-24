package auca.registration.AucaRegistration.service;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import auca.registration.AucaRegistration.domain.Semester;
import auca.registration.AucaRegistration.domain.Student;
import auca.registration.AucaRegistration.domain.StudentRegistration;
import auca.registration.AucaRegistration.repository.StudentRegistrationRepo;
import auca.registration.AucaRegistration.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRegistrationRepo regRepo;

    public String saveStudent(Student student) {
        if (student != null) {
            studentRepository.save(student);
            return "Student created successfully";
        } else {
            return null;
        }
    }
    public boolean isStudentExists(Long StudentId) {
        return studentRepository.existsById(StudentId);
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public String updateStudent(Long StudentId, Student student) {
        logger.info("Updating student with registration number: {}", StudentId);
        try {
            if (student != null) {
                if (isStudentExists(StudentId)) {
                    // Update the student information here
                    studentRepository.save(student);
                    logger.info("Student updated successfully");
                    return "Student updated successfully";
                } else {
                    return "Student not found";
                }
            } else {
                return "Invalid input";
            }
        }catch (Exception ex){
            logger.error("Failed to update student", ex);
            return "Student not updated successfully";
        }
    }

    public String deleteStudent(Long StudentId) {
        logger.info("Deleting student with registration number: {}", StudentId);
        try {
            if (StudentId != null) {
                if (isStudentExists(StudentId)) {
                    studentRepository.deleteById(StudentId);
                    logger.info("Student deleted");
                    return "Student deleted";
                } else {
                    return "Student not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete student", e);
            return "Student not deleted successfully";
        }
    }
    public List<StudentRegistration> getStudentsBySemester(Semester semester) {
        logger.info("Student in Semester: {}", semester);
        return regRepo.findBySemester(semester);
    }
    public List<StudentRegistration> getStudentsByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return regRepo.findByDepartmentAndSemester(department, semester);
    }
}