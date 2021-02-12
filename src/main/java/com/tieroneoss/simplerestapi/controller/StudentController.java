package com.tieroneoss.simplerestapi.controller;

import com.tieroneoss.simplerestapi.domain.Student;
import com.tieroneoss.simplerestapi.exception.StudentAlreadyExistsException;
import com.tieroneoss.simplerestapi.exception.StudentNotExistsException;
import com.tieroneoss.simplerestapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service){
        this.service = service;
    }

    //This method should save the student and return the student object
    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) throws StudentAlreadyExistsException {
        final Student studentAdded = service.saveStudent(student);
        if(studentAdded==null){
            throw new StudentAlreadyExistsException("Student already exists");
        }
        return new ResponseEntity<>(studentAdded, HttpStatus.CREATED);
    }

    //This method should return all the student objects
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() throws StudentNotExistsException {
        if(service.getAllStudents().isEmpty()){
            throw new StudentNotExistsException("No records found");
        }
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    //This method should find the student with the given id and return the student object
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") int studentId) throws StudentNotExistsException {
        final Student studentById = service.getStudentById(studentId);
        if(studentById==null){
            throw new StudentNotExistsException("Student Not Exists");
        }
        return new ResponseEntity<>(studentById, HttpStatus.FOUND);
    }

    //This method should delete the student by given id and return the student object
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentAfterDeleting(@PathVariable("studentId") int studentId) throws StudentNotExistsException {
        final String studentDeleted = service.deleteStudent(studentId);
        if(studentDeleted==null){
            throw new StudentNotExistsException("Student Not Exists");
        }
        return new ResponseEntity<>(studentDeleted, HttpStatus.OK);
    }

    //This method should update the student and return the student object
    @PutMapping("/student")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) throws StudentNotExistsException {
        final Student studentUpdated = service.updateStudent(student);
        if(studentUpdated == null){
            throw new StudentNotExistsException("Student not exists");
        }
        return new ResponseEntity<>(studentUpdated, HttpStatus.OK);
    }

    //function for pagination
    @GetMapping("/students/{pageNo}")
    public ResponseEntity<?> findPaginated(@PathVariable (value = "pageNo") int pageNo){
        int pageSize = 3;
        return new ResponseEntity<>(service.getAllStudentsPaginated(pageNo, pageSize).getContent(), HttpStatus.OK);
    }
}