package com.hamish.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

/**
 * Created by hamishdickson on 28/10/14.
 *
 */

@Entity
@Table(name = "student", catalog = "test")
public class Student {
    private static final long serialVersionUID = 1L;

    private Integer studentId;
    private String studentName;
    private String studentAge;

    public Student() {}

    public Student(String studentName, String studentAge) {
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "STUDENT_ID", unique = true, nullable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Column(name = "STUDENT_NAME", nullable = false, length = 10)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "STUDENT_AGE", nullable = false, length = 20)
    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }
}
