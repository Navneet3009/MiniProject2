package com.masai;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager implements TaskOperations {
	List<Task> tasks = new ArrayList<>();
	Map<Integer, Task> taskMap = new HashMap<>();
	int idCounter = 1;

	public int generateId() {
		return idCounter++;
	}

	@Override
	public void addTask(Task task) {
		tasks.add(task);
		taskMap.put(task.getId(), task);
	}

	@Override
	public void removeTask(int id) throws TaskNotFoundException {
		Task task = taskMap.get(id);
		if (task == null)
			throw new TaskNotFoundException("Task ID not found.");
		tasks.remove(task);
		taskMap.remove(id);
	}

	@Override
	public void updateTaskStatus(int id, String statusStr) throws TaskNotFoundException {
		Task task = taskMap.get(id);
		if (task == null)
			throw new TaskNotFoundException("Task ID not found.");
		task.setStatus(Status.valueOf(statusStr.toUpperCase()));
	}

	@Override
	public List<Task> getAllTasks() {
		return tasks;
	}

	@Override
	public Optional<Task> searchTaskById(int id) {
		return Optional.ofNullable(taskMap.get(id));
	}

	public List<Task> filterByStatus(Status status) {
		return tasks.stream().filter(task -> task.getStatus() == status).collect(Collectors.toList());
	}

	public List<Task> sortByDueDate() {
		return tasks.stream().sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
	}
}
