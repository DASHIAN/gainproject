package com.lec;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ljh.study.sample.Sample;

import config.RootConfig;


@DisplayName("Root Context 테스트")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
public class RootConfigTest {
	private Sample sample;
	
	@Autowired
	public RootConfigTest(Sample sample) {
		this.sample = sample;
	}
	
	@Test
	@DisplayName("Root Context Component scan 확인")
	void rootContextComponentScanTest() {
		assertNotNull(sample);
	}
	
}
