package com.tieroneoss.simplerestapi.service;

import com.tieroneoss.simplerestapi.domain.Students;
import com.tieroneoss.simplerestapi.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl  implements StudentsService{

    StudentsRepository repository;

    @Autowired
    public StudentsServiceImpl(StudentsRepository repository){
        this.repository = repository;
    }

    @Override
    public Students saveStudent(Students students) {
        return repository.save(students);
    }

    @Override
    public List<Students> getAllStudents() {
        return (List<Students>) repository.findAll();
    }

    @Override
    public Students getStudentById(int id) {
        Students student = null;
        student = repository.findById(id).get();
        return student;
    }

    @Override
    public Students deleteStudent(int id) {
        Students student = null;
        final Optional<Students> studentToBeDeleted = repository.findById(id);
        //Checking if the student with the given id is present. We will delete the entry and return the deleted entry in case it is present.
        if(studentToBeDeleted.isPresent()){
            student = repository.findById(id).get();
            repository.deleteById(id);
            return student;
        }
        //we will return null if the student with the given id is not present in the DB
        return null;
    }

    @Override
    public Students updateStudent(Students students) {
        final Optional<Students> blogToBeUpdated = repository.findById(students.getStudentId());
        //Checking if the student with the given id is present. We will update the entry and return the updated entry in case it is present.
        if(blogToBeUpdated.isPresent()){
            final Students updatedBlog = repository.save(students);
            return repository.findById(students.getStudentId()).get();
        }
        //we will return null if the student with the given id is not present in the DB
        return null;
    }

    @Override
    public Page<Students> getAllStudentsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize); //We use -1, since Java starts indexing from 0, 1 is the case with typical user understanding
        return this.repository.findAll(pageable);
    }
}
