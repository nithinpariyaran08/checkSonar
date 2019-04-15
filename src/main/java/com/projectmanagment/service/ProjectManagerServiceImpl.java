package com.projectmanagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagment.dao.ProjectManagerDAO;
import com.projectmanagment.dao.TaskManagerDAO;
import com.projectmanagment.dao.UserManagerDAO;
import com.projectmanagment.dto.IndividualTaskDTO;
import com.projectmanagment.dto.ParentTaskDTO;
import com.projectmanagment.dto.ProjectDTO;
import com.projectmanagment.dto.UserDTO;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

	@Autowired
	private UserManagerDAO userManagerDAO;
	
	@Autowired
	private ProjectManagerDAO projectManagerDAO;
	
	@Autowired
	private TaskManagerDAO taskManagerDAO;

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		return userManagerDAO.addUser(userDTO);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userManagerDAO.getAllUsers();
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		return userManagerDAO.updateUser(userDTO);
	}

	@Override
	public UserDTO deleteUser(UserDTO userDTO) {
		return userManagerDAO.deleteUser(userDTO);
	}

	@Override
	public ProjectDTO addProject(ProjectDTO projectDTO) {
		return projectManagerDAO.addProject(projectDTO);
	}

	@Override
	public ProjectDTO updateProject(ProjectDTO projectDTO) {
		return projectManagerDAO.updateProject(projectDTO);
	}

	@Override
	public List<ProjectDTO> getAllProjects() {
		return projectManagerDAO.getAllProjects();
	}

	@Override
	public IndividualTaskDTO addIndividualTask(IndividualTaskDTO individualTaskDTO) {
		return taskManagerDAO.addTask(individualTaskDTO);
	}

	@Override
	public IndividualTaskDTO updateIndividualTask(IndividualTaskDTO individualTaskDTO) {
		return taskManagerDAO.updateTask(individualTaskDTO);
	}

	@Override
	public List<IndividualTaskDTO> getAllIndividualTasks() {
	      return taskManagerDAO.getAllTask();
	}

	@Override
	public List<ParentTaskDTO> getAllParentTasks() {
		 return taskManagerDAO.getAllParentTask();
	}

	@Override
	public IndividualTaskDTO deleteTask(IndividualTaskDTO individualTaskDTO) {
		return taskManagerDAO.deleteTask(individualTaskDTO);
	}

	@Override
	public ProjectDTO deleteProject(ProjectDTO projectDTO) {
		return projectManagerDAO.deleteProject(projectDTO);
	}

}
