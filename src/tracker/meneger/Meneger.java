package tracker.meneger;

import tracker.tasks.Epic;
import tracker.tasks.SubTask;
import tracker.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Meneger {

    protected int id = 1;

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    // Создание Task
    public Task createTask(Task task) {
        task.setId(id++);
        task.setStatus("NEW");
        tasks.put(task.getId(), task);
        return task;
    }

    // создание Epic
    public Epic createEpic(Epic epic) {
        epic.setId(id++);
        epic.setStatus("NEW");
        epics.put(epic.getId(), epic);
        return epic;
    }

    // создание SubTask
    public SubTask createSubTask(SubTask subtask) {
        subtask.setId(id++);
        subtask.setStatus("NEW");
        Epic epic = epics.get(subtask.getIdEpic());
        if(epic != null) {
            subTasks.put(subtask.getId(), subtask);
            epic.setIdSubTask(subtask.getId());
        }
        return subtask;
    }

    // Удаление всех Task
    public void deleteAllTask(){
        tasks.clear();
    }

    // Удаление всех Epic
    public void deleteAllEpic(){
        for(Epic epic : epics.values()){
            int idEpic = epic.getId();
            ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
            for(Integer idSubTask : idsSubTask) {
                subTasks.remove(idSubTask);
            }
        }
        epics.clear();
    }

    // Удаление всех SubTask
    public void deleteAllSubTask(){
        for(SubTask subTask : subTasks.values()){
            int idEpic = subTask.getIdEpic();
            ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
            idsSubTask.remove((Integer) subTask.getId());
            checkStatus(idEpic);
        }
        subTasks.clear();
    }

    // Получение списка Task
    public ArrayList<Task> listAllTask(){
        return new ArrayList<>(tasks.values());
    }

    // Получение списка Epic
    public ArrayList<Epic> listAllEpic() {
        return new ArrayList<>(epics.values());
    }

    // Получение списка SubTask
    public ArrayList<SubTask> listAllSubTask(){
        return new ArrayList<>(subTasks.values());
    }

    // Получение Task по id
    public Task getListTaskById(int id){
        return tasks.get(id);
    }

    // Получение Epic по id
    public Epic getListEpicById(int id){
        return epics.get(id);
    }

    // Получение SubTask по id
    public SubTask getListSubTaskById(int id){
        return subTasks.get(id);
    }

    // Получение списка всех SubTask определенного Epic
    public ArrayList<SubTask> getListSubTasksByIdEpic(int id) {
        ArrayList<Integer> idsSubTask = epics.get(id).getIdSubTask();
        ArrayList<SubTask> listSubTasksByIdEpic = new ArrayList<>();
        for(int idSubTask : idsSubTask){
            listSubTasksByIdEpic.add(subTasks.get(idSubTask));
        }
        return listSubTasksByIdEpic;
    }

    // Обновление Task
    public void updateTask(Task updateTask){
        tasks.put(updateTask.getId(), updateTask);
    }

    // Обновление Epic
    public void updateEpic(Epic updateEpic){
        epics.put(updateEpic.getId(), updateEpic);
    }

    // Обновление SubTask
    public void updateSubTask(SubTask updateSabTask){
        subTasks.put(updateSabTask.getId(), updateSabTask);
        int idEpic = subTasks.get(updateSabTask.getId()).getIdEpic();
        checkStatus(idEpic);
    }

    // Удаление Task по id
    public void deleteTaskId(int id){
        tasks.remove(id);
    }

    // Удаление Epic по id
    public void deleteEpicId(int id){
        ArrayList<Integer> idSubTask = epics.get(id).getIdSubTask();
        for(Integer idSubtask : idSubTask){
            subTasks.remove(idSubtask);
        }
        epics.remove(id);
    }

    // Удаление SubTask по id
    public void deleteSubTaskId(int id){
        int idEpic = subTasks.get(id).getIdEpic();
        ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
        idsSubTask.remove((Integer) id);
        subTasks.remove(id);
        checkStatus(idEpic);
    }

    //Проверка статуса
    public void checkStatus(int idEpic) {
        int statusNEW = 0;
        int statusDONE = 0;
        ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
        for (Integer idSubTask : idsSubTask) {
            if (subTasks.get(idSubTask).getStatus().equals("NEW")) {
                statusNEW++;
            } else if (subTasks.get(idSubTask).getStatus().equals("DONE")) {
                statusDONE++;
            }
        }
        if (idsSubTask.size() == statusNEW || idsSubTask.isEmpty()) {
            epics.get(idEpic).setStatus("NEW");
        } else if (idsSubTask.size() == statusDONE) {
            epics.get(idEpic).setStatus("DONE");
        } else {
            epics.get(idEpic).setStatus("IN_PROGRESS");
        }
    }
}