package com.bhoomi.kafka.controller;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhoomi.kafka.model.MessageModel;
import com.bhoomi.kafka.service.PubService;


/**
 * All the controllers related to Employee Service 
 * 
 * @filename EmployeeController.java
 *
 * @author Chaitanya Bhoomireddy
 * 
 */
@RestController
@RequestMapping("/message")
public class KafkaProducerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PubService pubService;
	
	@Autowired
	private KafkaTemplate<String, MessageModel> greetingKafkaTemplate;
	
	@PostMapping(value = "/pub/{topicName}", consumes= "application/json", produces="application/json")
	public String pubMessage(@RequestBody MessageModel mm, @PathVariable("topicName") String topicName) throws InterruptedException, ExecutionException  
	{	
		logger.info("publishing message to "+topicName);
		ListenableFuture<SendResult<String, MessageModel>> response = greetingKafkaTemplate.send(topicName, mm);
		/*
		 * response.addCallback(new ListenableFutureCallback<SendResult<String,
		 * MessageModel>>() {
		 * 
		 * @Override public void onSuccess(SendResult<String, MessageModel> result) {
		 * logger.info("Sent message=[" + mm.getMessage() + "] with offset=[" +
		 * result.getRecordMetadata().offset() + "]"); }
		 * 
		 * @Override public void onFailure(Throwable ex) {
		 * logger.info("Unable to send message=[" + mm.getMessage() + "] due to : " +
		 * ex.getMessage()); } });
		 */
		return "success";
	}	

}
