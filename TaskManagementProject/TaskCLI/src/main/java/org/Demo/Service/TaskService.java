package org.Demo.Service;

import org.Demo.Modal.Task;
import org.Demo.Repository.TaskRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskService {
    Scanner sc=new Scanner(System.in);
    TaskRepository taskRepository=new TaskRepository();
    public void add(String trim) throws Exception{
        Task task=new Task();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        task.setTaskDescription(trim);
        while (task.getTaskDescription().isEmpty()){
            System.out.print("Please enter the task description:");
            task.setTaskDescription(sc.nextLine());
        }
        task.setTaskId(taskRepository.returnAndIncrementCount());
        task.setStatus("to-do");
        task.setCreatedAt(simpleDateFormat.format(date));
        task.setModifiedBy(simpleDateFormat.format(date));
        taskRepository.savetask(task);
    }

    public void update(int taskid,String description) throws  IOException{
        taskRepository.updateTask(taskid,description);
    }

    public void allTask() throws Exception{
        File dir=new File("src/main/resources/currentlyRunningTask");
        for(File file:dir.listFiles()){
            String data="";
            Scanner displayFilecontent=new Scanner(new FileReader(file));
            while (displayFilecontent.hasNextLine()){
                data+=displayFilecontent.nextLine()+"\n";
            }
            System.out.println(data);
        }
    }

    public void delete(int taskId) {
        File file=new File("src/main/resources/currentlyRunningTask/"+String.valueOf(taskId)+".json");
        if (file.delete()){
            System.out.println("TASK DELETED");
        }
        else {
            System.out.println("TASK NOT FOUND");
        }

    }

    public void status(int i) {
//        System.out.print("1. updateStatus\n2. allTaskInProgress\n3. allTaskCompleted\n4. allTaskToDo\n");
//        System.out.print("Please Enter the operation to perform:");
//        switch (sc.next().toLowerCase()){
//            case "update":
//                try {
//                    String status= sc.nextLine();
//                    taskRepository.updateTaskStatus(i,status);
//                }catch (IOException e){
//                    System.out.printf(e.getMessage());
//                }
//                break;
//            case "inprogress":
//                try {
//                     taskRepository.allInProgressTask();
//                }catch (IOException e){
//                     System.out.print(e.getMessage());
//                }
//                break;
//            case "completed":
//                try {
//                    taskRepository.allCompletedTask();
//                }catch (IOException e){
//                    System.out.print(e.getMessage());
//                }
//                break;
//            case "todo":
//                try {
//                    taskRepository.allToDoTask();
//                }catch (IOException e){
//                    System.out.print(e.getMessage());
//                }
//                break;
//        }
    }

}
