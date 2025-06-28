package com.masai;

import java.time.LocalDate;

public class WorkTask extends Task {
	public WorkTask(int id, String title, String description, Status status, LocalDate dueDate) {
		super(id, title, description, status, dueDate);
	}

	@Override
	public void displayTask() {
		System.out.println("Work Task -> ID: " + id + ", Title: " + title + ", Description: " + description
				+ ", Status: " + status + ", Due: " + dueDate);
	}
}
