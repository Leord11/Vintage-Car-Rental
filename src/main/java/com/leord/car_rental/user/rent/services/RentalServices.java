package com.leord.car_rental.user.rent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leord.car_rental.user.rent.models.Rentals;
import com.leord.car_rental.user.rent.repositories.RentRepository;

@Service
public class RentalServices {

	@Autowired
	private RentRepository rentRepository;
	
	
	public List<Rentals> findAll() {
		
		return rentRepository.findAll();
	}
	
	public Rentals findById(int id) {
		
		return rentRepository.findById(id).orElse(null);
	}
	
	public List<Rentals> findByStatus(String status) {
		return rentRepository.findByStatus(status);
	}

	public List<Rentals> findByUserid(int id) {
		return rentRepository.findByUserid(id);
	}
	
	
	public Integer countReservations(int id) {
		int count = 0;
		
		List<Rentals> rentals = rentRepository.findAll();
		
		for(Rentals rent : rentals) {
			
			
			if(rent.getUserid() == id) {
				count++;
				System.out.println(rent.getUserid());
			}
			
		}
		
		return count;
	}

	public Integer count(String status){
		int count = 0;
		
		List<Rentals> rentals = findAll();
		
		for(Rentals rent : rentals) {
			
			
			if(rent.getStatus().equals(status)) {
				count++;
			}
			
		}
		
		
		return count;
	}
	
	public void save(Rentals rent) {
		
		Integer initialTotal = rent.getTotal();
		Long msDiff = rent.getReturndate().getTime() - rent.getPickupdate().getTime();
		Long day = msDiff/86400000;
		Integer multiplier = day < 1 ? 1 : day.intValue() ;

		Integer total = initialTotal * multiplier;
		
		rent.setTotal(total);
	
		rentRepository.save(rent);
	}
	
	public void confirm(Rentals rent) {
		
		rentRepository.save(rent);
	}
	
	public void delete(int id) {
		rentRepository.deleteById(id);
	}
}

