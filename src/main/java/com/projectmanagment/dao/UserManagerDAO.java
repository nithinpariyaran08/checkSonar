package com.projectmanagment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.client.result.DeleteResult;
import com.projectmanagment.dto.UserDTO;
import com.projectmanagment.entity.User;

@Transactional
@Repository
public class UserManagerDAO {
	
	String COLLECTION_NAME_USER = "user_counter";
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDAO sequenceDao;

	
	public UserDTO addUser(UserDTO userDTO) {
		User user = new User();
		user.setId(sequenceDao.getNextSequenceId(COLLECTION_NAME_USER));
		user.setName(userDTO.getName());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user = mongoTemplate.insert(user);
		userDTO.setEmpId(user.getId());
		return userDTO;
	}

	public List<UserDTO> getAllUsers() {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<User> users = mongoTemplate.findAll(User.class);
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmpId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setDateOfBirth(user.getDateOfBirth());
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	public UserDTO updateUser(UserDTO userDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userDTO.getEmpId()));
		User user = mongoTemplate.findOne(query, User.class);
		user.setName(userDTO.getName());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		mongoTemplate.save(user);
		return userDTO;
	}
	
	
	public UserDTO deleteUser(UserDTO userDTO){
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userDTO.getEmpId()));
		DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
		userDTO.setRemarks(deleteResult.toString());
		return userDTO;
	}
	
}
