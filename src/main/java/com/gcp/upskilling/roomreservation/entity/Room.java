package com.gcp.upskilling.roomreservation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Room {

	// define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="room_id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private int price;
	
	@Column(name="availability")
	private int availability;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Room(int id, String type, int price) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
	}
	
	public Room() {
		
	}
	
	
	
	
}