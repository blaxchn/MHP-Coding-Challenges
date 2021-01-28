package com.mhp.coding.challenges.retry;

import com.mhp.coding.challenges.retry.persistence.RetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RetryApplication implements CommandLineRunner {

	@Autowired
	private RetryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RetryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
