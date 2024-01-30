package tracker.meneger;

import tracker.exceptions.ManagerSaveException;
import tracker.tasks.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileBackedTasksManager extends InMemoryTaskManager{

    public static void main(String[] args) {

        TaskManager fileBackedTasksManager = Managers.getDefaultFile();

        Task task1 = new Task("Задача1", "Описание задачи1");
        Task task2 = new Task("Задача2", "Описание задачи2");

        Epic epic3 = new Epic("Эпик1","Описание epic1");
        SubTask subTask5 = new SubTask("Подзадача1", "Описание подзадачи1", 3);
        SubTask subTask6 = new SubTask("Подзадача2", "Описание подзадачи2", 3);
        SubTask subTask7 = new SubTask("Подзадача3", "Описание подзадачи3", 3);

        Epic epic4 = new Epic("Эпик2","Описание epic2");

        fileBackedTasksManager.createTask(task1); // Создание Task
        fileBackedTasksManager.createTask(task2); // Создание Task1
        fileBackedTasksManager.createEpic(epic3); // Создание Epic3
        fileBackedTasksManager.createEpic(epic4); // Создание Epic4
        fileBackedTasksManager.createSubTask(subTask5); // Создание SubTask5
        fileBackedTasksManager.createSubTask(subTask6); // Создание SubTask6
        fileBackedTasksManager.createSubTask(subTask7); // Создание SubTask7

        fileBackedTasksManager.getListTaskById(task1.getId()); // Вывод Task по id

        fileBackedTasksManager.getListEpicById(epic3.getId()); // Вывод Epic по id
        fileBackedTasksManager.getListEpicById(epic4.getId()); // Вывод Epic по id

        fileBackedTasksManager.getListSubTaskById(subTask5.getId()); // Вывод SubTask по id


        FileBackedTasksManager fileManager = FileBackedTasksManager.loadFromFile(
                new File("src/tracker/files/historyfile.csv"));

        for (Map.Entry<Integer, Task> entry : fileManager.tasks.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        for (Map.Entry<Integer, Epic> entry : fileManager.epics.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        for (Map.Entry<Integer, SubTask> entry : fileManager.subTasks.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println(fileManager.getHistory());
    }

    private final File FILE;

    public FileBackedTasksManager(File FILE){
        this.FILE = FILE;
    }

    private static FileBackedTasksManager loadFromFile(File file){
        FileBackedTasksManager fileManager = new FileBackedTasksManager(file);
        Map<Integer, Task> fileHistory = new HashMap<>();
        List<Integer> idsHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))){
            List<String> taskLines = reader.lines().collect(Collectors.toList());
            for(int i = 1; i < taskLines.size(); i++){
                if(taskLines.get(i).isEmpty() && !taskLines.get(i + 1).isEmpty()){
                    idsHistory = historyFromString(taskLines.get(i + 1));
                    break;
                }
            String[] line = taskLines.get(i).split(",");
                Task task = fromString(line);
                fileHistory.put(task.getId(),task);
                switch (task.getTaskType()){
                    case TASK:
                        fileManager.tasks.put(task.getId(), task);
                        break;
                    case EPIC:
                        fileManager.epics.put(task.getId(), (Epic) task);
                        break;
                    case SUBTASK:
                        fileManager.subTasks.put(task.getId(), (SubTask) task);
                        break;
                }
                if(task.getId() > fileManager.id){
                    fileManager.id = task.getId();
                }
            }
            for (Integer id : idsHistory){
                fileManager.inMemoryHistoryManager.add(fileHistory.get(id));
            }
        } catch (IOException exception){
            throw new ManagerSaveException(exception.getMessage(), exception.getCause());
        }
        return fileManager;
    }

    private static  List<Integer> historyFromString(String value){
        List<Integer> idsHistory = new ArrayList<>();
        String[] line = value.split(",");
        for (String id : line){
            idsHistory.add(Integer.valueOf(id));
        }
        return idsHistory;
    }

    private static Task fromString(String[] line){
        int id = Integer.parseInt(line[0]);
        TaskType taskType = TaskType.valueOf(line[1]);
        String title = line[2];
        Status status = Status.valueOf(line[3]);
        String description = line[4];
        switch (taskType){
            case TASK:
                return new Task(title, description, status, id);
            case EPIC:
                return new Epic(title, description, id, status);
            case SUBTASK:
                int epicId = Integer.parseInt(line[5]);
                return new SubTask(title, description, status, id, epicId);
        }
        return null;
    }

    private void save(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(FILE), StandardCharsets.UTF_8))){
            writer.write("id,type,name,status,description,epic");
            writer.newLine();
            addTasksToFile(writer);
            writer.newLine();
            List<String> ids = new ArrayList<>();
            for(Task task : getHistory()){
                ids.add(String.valueOf(task.getId()));
            }
            writer.write(String.join(",", ids));
        }catch (IOException exception){
            throw new ManagerSaveException(exception.getMessage(), exception.getCause());
        }
    }

    private void addTasksToFile(BufferedWriter writer) throws IOException{
        for(Task task : listAllTask()){
            writer.write(toString(task));
            writer.newLine();
        }
        for(Epic epic : listAllEpic()){
            writer.write(toString(epic));
            writer.newLine();
        }
        for(SubTask subTask : listAllSubTask()){
            writer.write(toString(subTask));
            writer.newLine();
        }
    }

    private String toString(Task task){
        return task.getId() + "," + task.getTaskType() + "," + task.getName() + "," +
                task.getStatus() + "," + task.getDescription();
    }

    private String toString(SubTask subTask){
        return subTask.getId() + "," + subTask.getTaskType() + "," + subTask.getName() + "," +
                subTask.getStatus() + "," + subTask.getDescription() + "," + subTask.getIdEpic();
    }

    @Override
    public Task createTask(Task task) {
        super.createTask(task);
        save();
        return task;
    }

    // создание Epic
    @Override
    public Epic createEpic(Epic epic) {
        super.createEpic(epic);
        save();
        return epic;
    }

    // создание SubTask
    @Override
    public SubTask createSubTask(SubTask subtask) {
        super.createSubTask(subtask);
        save();
        return subtask;
    }

    // Удаление всех Task
    @Override
    public void deleteAllTask(){
        super.deleteAllTask();
        save();
    }

    // Удаление всех Epic
    @Override
    public void deleteAllEpic(){
        super.deleteAllEpic();
        save();
    }

    // Удаление всех SubTask
    @Override
    public void deleteAllSubTask(){
        super.deleteAllSubTask();
        save();
    }

    // Получение Task по id
    @Override
    public Task getListTaskById(int id){
        Task task = super.getListTaskById(id);
        save();
        return task;
    }

    // Получение Epic по id
    @Override
    public Epic getListEpicById(int id){
        Epic epic = super.getListEpicById(id);
        save();
        return epic;
    }

    // Получение SubTask по id
    @Override
    public SubTask getListSubTaskById(int id){
        SubTask subTask = super.getListSubTaskById(id);
        save();
        return subTask;
    }

    // Обновление Task
    @Override
    public void updateTask(Task updateTask){
        super.updateTask(updateTask);
        save();
    }

    // Обновление Epic
    @Override
    public void updateEpic(Epic updateEpic){
        super.updateEpic(updateEpic);
        save();
    }

    // Обновление SubTask
    @Override
    public void updateSubTask(SubTask updateSabTask){
        super.updateSubTask(updateSabTask);
        save();
    }

    // Удаление Task по id
    @Override
    public void deleteTaskId(int id){
        super.deleteTaskId(id);
        save();
    }

    // Удаление Epic по id
    @Override
    public void deleteEpicId(int id){
        super.deleteEpicId(id);
        save();
    }

    // Удаление SubTask по id
    @Override
    public void deleteSubTaskId(int id){
        super.deleteSubTaskId(id);
        save();
    }

    //Проверка статуса
    @Override
    public void checkStatus(int idEpic) {
        super.checkStatus(idEpic);
        save();
    }
}
