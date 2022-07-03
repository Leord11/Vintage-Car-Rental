package com.leord.car_rental.admin.cars.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.leord.car_rental.admin.cars.models.Cars;
import com.leord.car_rental.admin.cars.repositories.CarsRepository;

@Service
public class CarServices {

	@Autowired
	private CarsRepository carsRepository;
	
	//Get All Cars
	public List<Cars> findAll() {
		return carsRepository.findAll();
	}
	
	//Add New Car
	/*
	 * public void save(Cars car) { carsRepository.save(car); }
	 */
	
	public void update(MultipartFile file, Cars car) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains("..")) {
			System.out.println("Not an image");
		}
		
		try {
			car.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		carsRepository.save(car);
	}
	
	//save image
	public void saveAll(MultipartFile file, String make, String model, String price, String topspeed, String acceleration, String seats, String engine, String details) {
		
		Cars car = new Cars();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains("..")) {
			System.out.println("Not an image");
		}
		
		try {
			car.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		car.setMake(make);
		car.setModel(model);
		car.setPrice(price);
		car.setTopspeed(topspeed);
		car.setAcceleration(acceleration);
		car.setSeats(seats);
		car.setEngine(engine);
		car.setDetails(details);
		
		carsRepository.save(car);
		
	}
	
	//Get Car by Id 
	public Cars findById(int id) {
		return carsRepository.findById(id).orElse(null);
	}
	
	//Delete Car 
	public void delete(int id) {
		carsRepository.deleteById(id);
	}
	
}
