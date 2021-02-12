package com.tieroneoss.simplerestapi.service;

import com.tieroneoss.simplerestapi.domain.Student;
import com.tieroneoss.simplerestapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl  implements StudentService{

    StudentRepository repository;

    @Autowired
    public StudentsServiceImpl(StudentRepository repository){
        this.repository = repository;
    }

    @Override
    public Student saveStudent(Student student) {
        final Optional<Student> ifStudentExists = repository.findById(student.getStudentId());
        if(ifStudentExists.isPresent()){
            return null;
        }
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return (List<Student>) repository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Student student = null;
        student = repository.findById(id).get();
        return student;
    }

    @Override
    public String deleteStudent(int id) {
        Student student = null;
        final Optional<Student> studentToBeDeleted = repository.findById(id);
        //Checking if the student with the given id is present. We will delete the entry and return the deleted entry in case it is present.
        if(studentToBeDeleted.isPresent()){
            student = studentToBeDeleted.get();
            repository.deleteById(id);
            return "Student Record Deleted Successfully.";
        }
        return null;
    }

    @Override
    public Student updateStudent(Student students) {
        final Optional<Student> blogToBeUpdated = repository.findById(students.getStudentId());
        //Checking if the student with the given id is present. We will update the entry and return the updated entry in case it is present.
        if(blogToBeUpdated.isPresent()){
            final Student updatedBlog = repository.save(students);
            return repository.findById(students.getStudentId()).get();
        }
        //we will return null if the student with the given id is not present in the DB
        return null;
    }

    @Override
    public Page<Student> getAllStudentsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize); //We use -1, since Java starts indexing from 0, 1 is the case with typical user understanding
        return this.repository.findAll(pageable);
    }

    @Override
    public Student findStudentByFirstNameAndStandard(String firstName, int standard) {
        return repository.findByFirstNameAndStandard(firstName, standard);
    }
}
