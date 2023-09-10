package com.example.projetfilrouge_G2;

import com.example.projetfilrouge_G2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class FilRougeApplication {

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		return sessionFactory;
	}

//	@Autowired
//	private UserRepository userRepo;

	public static void main(String[] args) {SpringApplication.run(FilRougeApplication.class, args);}



//	@Override
//	public void run(String... args) throws Exception {}


	//	private TicketRepository ticketRepository;
//	private UserRepository userRepository;
//	public void TicketService(TicketRepository ticketRepository){
//		this.ticketRepository = ticketRepository;
//	}
//
//	public void UserService(UserRepository userRepository){
//		this.userRepository = userRepository;
//	}


}
