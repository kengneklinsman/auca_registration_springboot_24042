package auca.registration.AucaRegistration.repository;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import auca.registration.AucaRegistration.domain.Course;
import auca.registration.AucaRegistration.domain.CourseDefinition;
import auca.registration.AucaRegistration.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    public boolean existsByCourseDefinition(CourseDefinition courseDefinition);
    List<Course> findByDepartmentAndSemester(AcademicUnit department, Semester semester);

}

