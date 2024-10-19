package org.Demo.Repository;


import org.Demo.Modal.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskRepository {
    public int returnAndIncrementCount(){
        try (Scanner reader=new Scanner(new FileReader("src/main/resources/CurrentNumberOfTask/count.txt"))){
            String count=String.valueOf(reader.nextLine());
            incrementAndSave(Integer.parseInt(count)+1);
            return Integer.parseInt(count);
        }catch (IOException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    private void incrementAndSave(int i) {
        try(FileWriter fileWriter=new FileWriter("src/main/resources/CurrentNumberOfTask/count.txt")) {
            fileWriter.write(String.valueOf(i));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void savetask(Task task) {
        try (FileWriter fileWriter=new FileWriter("src/main/resources/currentlyRunningTask/"+String.valueOf(task.getTaskId())+".json")){
            fileWriter.write(convertToJson(task));
            fileWriter.flush();
        }catch (IOException e){
            System.out.printf(e.getMessage());
        }
    }

    private String convertToJson(Task task) {
        return "{\n" +
                "\"id\":" + task.getTaskId() + ",\n" +
                "\"description\":\"" + task.getTaskDescription() + "\",\n" +
                "\"status\":\"" + task.getStatus() + "\",\n" +
                "\"createdTime\":\"" + task.getCreatedAt() + "\",\n" +
                "\"updatedTime\":\"" + task.getModifiedBy() + "\"\n" +
                "}";
    }

    public void updateTask(int taskId,String updatedescription) throws IOException{
        Scanner jsonbody=new Scanner(new FileReader("src/main/resources/currentlyRunningTask/"+String.valueOf(taskId)+".json"));
        String data="";
        while (jsonbody.hasNextLine()) {
            data += jsonbody.nextLine();
        }
        Task updateTaskDetails=updateJson(data,"",updatedescription);
        jsonbody.close();
        savetask(updateTaskDetails);
    }

    private Task updateJson(String body,String status,String updatedescription) {
        Task task=new Task();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        String[] attributemap=body.replaceAll("\"", "").replace("{","").replace("}","").split(",");
        for(String str:attributemap){
            String key=str.substring(0,str.indexOf(':'));
            String value=str.substring(str.indexOf(':')+1,str.length());
            switch (key){
                case "id":task.setTaskId(Integer.valueOf(value)); break;
                case "description":
                    if(!updatedescription.isEmpty()){task.setTaskDescription(updatedescription);}
                    else {task.setTaskDescription(value);}
                    break;
                case "status":
                    if(!status.isEmpty()){task.setStatus(status);}
                    else{task.setStatus(value);}
                    break;
                case "createdTime":task.setCreatedAt(value);break;
                case "updatedTime":task.setModifiedBy(simpleDateFormat.format(date));break;
                default:System.exit(0);
            }
        }
        return task;
    }

    public void updateTaskStatus(int taskId, String status) throws IOException {
        Scanner jsonbody=new Scanner(new FileReader("src/main/resources/currentlyRunningTask/"+String.valueOf(taskId)+".json"));
        String data="";
        while (jsonbody.hasNextLine()) {
            data += jsonbody.nextLine();
        }
        Task updateTaskDetails=updateJson(data,status,"");
        jsonbody.close();
        savetask(updateTaskDetails);
    }

    public void allInProgressTask() throws IOException{
        File dir=new File("src/main/resources/currentlyRunningTask");
        for(File file:dir.listFiles()){
            String data="";
            Scanner displayFilecontent=new Scanner(new FileReader(file));
            while (displayFilecontent.hasNextLine()){
                data+=displayFilecontent.nextLine()+"\n";
            }
            if(data.contains("inprogress")){
                System.out.println(data);
            }
        }
    }

    public void allCompletedTask() throws IOException{
        File dir=new File("src/main/resources/currentlyRunningTask");
        for(File file:dir.listFiles()){
            String data="";
            Scanner displayFilecontent=new Scanner(new FileReader(file));
            while (displayFilecontent.hasNextLine()){
                data+=displayFilecontent.nextLine()+"\n";
            }
            if(data.contains("completed")){
                System.out.println(data);
            }
        }
    }

    public void allToDoTask() throws IOException{
        File dir=new File("src/main/resources/currentlyRunningTask");
        for(File file:dir.listFiles()){
            String data="";
            Scanner displayFilecontent=new Scanner(new FileReader(file));
            while (displayFilecontent.hasNextLine()){
                data+=displayFilecontent.nextLine()+"\n";
            }
            if(data.contains("to-do")){
                System.out.println(data);
            }
        }
    }
}
