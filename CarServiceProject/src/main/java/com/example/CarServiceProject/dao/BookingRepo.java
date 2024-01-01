package com.example.CarServiceProject.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.CarServiceProject.bean.Booking_Details;

public interface BookingRepo extends CrudRepository<Booking_Details, Integer> {

}
