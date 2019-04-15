package com.projectmanagment.config;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagment.dto.IndividualTaskDTO;
import com.projectmanagment.dto.ParentTaskDTO;
import com.projectmanagment.dto.ProjectDTO;
import com.projectmanagment.dto.UserDTO;
import com.projectmanagment.service.ProjectManagerService;

@RestController
public class ProjectManagerController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectManagerService projectManagerService;

	/**
	 * Add user
	 * 
	 * @param userDTO
	 * @return
	 */
	@RequestMapping(value = "/api/v1/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
		logger.info("inside add user" + userDTO);
		userDTO = projectManagerService.addUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	/**
	 * The method to update the user
	 * 
	 * @param userDTO
	 * @return
	 */

	@RequestMapping(value = "/api/v1/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
		logger.info("inside add user" + userDTO);
		userDTO = projectManagerService.updateUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	/**
	 * The method to get all the user
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/v1/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllUser() {
		logger.info("inside get all user");
		List<UserDTO> userDTOs = projectManagerService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/v1/deleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(id);
		logger.info("inside deleteUser" + userDTO);
		userDTO = projectManagerService.deleteUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);

	}

	/**
	 * Add user
	 * 
	 * @param userDTO
	 * @return
	 */
	@RequestMapping(value = "/api/v1/addProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> addProject(@Valid @RequestBody ProjectDTO projectDTO) {
		logger.info("inside add project" + projectDTO);
		projectDTO = projectManagerService.addProject(projectDTO);
		return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.OK);
	}

	/**
	 * The method to update the user
	 * 
	 * @param userDTO
	 * @return
	 */

	@RequestMapping(value = "/api/v1/updateProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
		logger.info("inside update project" + projectDTO);
		projectDTO = projectManagerService.updateProject(projectDTO);
		return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/v1/deleteProject/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ProjectDTO> deleteProject(@PathVariable("id") long id) {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectId(id);
		logger.info("inside deleteProject" + projectDTO);
		projectDTO = projectManagerService.deleteProject(projectDTO);
		return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.OK);

	}

	/**
	 * The method to get all the user
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/v1/getAllProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getAllProjects() {
		logger.info("inside get all user");
		List<ProjectDTO> projectDTOs = projectManagerService.getAllProjects();
		return new ResponseEntity<List<ProjectDTO>>(projectDTOs, HttpStatus.OK);
	}

	/**
	 * Add user
	 * 
	 * @param userDTO
	 * @return
	 */
	@RequestMapping(value = "/api/v1/addTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IndividualTaskDTO> addIndividualTask(
			@Valid @RequestBody IndividualTaskDTO individualTaskDTO) {
		logger.info("inside add task" + individualTaskDTO);
		individualTaskDTO = projectManagerService.addIndividualTask(individualTaskDTO);
		return new ResponseEntity<IndividualTaskDTO>(individualTaskDTO, HttpStatus.OK);
	}

	/**
	 * The method to update the tasks
	 * 
	 * @param userDTO
	 * @return
	 */

	@RequestMapping(value = "/api/v1/updateTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IndividualTaskDTO> updateIndividualTask(
			@Valid @RequestBody IndividualTaskDTO individualTaskDTO) {
		logger.info("inside add task" + individualTaskDTO);
		individualTaskDTO = projectManagerService.updateIndividualTask(individualTaskDTO);
		return new ResponseEntity<IndividualTaskDTO>(individualTaskDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/v1/deleteTask/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<IndividualTaskDTO> deleteTask(@PathVariable("id") long id) {
		IndividualTaskDTO individualTaskDTO = new IndividualTaskDTO();
		individualTaskDTO.setId(id);
		logger.info("inside deleteTask" + individualTaskDTO);
		individualTaskDTO = projectManagerService.deleteTask(individualTaskDTO);
		return new ResponseEntity<IndividualTaskDTO>(individualTaskDTO, HttpStatus.OK);

	}


	/**
	 * The method to get all the tasks
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/v1/getAllTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<IndividualTaskDTO>> getAllInvidualTasks() {
		logger.info("inside add task");
		List<IndividualTaskDTO> individualTaskDTOs = projectManagerService.getAllIndividualTasks();
		return new ResponseEntity<List<IndividualTaskDTO>>(individualTaskDTOs, HttpStatus.OK);
	}
	
	
	/**
	 * The method to get all the tasks
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/v1/getAllParentTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ParentTaskDTO>> getAllParentTasks() {
		logger.info("inside add task");
		List<ParentTaskDTO> parentTaskDTOs = projectManagerService.getAllParentTasks();
		return new ResponseEntity<List<ParentTaskDTO>>(parentTaskDTOs, HttpStatus.OK);
	}

}
