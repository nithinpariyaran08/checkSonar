package com.projectmanagment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projectmanagment.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

}
