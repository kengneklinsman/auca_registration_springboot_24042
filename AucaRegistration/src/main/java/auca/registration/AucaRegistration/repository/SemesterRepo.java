package auca.registration.AucaRegistration.repository;

import auca.registration.AucaRegistration.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, String> {
}