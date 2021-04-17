package com.example.dao.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.LogDetails;

@Repository
public interface LogDetailsRepository extends JpaRepository<LogDetails, Integer>{

}
