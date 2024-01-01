package com.example.CarServiceProject.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.CarServiceProject.bean.Registration;

public interface RegRepo extends CrudRepository<Registration, String>{

}
