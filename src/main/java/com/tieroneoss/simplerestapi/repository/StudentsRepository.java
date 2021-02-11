package com.tieroneoss.simplerestapi.repository;

import com.tieroneoss.simplerestapi.domain.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
