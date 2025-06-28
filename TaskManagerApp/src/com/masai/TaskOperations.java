package com.masai;

import java.util.*;

public interface TaskOperations {
	void addTask(Task task);

	void removeTask(int id) throws TaskNotFoundException;

	void updateTaskStatus(int id, String status) throws TaskNotFoundException;

	List<Task> getAllTasks();

	Optional<Task> searchTaskById(int id);
}
