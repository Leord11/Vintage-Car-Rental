package com.leord.car_rental.admin.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leord.car_rental.admin.cars.models.Cars;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Integer> {

	
}
