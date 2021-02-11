package com.tieroneoss.simplerestapi.Controller;

import com.tieroneoss.simplerestapi.domain.Students;
import com.tieroneoss.simplerestapi.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {

    private StudentsService service;

    @Autowired
    public StudentsController(StudentsService service){
        this.service = service;
    }

    //This method should save the student and return the student object
    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody Students student){
        return new ResponseEntity<>(service.saveStudent(student), HttpStatus.CREATED);
    }

    //This method should return all the student objects
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(){
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    //This method should find the student with the given id and return the student object
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Students> getStudentById(@PathVariable("studentId") int studentId){
        return new ResponseEntity<>(service.getStudentById(studentId), HttpStatus.FOUND);
    }

    //This method should delete the student by given id and return the student object
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentAfterDeleting(@PathVariable("studentId") int studentId){
        return new ResponseEntity<>(service.deleteStudent(studentId), HttpStatus.OK);
    }

    //This method should update the student and return the student object
    @PutMapping("/student")
    public ResponseEntity<?> updateStudent(@RequestBody Students student){
        return new ResponseEntity<>(service.updateStudent(student), HttpStatus.OK);
    }
}
