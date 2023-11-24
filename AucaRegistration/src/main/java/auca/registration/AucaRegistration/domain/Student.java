package auca.registration.AucaRegistration.domain;

import jakarta.persistence.*;

@Entity
@Table(name= "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    public Student() {}

    public Student(String studentName, String dateOfBirth) {
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
