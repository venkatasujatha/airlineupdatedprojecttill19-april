package com.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.user.model.IndicomAirlines;
import com.user.model.Passenger;
import com.user.model.Ticket;
import com.user.repository.AirlinesRepository;
import com.user.repository.PassengerRepository;
import com.user.repository.TicketRepository;

@Service
public class AirlineServiceImpl<E> implements AirlineServiceInterface {

	@Autowired
	private AirlinesRepository airlineRepo;

	@Autowired
	private PassengerRepository prepo;
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public IndicomAirlines saveTicket(IndicomAirlines ticket) {

		return airlineRepo.save(ticket);
	}

	@Override
	public List<IndicomAirlines> getFlightPassangers(String flightName) {
		List<IndicomAirlines> list = airlineRepo.findByflightName(flightName);

		List<IndicomAirlines> l = list.stream().filter(i -> i.getFlightName().contains("INDI34E"))
				.collect(Collectors.toList());

		List<Ticket> tick = new ArrayList();

		for (IndicomAirlines ia : l) {
			tick.addAll(ia.getTickets());
		}
		return list;

	}

	
	@Override
	public List<IndicomAirlines> getList(String flightName) {
		List<IndicomAirlines> list1 = airlineRepo.findByflightName(flightName);

		List<IndicomAirlines> collect = list1.stream().filter(i -> i.getFlightName().contains("INDI67G"))
				.collect(Collectors.toList());
		List<Ticket> t = new ArrayList();

		for (IndicomAirlines collect1 : list1) {
			t.addAll(collect1.getTickets());
		}

		return list1;
	}

	

	@Override
	public List<IndicomAirlines> fList(IndicomAirlines indicomAirlines) {
		List<IndicomAirlines> list=airlineRepo.findAll();
	
		return list;
	}

	@Override
	public List<Passenger> sortByAge() {
		
		List<Passenger> list2 = prepo.findByage();
		
		return list2;
	}

	@Override
	public List<Passenger> servhByTicSortByAge() {
		List<Passenger> list = prepo.searchByTicSort();
		return list;
	}

	@Override
	public List<Passenger> tickets(String id) 
	{
		List<Ticket> list = ticketRepository.findByTicketId(id);
		
		List<Passenger> list2=null;
		for (Ticket ticket : list) {
			list2 = ticket.getPassengers();
			
		}
		
		Collections.sort(list2, (o1, o2) -> {return o1.getAge()-o2.getAge();});
		
		return list2;
		
	}

	@Override
	public void canceltic() {
	
		/*
		 * Date date = new Date();
		 * 
		 * date= Tue Apr 19 14:46:07 IST 2022; System.out.println(date);
		 */
	//	LocalDateTime ldt=2022-04-19T14:54:54.563039943;

		LocalDateTime ldt=LocalDateTime.now();
		System.out.println(ldt);
		LocalDateTime minusDays = ldt.minusDays(1);
		System.out.println(minusDays);
		
		List<Ticket> list = ticketRepository.findAll();
		for (Ticket ticket : list) {
			System.out.println(ticket.getArrivalDateAndTime());
			Date a = ticket.getArrivalDateAndTime();
			//ldt.minus()
		}
		
		
		
	}


	@Override
	public String deletebyId(Long id) {
		//List<Ticket> list = ticketRepository.findByTicketId(id);
		prepo.deleteById(id);
		return "";
	}


}
