package com.usermgmtservice.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
* @author ahmed
*/
public class RandomGeneratorTest {

	private int randomNumberSize;
	
	@BeforeEach
	void setup() {
		randomNumberSize = 4;
	}
	@Test
	void generateMobileOtpCode() {
		/*
		 * try (MockedStatic<RandomGenerator> randomGenerator =
		 * Mockito.mockStatic(RandomGenerator.class)){ randomGenerator.when(()->{
		 * RandomGenerator.generateMobileOtpCode(4); }).thenReturn("1234"); String
		 * actualRandomGenerated = RandomGenerator.generateMobileOtpCode(4); String
		 * expectedRandomeGenerated = "1010"; assertEquals(expectedRandomeGenerated,
		 * actualRandomGenerated); }
		 */
		 
		//assertEquals(RandomGenerator.generateMobileOtpCode(4),"1234" );
		String actualRandom = RandomGenerator.generateMobileOtpCode(randomNumberSize);
				
		assertEquals(actualRandom.length(),randomNumberSize);
	}
	
	
}
