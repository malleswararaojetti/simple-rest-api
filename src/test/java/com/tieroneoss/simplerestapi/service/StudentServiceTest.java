package com.tieroneoss.simplerestapi.service;

import com.tieroneoss.simplerestapi.domain.Student;
import com.tieroneoss.simplerestapi.exception.StudentNotExistsException;
import com.tieroneoss.simplerestapi.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl service;

    @Test
    void saveStudent() {
        Student student = new Student(1, "Ramesh", "V","rameshv@gamil.com", 10);
        when(repository.save(any())).thenReturn(Optional.of(student).get());
        service.saveStudent(student);
        verify(repository, times(1)).save(any());
    }

    @Test
    void getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        when(service.getAllStudents()).thenReturn(studentList);
        verify(repository, times(1)).findAll();
    }

    @Test
    void getStudentById() throws StudentNotExistsException {
        Student student1 = new Student(1, "Ramesh", "V","ramesh@gamil.com", 10);
        when(repository.findById(1)).thenReturn(Optional.of(student1));
        Student fetchedStudent = service.getStudentById(student1.getStudentId());
        assertEquals(fetchedStudent, student1);
        verify(repository, times(1)).findById(any());

    }

    @Test
    void deleteStudent() {
        Student student1 = new Student(5, "Ramesh", "V","ramesh@gamil.com", 10);
        when(repository.findById(5)).thenReturn(Optional.of(student1));
        String result = service.deleteStudent(5);
        assertEquals(result, "Student Record Deleted Successfully.");
        verify(repository, times(1)).findById(any());
    }


    @Test
    void updateStudent() {
    }

    @Test
    void getAllStudentsPaginated() {
    }

    @Test
    void getStudents() {
    }
}