package com.bhoomi.kafka.service;

import com.bhoomi.kafka.model.MessageModel;

/**
 * EmployeeSerice mapped for Employee Controller 
 * 
 * @filename EmployeeService.java
 *
 * @author Chaitanya Bhoomireddy
 * 
 */

public interface PubService {
	public abstract String publishMessage(MessageModel mm, String topicName);

}
