package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.controller.api.TicketRestController;
import com.example.projetfilrouge_Spring.controller.api.UserRestController;
import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.UserRepository;
import com.example.projetfilrouge_Spring.repository.entity.Role;
import com.example.projetfilrouge_Spring.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;


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

		// CRUD :
		// CREATE  : OK via RestController for User
		// READ (getById) : OK via RestController for User
		// TODO READ (getByUsername) : OK via RestController for User
		// DELETE : OK via RestController for User
		// UPDATE : OK via RestController for User

		// CREATE  : OK via RestController for Ticket
		// READ : OK via RestController for Ticket (getById)
		// TODO READ (getByEvent) : OK via RestController for Ticket
		// DELETE : OK via RestController for Ticket
		// UPDATE : OK via RestController for Ticket
//
//		UserDto user1 = new UserDto("HedyLamarr", passwordEncoder.encode("gps"),"010203040506","https://commons.wikimedia.org/wiki/File:Hedy_Lamarr_in_The_Heavenly_Body_1944.jpg?uselang=fr","notamail@null.com");
//		TicketDto ticket1 = new TicketDto(LocalDate.parse("2024-02-24"), "EVENT", 30.30F);
//
//		userRestController.add(user1);
//		ticketRestController.add(ticket1);
//
//		UserDto user2 = new UserDto("testUser", passwordEncoder.encode("testPwd"), "testPhone", "testPhoto", "testEmail");
//		TicketDto ticket2 = new TicketDto(LocalDate.parse("2024-02-24"), "TESTevent", 30.30F);
//
//		userRestController.add(user2);
//		ticketRestController.add(ticket2);

		// //TEST DELETE
//		userRestController.deleteById(2L);
//		ticketRestController.deleteById(2L);

		// //TEST READ
//		System.out.println("PRINT TEST");
//		System.out.println("User username : " + userRestController.getById(2L).get().getUsername());
//		System.out.println("Ticket event : " + ticketRestController.getById(2L).get().getEvent());
// fixme:		System.out.println("User username : " + userRestController.getByUsernameIsContainingIgnoreCase("tosuppr").get(0).getUsername());
//	fixme:	System.out.println("Ticket event : " + ticketRestController.getByEvent("TESTSUPPR").toString());

		// //TEST UPDATE
//		UserDto user2Updated = new UserDto("testUser", "UPDATEDpwd", "UPDATEDnum", "UPDATEDphoto", "UPDATEDmail");
//		TicketDto ticket2Updated = new TicketDto(LocalDate.parse("2020-02-02"), "UPDATED EVENT", 22.22F);
//
//		userRestController.updateById(2L, user2Updated);
//		ticketRestController.updateById(2L, ticket2Updated);
//
//		System.out.println("User updated PhotoUrl : " + userRestController.getById(2L).get().getPhotoUrl());
//		System.out.println("Ticket updated event : " + ticketRestController.getById(2L).get().getEvent());

		// TEST findByEvent
//		List<TicketDto> ticketsDtoFoundByEvent = ticketRestController.getByEvent("EVENT");
//		for (TicketDto dto:ticketsDtoFoundByEvent) {
//			System.out.println("Test Ticket getByEvent : " + dto.toString());
//		}

		List<UserDto> usersDtoFoundByName = userRestController.getByUsernameIsContainingIgnoreCase("La");
		for (UserDto dto:usersDtoFoundByName) {
			System.out.println("Test User getByUsernameIsContainingIgnoreCase : " + dto.getUsername().toString());
		}

	}
}