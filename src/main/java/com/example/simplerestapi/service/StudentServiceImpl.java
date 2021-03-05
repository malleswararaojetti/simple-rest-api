package com.example.simplerestapi.service;

import com.example.simplerestapi.repository.StudentRepository;
import com.example.simplerestapi.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository){
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
    public String deleteStudent(int id){
        Optional<Student> student = null;
        student = repository.findById(id);
        if(student.isPresent())
        {
            repository.deleteById(student.get().getStudentId());
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

    //Dynamic Query using specification API
    @Override
    public List<Student> getStudents(String name, String email, Integer standard, Pageable pageable){
       return (List<Student>) repository.findAll((Specification<Student>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (StringUtils.hasText(name)) {//The query will be added and executed only if it has a value passed it to
               p = cb.and(p, cb.like(root.get("firstName"), "%" + name + "%"));
            }
            if (Objects.nonNull(standard)) {//The query will be added and executed only if it has a value passed it to
                p = cb.and(p, cb.equal(root.get("standard"), standard));
            }
            if (StringUtils.hasText(email)) {//The query will be added and executed only if it has a value passed it to
                p = cb.and(p, cb.like(root.get("email"), "%" + email + "%"));
            }
            return p;
        }, pageable).getContent();
    }
}
