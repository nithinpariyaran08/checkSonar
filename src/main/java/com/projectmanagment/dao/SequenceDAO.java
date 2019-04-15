package com.projectmanagment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.projectmanagment.entity.DatabaseSequence;
@Repository
public class SequenceDAO {
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
    public long getNextSequenceId(String counterName){
    	Query query = new Query(Criteria.where("name").is(counterName));
        Update update = new Update().inc("sequence", 1);
        DatabaseSequence counter = mongoTemplate.findAndModify(query, update, DatabaseSequence.class); // return old Counter object
        return counter.getSequence();
        
    }
}
