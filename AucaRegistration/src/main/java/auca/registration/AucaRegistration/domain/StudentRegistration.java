package auca.registration.AucaRegistration.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "student_id")
    private String studentId;
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "academic_unit_id")
    private AcademicUnit department;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToMany(mappedBy = "studentRegistration")
    @JsonBackReference
    private List<StudentCourse> courses;

    @ManyToOne
    @JoinColumn(name = "registration_number")
    private Student student;

    public StudentRegistration() {
    }

    public StudentRegistration(int id, String studentId, LocalDate registrationDate, AcademicUnit department, Semester semester, List<StudentCourse> courses, Student student) {
        this.id = id;
        this.studentId = studentId;
        this.registrationDate = registrationDate;
        this.department = department;
        this.semester = semester;
        this.courses = courses;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public AcademicUnit getDepartment() {
        return department;
    }

    public void setDepartment(AcademicUnit department) {
        this.department = department;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<StudentCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<StudentCourse> courses) {
        this.courses = courses;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
