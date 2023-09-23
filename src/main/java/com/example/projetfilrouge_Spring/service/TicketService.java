package com.example.projetfilrouge_Spring.service;

import com.example.projetfilrouge_Spring.controller.model.TicketDto;
import com.example.projetfilrouge_Spring.repository.TicketRepository;
import com.example.projetfilrouge_Spring.repository.TransactionRepository;
import com.example.projetfilrouge_Spring.repository.UserRepository;
import com.example.projetfilrouge_Spring.repository.entity.Ticket;
import com.example.projetfilrouge_Spring.repository.entity.Transaction;
import com.example.projetfilrouge_Spring.repository.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository,
                         TransactionRepository transactionRepository,
                         UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<TicketDto> findAll() {
        List<Ticket> allTickets = ticketRepository.findAll();
        List<TicketDto> allTicketDto = new ArrayList<>();
        for (Ticket ticket: allTickets) {
            TicketDto ticketDto = new TicketDto(Optional.ofNullable(ticket));
            allTicketDto.add(ticketDto);
        }
        return allTicketDto;
    }

    @Transactional
    public ResponseEntity<TicketDto> purchase(Long ticketId){
        // Retrieve currently authenticated User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername).get();

        // get Ticket entity to access linked Transaction
        Ticket purchasedTicket = ticketRepository.findById(ticketId).get();
        Transaction currentTransaction = purchasedTicket.getTransaction();

        if( currentTransaction.getCompleted() ){
            // signal if that purchasedTicket is already sold (transaction completed) ==> should be filtered before
            throw new IllegalStateException("Selected ticket was already sold.");
        }else{
            currentTransaction.setDate(LocalDate.now()); // date of creation become date of purchase
            currentTransaction.setCompleted(true); // Transaction isn't available anymore
            currentTransaction.setPurchaseUser(currentUser);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //TODO : test custom Exceptions if issue with previous version
//    @Transactional
//    public void purchase(Long ticketId) {
//        // Retrieve currently authenticated User
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//        User currentUser = userRepository.findByUsername(currentUsername).orElse(null);
//
//        // get Ticket entity to access linked Transaction
//        Ticket purchasedTicket = ticketRepository.findById(ticketId).orElse(null);
//
//        if (currentUser == null) {
//            throw new UserNotFoundException("Current user not found");
//        }
//
//        Transaction currentTransaction = purchasedTicket.getTransaction();
//
//        if (currentTransaction == null) {
//            throw new TransactionNotFoundException("Transaction not found for the selected ticket");
//        }
//
//        if (currentTransaction.getCompleted()) {
//            throw new IllegalStateException("Selected ticket was already sold.");
//        }
//
//        currentTransaction.setDate(LocalDate.now());
//        currentTransaction.setCompleted(true);
//        currentTransaction.setPurchaseUser(currentUser);
//    }


    @Transactional
    public void save(TicketDto ticketDto) {

        // Retrieve currently authenticated User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername).get();

        if (currentUser == null) {
            throw new IllegalStateException("Current user not found");
        }

        System.out.println(">>> currentUser : " + currentUser.toString());

        Ticket ticketToAdd = new Ticket(ticketDto.getDate(),
                ticketDto.getEventName(),
                ticketDto.getEventType(),
                ticketDto.getEventCity(),
                ticketDto.getPrice());
        ticketRepository.save(ticketToAdd);
        Ticket ticketAdded = ticketRepository.findTopByOrderByIdDesc();
        // verification print :
        System.out.println("Last Ticket added : id " + ticketAdded.getId() + " eventName : " + ticketAdded.getEventName());

        Transaction transactionNotCompleted = new Transaction(false, ticketAdded, LocalDate.now());
        transactionRepository.save(transactionNotCompleted);

        // verification print :
        Transaction transactionSelling = transactionRepository.findTopByOrderByIdDesc();
        transactionSelling.setSellingUser(currentUser);
        System.out.println("Last Transaction added : id " + transactionSelling.getId()
                + " - eventName : " + ticketAdded.getEventName()
                + " - ticket_id : " + transactionSelling.getTicket().getId());

        // Check if currentUser is not null before adding to sellingHistory
        if (currentUser != null) {
            // Add the transaction to the user's sellingHistory
            System.out.println("CurrentUser sellingHistory : BEFORE saving new Transaction");
            for (Transaction transaction : currentUser.getSellingHistory()) {
                System.out.println("Transaction id : " + transaction.getId()
                        + " - completed : " + transaction.getCompleted()
                        + " - ticket eventName : " + transaction.getTicket().getEventName());
            }
            System.out.println("======== add new transaction ========");
            currentUser.getSellingHistory().add(transactionSelling);
            // Save the updated user (with transactionAdded added to sellingHistory)
            userRepository.save(currentUser);
            System.out.println("CurrentUser sellingHistory : AFTER saving new Transaction");
            for (Transaction transaction : currentUser.getSellingHistory()) {
                System.out.println("Transaction id : " + transaction.getId()
                        + " - completed : " + transaction.getCompleted()
                        + " - ticket eventName : " + transaction.getTicket().getEventName());
            }
        }
    }
    public List<TicketDto> findTicketByEventNameContainingIgnoreCase(String event) {
        List<Ticket> result = ticketRepository.findTicketByEventNameContainingIgnoreCase(event);
        List<TicketDto> resultTicketDto = new ArrayList<>();
        for (Ticket ticket: result) {
            TicketDto ticketDto = new TicketDto(Optional.ofNullable(ticket));
            resultTicketDto.add(ticketDto);
        }
        return resultTicketDto;
    }

    public Optional<TicketDto> findById(Long id) {
        TicketDto ticketDto = new TicketDto(ticketRepository.findById(id));
        return Optional.of(ticketDto);
    }

    @Transactional
    public void update(Long id, TicketDto ticketDto) {
        // Note : probably not necessary because it's also checked in RestController method
        Ticket ticketToUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with id " + id + " not found"));

        // verification for each attribute that a "null" value would not erase the current value
        // temporary variables to avoid confusion between getters & setters
        LocalDate date = ticketDto.getDate();
        String event = ticketDto.getEventName();
        Float price = ticketDto.getPrice();
        if(date!=null){
            ticketToUpdate.setDate(date);
        }
        if(event!=null){
            ticketToUpdate.setEventName(event);
        }
        if(price!=null){
            ticketToUpdate.setPrice(price);
        }
//        System.out.println("TEMP TicketService : ticketDto = " + ticketDto.toString() );
//        System.out.println("TEMP TicketService : ticketToUpdate = " + ticketToUpdate.toString() );
        ticketRepository.save(ticketToUpdate);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    public TicketDto toDto(Ticket ticket){
        return new TicketDto(Optional.ofNullable(ticket));
    }

    public List<TicketDto> listToDto(List<Ticket> ticketList){
        List<TicketDto> dtoList = new ArrayList<>();
        for (Ticket ticket:ticketList) {
            dtoList.add(toDto(ticket));
        }
        return dtoList;
    }

    public List<Ticket> findIntersection(List<Ticket>... lists) {
        if (lists == null || lists.length == 0) {
            return new ArrayList<>();
        }
        // intersection begins empty
        List<Ticket> intersection = new ArrayList<>();
        // if there is at least one non-empty list, put the content of the 1st list into
        // the intersection
        if (lists.length > 0) {
            intersection.addAll(lists[0]);
        }
        //
        for (int i = 1; i < lists.length; i++) {
            intersection.retainAll(lists[i]);
            // filter instances not already in intersection.
            // Note : could totally remove all results if one criterion isn't satisfied
            // (example : searching for an eventCity with no result in the database)
        }

        return intersection; //return Ticket format
    }


    public List<TicketDto> searchTickets(String eventName, String eventCity, String eventType) {

        //fixme : gérer cas où l'un des champs de recherche n'a pas été rempli
        //if a parameter is empty (the user doesn't wish to use that filter),
        //all the instances are considered to be a match for this selection parameter
        //to call findIntersection() method.
        // Example : if user only wants to search on evenType, the selection result will be
        // findIntersection(findAll(), findAll(), findTicketsByEventTypeIgnoreCase())
        List<Ticket> foundByEventName = StringUtils.isEmpty(eventName)
                ? ticketRepository.findAll()
                : ticketRepository.findTicketByEventNameContainingIgnoreCase(eventName);

        List<Ticket> foundByEventCity = StringUtils.isEmpty(eventCity)
                ? ticketRepository.findAll()
                : ticketRepository.findTicketsByEventCityIgnoreCase(eventCity);

        List<Ticket> foundByEventType = StringUtils.isEmpty(eventType)
                ? ticketRepository.findAll()
                : ticketRepository.findTicketsByEventTypeIgnoreCase(eventType);

        // TODO add conditions to exclude Tickets with date already passed,
        //  and Tickets of already completed Transaction


        // The entities satisfying all the (optional) search criterions are the intersection of separates query results.
        List<Ticket> intersection = findIntersection(foundByEventName, foundByEventCity, foundByEventType);

        // Convert the matching tickets to DTOs
        return listToDto(intersection);
    }
}
