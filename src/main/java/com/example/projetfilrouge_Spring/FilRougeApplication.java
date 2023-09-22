package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.controller.api.TicketRestController;
import com.example.projetfilrouge_Spring.controller.api.UserRestController;
import com.example.projetfilrouge_Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class FilRougeApplication implements CommandLineRunner {
	private UserRestController userRestController;
	private TicketRestController ticketRestController;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public FilRougeApplication(UserRestController userRestController, TicketRestController ticketRestController) {
		this.userRestController = userRestController;
		this.ticketRestController = ticketRestController;
	}

	public static void main(String[] args) {SpringApplication.run(FilRougeApplication.class, args);}


	@Override
	public void run(String... args) throws Exception {


//		UserDto user1 = new UserDto("HedyLamarr", passwordEncoder.encode("gps"),"010203040506","https://commons.wikimedia.org/wiki/File:Hedy_Lamarr_in_The_Heavenly_Body_1944.jpg?uselang=fr","notamail@null.com");
//		TicketDto ticket1 = new TicketDto(LocalDate.parse("2024-02-24"), "EVENT", 30.30F);
//
//		userRestController.add(user1);
//		ticketRestController.add(ticket1);
//
//		UserDto user2 = new UserDto("testUser", passwordEncoder.encode("testsuppr"), "testsuppr", "testsuppr", "testsuppr");
//		TicketDto ticket2 = new TicketDto(LocalDate.parse("2024-02-24"), "TESTSUPPR", 30.30F);
//
//		userRestController.add(user2);
//		ticketRestController.add(ticket2);
//
//		User user3 = new User("steeve", passwordEncoder.encode("steeve"));
//		user3.setRoleList(Arrays.asList(new Role("USER")));
//		userRepo.save(user3);

	}
}