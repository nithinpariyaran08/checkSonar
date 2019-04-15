package com.projectmanagment.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.client.result.DeleteResult;
import com.projectmanagment.dto.IndividualTaskDTO;
import com.projectmanagment.dto.ParentTaskDTO;
import com.projectmanagment.dto.ProjectDTO;
import com.projectmanagment.dto.UserDTO;
import com.projectmanagment.entity.IndividualTask;
import com.projectmanagment.entity.ParentTask;
import com.projectmanagment.entity.Project;
import com.projectmanagment.entity.User;

@Transactional
@Repository
public class TaskManagerDAO {

	String COLLECTION_NAME_TASK = "task_counter";
	String COLLECTION_NAME_PARENT_TASK = "parent_task_counter";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDAO sequenceDao;

	public IndividualTaskDTO addTask(IndividualTaskDTO individualTaskDTO) {
		IndividualTask individualTask = new IndividualTask();
		Query projectQuery = new Query();
		projectQuery.addCriteria(Criteria.where("id").is(individualTaskDTO.getProjectDTO().getProjectId()));
		Project project = mongoTemplate.findOne(projectQuery, Project.class);

		Query userQuery = new Query();
		userQuery.addCriteria(Criteria.where("id").is(individualTaskDTO.getUserDTO().getEmpId()));
		User user = mongoTemplate.findOne(userQuery, User.class);

		if (null != individualTaskDTO.getParentTaskDTO()) {
			ParentTask parentTask = new ParentTask();
			parentTask.setId(sequenceDao.getNextSequenceId(COLLECTION_NAME_PARENT_TASK));
			parentTask.setParentTask(individualTaskDTO.getParentTaskDTO().getParentTask());
			parentTask = mongoTemplate.save(parentTask);
			individualTask.setParentTask(parentTask);
		} else {
			ParentTask parentTask = new ParentTask();
			parentTask.setId(sequenceDao.getNextSequenceId(COLLECTION_NAME_PARENT_TASK));
			parentTask.setParentTask(individualTaskDTO.getTaskName());
			parentTask = mongoTemplate.save(parentTask);
		}

		individualTask.setId(sequenceDao.getNextSequenceId(COLLECTION_NAME_TASK));
		individualTask.setTaskName(individualTaskDTO.getTaskName());
		individualTask.setTaskStatus(individualTaskDTO.getTaskStatus());
		individualTask.setTaskEndDate(individualTaskDTO.getEndDate());
		individualTask.setTaskStartDate(individualTaskDTO.getStartDate());
		individualTask.setTaskPriority(individualTaskDTO.getPriority());
		individualTask.setProject(project);
		individualTask.setUser(user);

		mongoTemplate.save(individualTask);
		populateIndividualTaskDTO(individualTask, individualTaskDTO);
		return individualTaskDTO;
	}
	
	
	
	public IndividualTaskDTO updateTask(IndividualTaskDTO individualTaskDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(individualTaskDTO.getId()));
		IndividualTask individualTask = mongoTemplate.findOne(query, IndividualTask.class);
		individualTask.setTaskName(individualTaskDTO.getTaskName());
		individualTask.setTaskPriority(individualTaskDTO.getPriority());
		individualTask.setTaskEndDate(individualTaskDTO.getEndDate());
		individualTask.setTaskStartDate(individualTaskDTO.getStartDate());
		individualTask.setTaskStatus(individualTaskDTO.getTaskStatus());
	
		if(individualTaskDTO.getParentTaskDTO() != null){
			Query parentQuery = new Query();
			parentQuery.addCriteria(Criteria.where("id").is(individualTaskDTO.getParentTaskDTO().getId()));
			ParentTask parentTask = mongoTemplate.findOne(query, ParentTask.class);
			individualTask.setParentTask(parentTask);	
		}
		
		
		
		Query projectQuery = new Query();
		projectQuery.addCriteria(Criteria.where("id").is(individualTaskDTO.getProjectDTO().getProjectId()));
		Project project = mongoTemplate.findOne(projectQuery, Project.class);
		individualTask.setProject(project);
		
		Query userQuery = new Query();
		userQuery.addCriteria(Criteria.where("id").is(individualTaskDTO.getUserDTO().getEmpId()));
		User user = mongoTemplate.findOne(userQuery, User.class);
		individualTask.setUser(user);
		
		mongoTemplate.save(individualTask);
		
		populateIndividualTaskDTO(individualTask,individualTaskDTO);
		return individualTaskDTO;
	}
	
	
	

	public List<IndividualTaskDTO> getAllTask() {
		List<IndividualTaskDTO> individualTaskDTOs = new ArrayList<>();
		List<IndividualTask> individualTasks = mongoTemplate.findAll(IndividualTask.class);
		for (IndividualTask individualTask : individualTasks) {
			IndividualTaskDTO individualTaskDTO = new IndividualTaskDTO();

			populateIndividualTaskDTO(individualTask, individualTaskDTO);
			individualTaskDTOs.add(individualTaskDTO);
		}
		return individualTaskDTOs;
	}
	
	
	
	public List<ParentTaskDTO> getAllParentTask() {
		List<ParentTaskDTO> parentTaskDTOs = new ArrayList<>();
		List<IndividualTask> individualTasks = mongoTemplate.findAll(IndividualTask.class);
		for (IndividualTask individualTask : individualTasks) {
			ParentTaskDTO parentTaskDTO = new ParentTaskDTO();
			parentTaskDTO.setId(individualTask.getId());
			parentTaskDTO.setParentTask(individualTask.getTaskName());
			parentTaskDTOs.add(parentTaskDTO);
		}
		return parentTaskDTOs;
	}

	private void populateIndividualTaskDTO(IndividualTask individualTask, IndividualTaskDTO individualTaskDTO) {
		individualTaskDTO.setId(individualTask.getId());
		individualTaskDTO.setTaskName(individualTask.getTaskName());
		individualTaskDTO.setTaskStatus(individualTask.getTaskStatus());
		individualTaskDTO.setEndDate(individualTask.getTaskEndDate());
		individualTaskDTO.setStartDate(individualTask.getTaskStartDate());
		individualTaskDTO.setPriority(individualTask.getTaskPriority());
		ParentTaskDTO parentTaskDTO = new ParentTaskDTO();
		if (individualTask.getParentTask() != null) {
			parentTaskDTO.setId(individualTask.getParentTask().getId());
			parentTaskDTO.setParentTask(individualTask.getParentTask().getParentTask());
			individualTaskDTO.setParentTaskDTO(parentTaskDTO);
		}
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectId(individualTask.getProject().getId());
		projectDTO.setProjectName(individualTask.getProject().getProjectName());
		projectDTO.setProjectManager(individualTask.getUser().getName());
		projectDTO.setProjectPriority(individualTask.getProject().getProjectPriority());
		projectDTO.setProjectStartDate(individualTask.getProject().getProjectEndDate());
		projectDTO.setProjectEndDate(individualTask.getProject().getProjectEndDate());
		individualTaskDTO.setProjectDTO(projectDTO);
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(individualTask.getUser().getId());
		userDTO.setName(individualTask.getUser().getName());
		userDTO.setDateOfBirth(individualTask.getUser().getDateOfBirth());
		getAvailableActions(individualTask,individualTaskDTO);
		individualTaskDTO.setUserDTO(userDTO);
	}

	public IndividualTaskDTO deleteTask(IndividualTaskDTO individualTaskDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(individualTaskDTO.getId()));
		DeleteResult deleteResult = mongoTemplate.remove(query, IndividualTask.class);
		individualTaskDTO.setRemarks(deleteResult.toString());
		return individualTaskDTO;
	}

	private void getAvailableActions(IndividualTask individualTask, IndividualTaskDTO individualTaskDTO) {
		ArrayList<String> actionlist = new ArrayList<String>(
				Arrays.asList("Edit", "Delete"));
		if(individualTask.getTaskStatus() == "C"){
			actionlist.remove("Edit");
		}
		individualTaskDTO.setActions(actionlist);
	}
	

}
