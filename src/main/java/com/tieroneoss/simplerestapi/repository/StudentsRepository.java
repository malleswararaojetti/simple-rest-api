package com.tieroneoss.simplerestapi.repository;

import com.tieroneoss.simplerestapi.domain.Students;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends CrudRepository<Students, Integer> {

}
