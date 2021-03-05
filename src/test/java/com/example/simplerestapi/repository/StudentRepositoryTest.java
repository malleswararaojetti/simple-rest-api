package com.example.simplerestapi.repository;

import com.example.simplerestapi.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    public void givenStudentToSaveShouldReturnSavedStudent(){
        Student student = new Student(1, "Ramesh", "V","rameshv@gamil.com", 10);
        repository.save(student);
        Student savedStudent = repository.findById(student.getStudentId()).get();
        assertNotNull(savedStudent);
        assertEquals(student.getEmail(), savedStudent.getEmail());
    }

    @Test
    public void givenStudentIdShouldReturnRespectiveStudent(){
        Student student = new Student(2, "Mahesh", "V","mahesh@gamil.com", 10);
        repository.save(student);
        Student fetchedStudent = null;
        fetchedStudent = repository.findById(2).get();
        assertNotNull(fetchedStudent);
        assertEquals(student.getFirstName(), fetchedStudent.getFirstName());
    }

    @Test
    public void givenGetAllStudentsShouldReturnAllTheStudents(){
        List<Student> studentList = new ArrayList<Student>();
        Student student1 = new Student(1, "Ramesh", "V","ramesh@gamil.com", 10);
        Student student2 = new Student(2, "Mahesh", "V","mahesh@gamil.com", 11);
        Student student3 = new Student(3, "Lokesh", "V","lokesh@gamil.com", 12);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        repository.save(student1);
        repository.save(student2);
        repository.save(student3);

        List<Student> fetchedStudentList = repository.findAll();

        assertEquals(fetchedStudentList.get(1).getFirstName(), studentList.get(1).getFirstName());
        assertEquals(3, fetchedStudentList.size());
    }
}