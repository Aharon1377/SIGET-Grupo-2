package com.example.demo;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
public class SigetGrupo2ApplicationTest {
	
	@Test
	void defaultTest() {
		if(1!=1) {
			fail("Imposible");
		}
	}
}
