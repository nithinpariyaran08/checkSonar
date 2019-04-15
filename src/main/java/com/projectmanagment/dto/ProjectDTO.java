package com.projectmanagment.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectmanagment.config.DateSerializer;

public class ProjectDTO {

	private long projectId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String projectName;
	private String projectManager;
	@JsonSerialize(using = DateSerializer.class)
	private Date projectStartDate;
	@JsonSerialize(using = DateSerializer.class)
	private Date projectEndDate;
	private int projectPriority;
	private String projectStatus;
	private String projectRemarks;
	private int noOfTask;
	
	private List<String> actions;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserDTO userDTO;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public int getProjectPriority() {
		return projectPriority;
	}

	public void setProjectPriority(int projectPriority) {
		this.projectPriority = projectPriority;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public int getNoOfTask() {
		return noOfTask;
	}

	public void setNoOfTask(int noOfTask) {
		this.noOfTask = noOfTask;
	}

	public String getProjectRemarks() {
		return projectRemarks;
	}

	public void setProjectRemarks(String projectRemarks) {
		this.projectRemarks = projectRemarks;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}


	
}
