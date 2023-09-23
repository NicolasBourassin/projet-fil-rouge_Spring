package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.controller.api.TicketRestController;
import com.example.projetfilrouge_Spring.controller.api.UserRestController;
import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

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
		// READ (getByUsername) : OK via RestController for User
		// DELETE : OK via RestController for User
		// UPDATE : OK via RestController for User

		// CREATE  : OK via RestController for Ticket
		// READ : OK via RestController for Ticket (getById)
		// READ (getByEvent) : OK via RestController for Ticket
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

		// TEST getByUsernameIsContainingIgnoreCase
//		List<UserDto> usersDtoFoundByName = userRestController.getByUsernameIsContainingIgnoreCase("La");
//		for (UserDto dto:usersDtoFoundByName) {
//			System.out.println("Test User getByUsernameIsContainingIgnoreCase : " + dto.getUsername().toString());
//		}

		//TODO : ENCODE ALL PASSWORD EXAMPLES

//		UserDto user = new UserDto("sam", passwordEncoder.encode("123"),"","","sam@null.com");
//		userRestController.add(user);
// TODO : create test users
		UserDto user1 = new UserDto("HedyLamarr",
				passwordEncoder.encode("gps"),
				"0102030405",
				"https://commons.wikimedia.org/wiki/File:Hedy_Lamarr_in_The_Heavenly_Body_1944.jpg?uselang=fr",
				"HedyLamarr@mail.com");
		UserDto user2 = new UserDto("cyrano",
				passwordEncoder.encode("nez"),
				"0102030405",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Coquelin_ain%C3%A9.jpg/250px-Coquelin_ain%C3%A9.jpg?uselang=fr",
				"cyrano@mail.com");
		UserDto user3 = new UserDto("a",
				passwordEncoder.encode("a"),
				"0102030405",
				"https://www.goines.net/acra_book/acra_content/files/sequence%20letters/sequence_a.jpg",
				"a@mail.com");
		UserDto user4 = new UserDto("GaryLarson",
				passwordEncoder.encode("fearoftheduck"),
				"0122222222",
				"https://i.pinimg.com/originals/eb/f4/37/ebf43735149fd6ed1e7d260234264080.jpg",
				"fearoftheduck@mail.com");
		userRestController.add(user1);
		userRestController.add(user2);
		userRestController.add(user3);
		userRestController.add(user4);

	}
}