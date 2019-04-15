package com.projectmanagment.service;

import java.util.List;

import com.projectmanagment.dto.IndividualTaskDTO;
import com.projectmanagment.dto.ParentTaskDTO;
import com.projectmanagment.dto.ProjectDTO;
import com.projectmanagment.dto.UserDTO;

public interface ProjectManagerService {

	public UserDTO addUser(UserDTO userDTO);
	
	public List<UserDTO> getAllUsers();

	public UserDTO updateUser(UserDTO userDTO);

	public UserDTO deleteUser(UserDTO userDTO);

	public ProjectDTO addProject(ProjectDTO projectDTO);

	public ProjectDTO updateProject(ProjectDTO projectDTO);

	public List<ProjectDTO> getAllProjects();

	public IndividualTaskDTO addIndividualTask(IndividualTaskDTO individualTaskDTO);

	public IndividualTaskDTO updateIndividualTask(IndividualTaskDTO individualTaskDTO);

	public List<IndividualTaskDTO> getAllIndividualTasks();

	public List<ParentTaskDTO> getAllParentTasks();

	public IndividualTaskDTO deleteTask(IndividualTaskDTO individualTaskDTO);

	public ProjectDTO deleteProject(ProjectDTO projectDTO);

}
