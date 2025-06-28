package com.masai;

import java.time.LocalDate;

public abstract class Task {
	int id;
	String title;
	String description;
	Status status;
	LocalDate dueDate;

	public Task(int id, String title, String description, Status status, LocalDate dueDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public abstract void displayTask();
}
