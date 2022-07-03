package com.leord.car_rental.user.rent.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leord.car_rental.user.rent.models.Rentals;

@Repository
public interface RentRepository extends JpaRepository<Rentals, Integer> {

	List<Rentals> findByStatus(String status);

	List<Rentals> findByUserid(Integer userid);
}
