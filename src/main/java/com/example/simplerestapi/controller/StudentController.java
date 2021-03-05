package com.example.simplerestapi.controller;

import com.example.simplerestapi.exception.StudentNotExistsException;
import com.example.simplerestapi.domain.Student;
import com.example.simplerestapi.exception.StudentAlreadyExistsException;
import com.example.simplerestapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
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
    @GetMapping("/allStudents")
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

    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) Integer standard,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) Integer pageNumber,
                                     @RequestParam(required = false) Integer pageSize,
                                     Pageable pageable) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
                return service.getStudents(name, email,standard, p);
    }/*

    @GetMapping("/students/paginated")
    public ResponseEntity<?> findPaginatedUsingParameters(@RequestParam (value = "pageNo", required=false, defaultValue = "1") int pageNo,
                                                          @RequestParam(value = "pageSize", required=false, defaultValue = "10") int pageSize){
        return new ResponseEntity<>(service.getAllStudentsPaginated(pageNo, pageSize).getContent(), HttpStatus.OK);
    }*/
}
