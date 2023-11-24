package auca.registration.AucaRegistration.repository;

import auca.registration.AucaRegistration.domain.CourseDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDefRepo extends JpaRepository<CourseDefinition,String> {
}
