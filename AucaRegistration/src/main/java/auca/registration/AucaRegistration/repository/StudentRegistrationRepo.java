package auca.registration.AucaRegistration.repository;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import auca.registration.AucaRegistration.domain.Semester;
import auca.registration.AucaRegistration.domain.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepo extends JpaRepository<StudentRegistration,Integer> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    boolean existsByStudentId(String studentId);
    StudentRegistration findByStudentId(String studentId);

    List<StudentRegistration> findBySemester(Semester semester);
    List<StudentRegistration> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
    List<StudentRegistration> findBySemesterId(String semesterId);

}
