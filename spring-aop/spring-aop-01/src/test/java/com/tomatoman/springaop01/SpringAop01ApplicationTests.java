package com.tomatoman.springaop01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAop01ApplicationTests {

	@Autowired
	Calculator calculator;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAspectLog001() {
		int result = calculator.add(1, 2);
	}

	@Test
	public void testAspectLog002() {
		int result = calculator.div(4, 2);
	}

	@Test
	public void testAspectLog003() {
		int result = calculator.div(4, 0);
	}

}
