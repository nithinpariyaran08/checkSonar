package com.projectmanagment.dto;

public class ParentTaskDTO {
	
	private long id;
	private String parentTask;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	@Override
	public boolean equals(Object obj) {
		ParentTaskDTO parentTaskDTO = (ParentTaskDTO) obj;
		if (this.parentTask.equals(parentTaskDTO.getParentTask())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((parentTask == null ? 0 : parentTask.hashCode()));
		return 0;
	}

}
