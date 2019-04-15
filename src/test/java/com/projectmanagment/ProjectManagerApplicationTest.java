package com.projectmanagment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.projectmanagment.dto.IndividualTaskDTO;
import com.projectmanagment.dto.ProjectDTO;
import com.projectmanagment.dto.UserDTO;


public class ProjectManagerApplicationTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void addUser() throws Exception {
		String uri = "/api/v1/addUser";
		String inputJson = super.mapToJson(ProjectManagerApplicationTest.createuserDTO());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void updateUser() throws Exception {
		String uri = "/api/v1/updateUser";
		String inputJson = super.mapToJson(ProjectManagerApplicationTest.updateUserDTO());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void getUsersList() throws Exception {
		String uri = "/api/v1/getAllUsers";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		UserDTO[] productlist = super.mapFromJson(content, UserDTO[].class);
		assertTrue(productlist.length > 0);
	}
	
	private static UserDTO createuserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("Nithin");
		userDTO.setDateOfBirth(new Date());
		return userDTO;
	}
	
	private static UserDTO updateUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(29);
		userDTO.setName("Nithin PV");
		userDTO.setDateOfBirth(new Date());
		return userDTO;
	}
	
	
	@Test
	public void addProject() throws Exception {
		String uri = "/api/v1/addProject";
		String inputJson = super.mapToJson(ProjectManagerApplicationTest.createProjectDTO());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void updateProject() throws Exception {
		String uri = "/api/v1/updateProject";
		String inputJson = super.mapToJson(ProjectManagerApplicationTest.updateProjectDTO());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void getProjectsList() throws Exception {
		String uri = "/api/v1/getAllProjects";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ProjectDTO[] projectDTOs = super.mapFromJson(content, ProjectDTO[].class);
		assertTrue(projectDTOs.length > 0);
	}
	
	@Test
	public void deleteProject() throws Exception {
		String uri = "/api/v1/deleteProject/24/";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	
	private static ProjectDTO createProjectDTO() {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectName("ePOS");
		projectDTO.setProjectPriority(1);
		projectDTO.setProjectStatus("S");
		projectDTO.setProjectEndDate(new Date());
		projectDTO.setProjectStartDate(new Date());
		
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(29);
		projectDTO.setUserDTO(userDTO);
		
		return projectDTO;
	}
	
	private static ProjectDTO updateProjectDTO() {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectId(23);
		projectDTO.setProjectName("ePOS 2.0");
		projectDTO.setProjectPriority(1);
		projectDTO.setProjectStatus("S");
		projectDTO.setProjectEndDate(new Date());
		projectDTO.setProjectStartDate(new Date());
		
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(29);
		projectDTO.setUserDTO(userDTO);
		
		return projectDTO;
	}
	
	@Test
	public void addTask() throws Exception {
		String uri = "/api/v1/addTask";
		String inputJson = super.mapToJson(ProjectManagerApplicationTest.createTaskManagerDTO());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void updateTask() throws Exception {
		String uri = "/api/v1/updateTask";
		String inputJson = super.mapToJson(ProjectManagerApplicationTest.updateTaskManagerDTO());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	@Test
	public void getTaskList() throws Exception {
		String uri = "/api/v1/getAllTasks";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		IndividualTaskDTO[] individualTaskDTOs = super.mapFromJson(content, IndividualTaskDTO[].class);
		assertTrue(individualTaskDTOs.length > 0);
	}
	
	/*@Test
	public void deleteTask() throws Exception {
		String uri = "/api/v1/deleteTask/22/";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}*/
	private static IndividualTaskDTO createTaskManagerDTO() {
		IndividualTaskDTO individualTaskDTO = new IndividualTaskDTO();
		individualTaskDTO.setTaskName("UAT");
		individualTaskDTO.setPriority(1);
		individualTaskDTO.setStartDate(new Date());
		individualTaskDTO.setEndDate(new Date());
		individualTaskDTO.setTaskStatus("S");
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(29);
		individualTaskDTO.setUserDTO(userDTO);
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectId(23);
		individualTaskDTO.setProjectDTO(projectDTO);
		return individualTaskDTO;
	}

	private static IndividualTaskDTO updateTaskManagerDTO() {
		
		IndividualTaskDTO individualTaskDTO = new IndividualTaskDTO();
		individualTaskDTO.setId(20);
		individualTaskDTO.setTaskName("Coding & Unit Testing");
		individualTaskDTO.setPriority(1);
		individualTaskDTO.setStartDate(new Date());
		individualTaskDTO.setEndDate(new Date());
		individualTaskDTO.setTaskStatus("S");
		UserDTO userDTO = new UserDTO();
		userDTO.setEmpId(29);
		individualTaskDTO.setUserDTO(userDTO);

		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectId(23);
		individualTaskDTO.setProjectDTO(projectDTO);
		
		return individualTaskDTO;
	}

}
