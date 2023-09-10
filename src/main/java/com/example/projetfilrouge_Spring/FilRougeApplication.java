package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.service.TicketService;
import com.example.projetfilrouge_Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FilRougeApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private TicketService ticketService;

	public static void main(String[] args) {SpringApplication.run(FilRougeApplication.class, args);}


	@Override
	public void run(String... args) throws Exception {

	}
}
