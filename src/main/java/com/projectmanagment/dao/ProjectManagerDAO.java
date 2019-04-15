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
import com.projectmanagment.dto.ProjectDTO;
import com.projectmanagment.dto.UserDTO;
import com.projectmanagment.entity.Project;
import com.projectmanagment.entity.User;

@Transactional
@Repository
public class ProjectManagerDAO {

	String COLLECTION_NAME_PROJECT = "project_counter";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDAO sequenceDao;

	public ProjectDTO addProject(ProjectDTO projectDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(projectDTO.getUserDTO().getEmpId()));
		User user = mongoTemplate.findOne(query, User.class);
		Project project = new Project();
		project.setId(sequenceDao.getNextSequenceId(COLLECTION_NAME_PROJECT));
		project.setProjectName(projectDTO.getProjectName());
		project.setProjectManager(projectDTO.getUserDTO().getName());
		project.setProjectStartDate(projectDTO.getProjectStartDate());
		project.setProjectEndDate(projectDTO.getProjectEndDate());
		project.setProjectPriority(projectDTO.getProjectPriority());
		project.setProjectStatus(projectDTO.getProjectStatus());
		project.setUser(user);
		mongoTemplate.save(project);
		projectDTO.getUserDTO().setName(user.getName());
		projectDTO.getUserDTO().setDateOfBirth(user.getDateOfBirth());
		return projectDTO;
	}

	public List<ProjectDTO> getAllProjects() {
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		List<Project> projects = mongoTemplate.findAll(Project.class);
		for (Project project : projects) {
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setProjectId(project.getId());

			projectDTO.setProjectName(project.getProjectName());
			projectDTO.setProjectManager(project.getUser().getName());
			projectDTO.setProjectEndDate(project.getProjectEndDate());
			projectDTO.setProjectStartDate(project.getProjectStartDate());
			projectDTO.setProjectPriority(project.getProjectPriority());
			projectDTO.setProjectStatus(project.getProjectStatus());

			UserDTO userDTO = new UserDTO();
			userDTO.setEmpId(project.getUser().getId());
			userDTO.setName(project.getUser().getName());
			userDTO.setDateOfBirth(project.getUser().getDateOfBirth());
			projectDTO.setUserDTO(userDTO);
			getAvailableActions(project,projectDTO);
			projectDTOs.add(projectDTO);
		}
		return projectDTOs;
	}

	public ProjectDTO updateProject(ProjectDTO projectDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(projectDTO.getProjectId()));
		Project project = mongoTemplate.findOne(query, Project.class);
		project.setProjectName(projectDTO.getProjectName());
		project.setProjectManager(projectDTO.getUserDTO().getName());
		project.setProjectStartDate(projectDTO.getProjectStartDate());
		project.setProjectEndDate(projectDTO.getProjectEndDate());
		project.setProjectPriority(projectDTO.getProjectPriority());
		project.setProjectStatus(projectDTO.getProjectStatus());
		mongoTemplate.save(project);
		return projectDTO;
	}

	public ProjectDTO deleteProject(ProjectDTO projectDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(projectDTO.getProjectId()));
		DeleteResult deleteResult = mongoTemplate.remove(query, Project.class);
		projectDTO.setProjectRemarks(deleteResult.toString());
		return projectDTO;
	}
	
	private void getAvailableActions(Project project, ProjectDTO projectDTO) {
		ArrayList<String> actionlist = new ArrayList<String>(
				Arrays.asList("Edit", "Delete"));
		if(project.getProjectStatus() == "C"){
			actionlist.remove("Edit");
		}
		projectDTO.setActions(actionlist);
	}

}
