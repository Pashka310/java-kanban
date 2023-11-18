import java.util.ArrayList;
import java.util.HashMap;

public class Meneger {

    protected int id = 1;

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();

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
        epics.clear();
    }

    // Удаление всех SubTask
    public void deleteAllSubTask(){
        subTasks.clear();
    }

    // Получение списка Task
    public void listAllTask(){
        for(Task task : tasks.values()){
            System.out.println(task);
        }
    }

    // Получение списка Epic
    public void listAllEpic() {
        for(Epic epic : epics.values()){
            System.out.println(epic);
        }
    }

    // Получение списка SubTask
    public void listAllSubTask(){
        for(SubTask subtask : subTasks.values()){
            System.out.println(subtask);
        }
    }

    // Получение Task по id
    public void getListTaskById(int id){
        System.out.println(tasks.get(id));
    }

    // Получение Epic по id
    public void getListEpicById(int id){
        System.out.println(epics.get(id));
    }

    // Получение SubTask по id
    public void getListSubTaskById(int id){
        System.out.println(subTasks.get(id));
    }

    // Получение списка всех SubTask определенного Epic
    public HashMap<Integer, SubTask> getListSubTasksByIdEpic(int id) {
        ArrayList<Integer> idsSubTask = epics.get(id).getIdSubTask();
        HashMap<Integer, SubTask> listSubTasksByIdEpic = new HashMap<>();
        for(int idSubTask : idsSubTask){
            listSubTasksByIdEpic.put(idSubTask, subTasks.get(idSubTask));
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
        ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
        for(Integer idSubTask : idsSubTask){
            if(subTasks.get(idSubTask).getStatus().equals("NEW") || idsSubTask.isEmpty()){
                Epic epicUpdateStatus = new Epic(epics.get(idEpic).getName(),
                        epics.get(idEpic).getDescription(),
                        "NEW",
                        epics.get(idEpic).getId(),
                        epics.get(idEpic).getIdSubTask());
                epics.put(idEpic, epicUpdateStatus);
            } else if (subTasks.get(idSubTask).getStatus().equals("DONE")) {
                Epic epicUpdateStatus = new Epic(epics.get(idEpic).getName(),
                        epics.get(idEpic).getDescription(),
                        "DONE",
                        epics.get(idEpic).getId(),
                        epics.get(idEpic).getIdSubTask());
                epics.put(idEpic, epicUpdateStatus);
            } else {
                Epic epicUpdateStatus = new Epic(epics.get(idEpic).getName(),
                        epics.get(idEpic).getDescription(),
                        "IN_PROGRESS",
                        epics.get(idEpic).getId(),
                        epics.get(idEpic).getIdSubTask());
                epics.put(idEpic, epicUpdateStatus);
            }
        }
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
        for(Integer idSubTask : idsSubTask){
            if(subTasks.get(idSubTask).getStatus().equals("NEW") || idsSubTask.isEmpty()){
                Epic epicUpdateStatus = new Epic(epics.get(idEpic).getName(),
                        epics.get(idEpic).getDescription(),
                        "NEW",
                        epics.get(idEpic).getId(),
                        epics.get(idEpic).getIdSubTask());
                epics.put(idEpic, epicUpdateStatus);
            } else if (subTasks.get(idSubTask).getStatus().equals("DONE")) {
                Epic epicUpdateStatus = new Epic(epics.get(idEpic).getName(),
                        epics.get(idEpic).getDescription(),
                        "DONE",
                        epics.get(idEpic).getId(),
                        epics.get(idEpic).getIdSubTask());
                epics.put(idEpic, epicUpdateStatus);
            } else {
                Epic epicUpdateStatus = new Epic(epics.get(idEpic).getName(),
                        epics.get(idEpic).getDescription(),
                        "IN_PROGRESS",
                        epics.get(idEpic).getId(),
                        epics.get(idEpic).getIdSubTask());
                epics.put(idEpic, epicUpdateStatus);
            }
        }
    }
}