package auca.registration.AucaRegistration.repository;

import auca.registration.AucaRegistration.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, String> {
}
