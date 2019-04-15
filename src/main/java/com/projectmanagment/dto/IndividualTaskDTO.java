package com.projectmanagment.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectmanagment.config.DateSerializer;

public class IndividualTaskDTO {
	
	//the primary key needs to be primitive
	private long id;

	private String taskName;

	private int priority;

	@JsonSerialize(using = DateSerializer.class)
	private Date startDate;

	@JsonSerialize(using = DateSerializer.class)
	private Date endDate;

	private String taskStatus;
	
	private String remarks;
	
	private String parentTasks;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ParentTaskDTO parentTaskDTO;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserDTO userDTO;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProjectDTO projectDTO;
	
	
	private List<String> actions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getParentTasks() {
		return parentTasks;
	}

	public void setParentTasks(String parentTasks) {
		this.parentTasks = parentTasks;
	}

	public ParentTaskDTO getParentTaskDTO() {
		return parentTaskDTO;
	}

	public void setParentTaskDTO(ParentTaskDTO parentTaskDTO) {
		this.parentTaskDTO = parentTaskDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	
	
	

}
