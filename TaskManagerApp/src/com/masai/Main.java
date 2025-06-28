package com.masai;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TaskManager manager = new TaskManager();

		while (true) {
			System.out.println("\n--- Task Manager ---");
			System.out.println("1. Add Task");
			System.out.println("2. View All Tasks");
			System.out.println("3. Search Task by ID");
			System.out.println("4. Update Task Status");
			System.out.println("5. Delete Task");
			System.out.println("6. Filter by Status");
			System.out.println("7. Sort by Due Date");
			System.out.println("8. Exit");
			System.out.print("Choose: ");
			int choice = sc.nextInt();
			sc.nextLine();

			try {
				switch (choice) {
				case 1:
					System.out.print("Enter title: ");
					String title = sc.nextLine();
					System.out.print("Enter description: ");
					String desc = sc.nextLine();
					System.out.print("Enter due date (YYYY-MM-DD): ");
					LocalDate dueDate = LocalDate.parse(sc.nextLine());
					System.out.print("Is this Work or Personal task? ");
					String type = sc.nextLine();
					int id = manager.generateId();

					Task task = type.equalsIgnoreCase("Work") ? new WorkTask(id, title, desc, Status.PENDING, dueDate)
							: new PersonalTask(id, title, desc, Status.PENDING, dueDate);

					manager.addTask(task);
					System.out.println("Task added successfully.");
					break;

				case 2:
					List<Task> tasks = manager.getAllTasks();
					if (tasks.isEmpty()) {
						System.out.println("No any task is available right now.");

					} else {
						tasks.forEach(Task::displayTask);
					}

					break;

				case 3:
					System.out.print("Enter task ID: ");
					int sid = sc.nextInt();
					manager.searchTaskById(sid).ifPresentOrElse(Task::displayTask,
							() -> System.out.println("Task not found."));
					break;

				case 4:
					System.out.print("Enter task ID: ");
					int uid = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter new status (PENDING/COMPLETED): ");
					String status = sc.nextLine();
					manager.updateTaskStatus(uid, status);
					System.out.println("Status updated.");
					break;

				case 5:
					System.out.print("Enter task ID to delete: ");
					int did = sc.nextInt();
					manager.removeTask(did);
					System.out.println("Task deleted.");
					break;

				case 6:
					System.out.print("Enter status to filter (PENDING/COMPLETED): ");
					String s = sc.nextLine();
					manager.filterByStatus(Status.valueOf(s.toUpperCase())).forEach(Task::displayTask);
					break;

				case 7:
					manager.sortByDueDate().forEach(Task::displayTask);
					break;

				case 8:
					System.out.println("Exiting...");
					System.exit(0);

				default:
					System.out.println("Invalid choice.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
