package com.gcp.upskilling.roomreservation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="reservation")
public class Reservation {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="room_number")
	private int roomNum;
	
	@Column(name="username")
	private String username;
	
	@Column(name="room_id")
	private int roomId;

	public int getRoom_num() {
		return roomNum;
	}

	public void setRoom_num(int room_num) {
		this.roomNum = room_num;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Reservation(int room_num, String username, int roomId) {
		super();
		this.roomNum = room_num;
		this.username = username;
		this.roomId = roomId;
	}
	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public Reservation() {
		
	}
	
}

	