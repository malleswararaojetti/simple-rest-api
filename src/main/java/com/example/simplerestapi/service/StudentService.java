package com.example.simplerestapi.service;

import com.example.simplerestapi.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /*public Optional<Student> findStudentByAllParameters(String firstName, String email, int standard) throws StudentNotExistsException;

    public Optional<Student> findStudentByFirstNameAndEmail(String firstName, String email) throws StudentNotExistsException;

    public Optional<List<Student>> findStudentsByFirstNameAndStandard(String firstName, int standard) throws StudentNotExistsException;

    public Optional<Student> findStudentBQuery
    public List<Student> getStudents(String name, String email, String standard, Pageable pageable);yEmailAndStandard(String email, int standard) throws StudentNotExistsException;

    Optional<List<Student>> findStudentsByFirstName(String firstName) throws StudentNotExistsException;

    Optional<List<Student>> findStudentsByStandard(int standard) throws StudentNotExistsException;

    Optional<Student> findStudentByEmail(String email) throws StudentNotExistsException;*/

    //Question Dynamic Query
    public List<Student> getStudents(String name, String email, Integer standard, Pageable pageable);

}
