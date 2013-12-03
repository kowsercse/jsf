package com.kowsercse.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.kowsercse.service.TestService;

@ContextConfiguration(locations={"classpath:spring/test-config.xml"})
public class AbstractTransactionalTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private TestService service;
	
	@Test
	public void testGeneriService() {
		service.testGenericDao();
	}
	
	@Test
	public void testService() {
		service.findAll();
	}
	
//	@Test
	public void test() {
		System.out.println("fun");
	}
	
}
