package com.tieroneoss.simplerestapi.service;

import com.tieroneoss.simplerestapi.domain.Students;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentsService {

    //Abstract methods

    //To save a new student
    Students saveStudent(Students students);

    //To get all students
    List<Students> getAllStudents();

    //To get a student by ID
    Students getStudentById(int id);

    //To delete a Student by ID
    Students deleteStudent(int id);

    //To update a student entry
    Students updateStudent(Students students);

    Page<Students> getAllStudentsPaginated(int pageNo, int pageSize);

}
