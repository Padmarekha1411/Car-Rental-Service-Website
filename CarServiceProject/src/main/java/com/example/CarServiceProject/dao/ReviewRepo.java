package com.example.CarServiceProject.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.CarServiceProject.bean.Reviews;

public interface ReviewRepo extends CrudRepository<Reviews, Integer>{

}
