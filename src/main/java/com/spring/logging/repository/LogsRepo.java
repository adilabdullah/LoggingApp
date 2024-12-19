package com.spring.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.logging.model.logs;

@Repository
public interface LogsRepo extends JpaRepository<logs,Long>{

}
