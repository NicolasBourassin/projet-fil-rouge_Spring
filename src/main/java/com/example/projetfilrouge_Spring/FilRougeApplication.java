package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.controller.api.TicketRestController;
import com.example.projetfilrouge_Spring.controller.api.UserRestController;
import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.controller.model.UserDto;
import com.example.projetfilrouge_Spring.repository.TicketRepository;
import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import com.example.projetfilrouge_Spring.repository.entity.User;
import com.example.projetfilrouge_Spring.service.TicketService;
import com.example.projetfilrouge_Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class FilRougeApplication implements CommandLineRunner {
	private UserRestController userRestController;
	private TicketRestController ticketRestController;

	public FilRougeApplication(UserRestController userRestController, TicketRestController ticketRestController) {
		this.userRestController = userRestController;
		this.ticketRestController = ticketRestController;
	}

	public static void main(String[] args) {SpringApplication.run(FilRougeApplication.class, args);}


	@Override
	public void run(String... args) throws Exception {

	}
	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			/*FIXME : TEST NIVEAU RestController : FAIL*/
			UserDto user1 = new UserDto("HedyLamarr", "gps",010203040506L,"https://commons.wikimedia.org/wiki/File:Hedy_Lamarr_in_The_Heavenly_Body_1944.jpg?uselang=fr","notamail@null.com");
			TicketDto ticket1 = new TicketDto(LocalDate.parse("2024-02-24"), "EVENT", 30.30F);

			userRestController.add(user1);
			ticketRestController.add(ticket1);

			//TODO TEST Niveau Service

			//TODO TEST Niveau Repository

		};
	}
}