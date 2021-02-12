package com.tieroneoss.simplerestapi.service;

import com.tieroneoss.simplerestapi.domain.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    //Abstract methods

    //To save a new student
    Student saveStudent(Student student);

    //To get all students
    List<Student> getAllStudents();

    //To get a student by ID
    Student getStudentById(int id);

    //To delete a Student by ID
    String deleteStudent(int id);

    //To update a student entry
    Student updateStudent(Student student);

    Page<Student> getAllStudentsPaginated(int pageNo, int pageSize);

}
