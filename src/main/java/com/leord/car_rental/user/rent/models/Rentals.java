package com.leord.car_rental.user.rent.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Rentals {
	
	  	@Id
	    @GeneratedValue( strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private Integer id;
	  	
	  	
	  	private String name;
	  	private String phone;
	  	private String email;
	  	
	  	
	  	private String make;
	  	private String model;
	  	private Integer carid;
	  	private Integer total;
	  	
	  	//TODO add userid and status in the form 
	  	private Integer userid;
	  	
	  	private String status = "pending";
	  	
	  	@DateTimeFormat(pattern = "yyyy-MM-dd")
	  	private Date pickupdate;
	  	
	  	@DateTimeFormat(pattern = "yyyy-MM-dd")
	  	private Date returndate;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public Integer getCarid() {
			return carid;
		}

		public void setCarid(Integer carid) {
			this.carid = carid;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Date getPickupdate() {
			return pickupdate;
		}

		public void setPickupdate(Date pickupdate) {
			this.pickupdate = pickupdate;
		}

		public Date getReturndate() {
			return returndate;
		}

		public void setReturndate(Date returndate) {
			this.returndate = returndate;
		}

		public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	  	
	  	
	  	
}
