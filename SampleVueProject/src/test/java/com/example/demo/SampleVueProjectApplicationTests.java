package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleVueProjectApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void assertTimeoutSuccessTest() {
		assertTimeout(Duration.ofMillis(1000), () -> {
            System.out.println("executable 실행");
        });
	    
	}
	
	@Test
    void assertTimeoutFailureTest() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Thread.sleep(1500);
            System.out.println("executable 실행");
        });
    }

}
