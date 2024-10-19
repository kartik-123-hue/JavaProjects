package org.Demo;

import org.Demo.Repository.TaskRepository;
import org.Demo.Service.TaskService;

import java.io.IOException;
import java.util.Scanner;

public class CommandLineInterface {
    public static void main(String[] args) {
        TaskService taskService=new TaskService();
        TaskRepository taskRepository=new TaskRepository();
        Scanner sc=new Scanner(System.in);
        System.out.println("MENU");
        System.out.print("1. add\n2. update\n3. status\n4. delete\n5. list\n");
        String command="";
        while(!command.equals("exit")) {
            System.out.print("task cli: ");
            command = sc.next();
            switch (command) {
                case "add":
                    try {
                        String description=sc.nextLine();
                        taskService.add(description);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "update":
                    try {
                        int taskId=sc.nextInt();
                        String description=sc.nextLine();
                        taskService.update(taskId, description);
                    } catch (Exception e) {
                        System.out.printf(e.getMessage());
                    }
                    break;
                case "delete":
                    int taskId=sc.nextInt();
                    taskService.delete(taskId);
                    break;
                case "list":
                    try {
                        taskService.allTask();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "status":
                    switch (sc.next().toLowerCase()) {
                        case "update":
                            try {
                                int i = sc.nextInt();
                                String status = sc.nextLine().trim();
                                taskRepository.updateTaskStatus(i, status);
                            } catch (IOException e) {
                                System.out.printf(e.getMessage());
                            }
                            break;
                        case "inprogress":
                            try {
                                taskRepository.allInProgressTask();
                            } catch (IOException e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "completed":
                            try {
                                taskRepository.allCompletedTask();
                            } catch (IOException e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "todo":
                            try {
                                taskRepository.allToDoTask();
                            } catch (IOException e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                    }
                    break;
            }
        }
    }
}