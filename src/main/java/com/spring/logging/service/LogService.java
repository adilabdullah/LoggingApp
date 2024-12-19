package com.spring.logging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.logging.model.logs;
import com.spring.logging.repository.LogsRepo;

@Service
public class LogService {

	@Autowired
	LogsRepo logsRepo;
	
	public void save(logs log)
	{
		logsRepo.save(log);
	}
	
}
