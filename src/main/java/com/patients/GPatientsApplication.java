package com.patients;

import com.patients.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("unused")
@SpringBootApplication
public class GPatientsApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(GPatientsApplication.class, args);
		System.out.println("Appliation started!");
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
