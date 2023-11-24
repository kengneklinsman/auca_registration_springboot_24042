package auca.registration.AucaRegistration.repository;

import auca.registration.AucaRegistration.domain.AcademicUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepo extends JpaRepository<AcademicUnit,String> {
}
