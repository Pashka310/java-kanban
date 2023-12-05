package tracker.meneger;

import tracker.tasks.Epic;
import tracker.tasks.Status;
import tracker.tasks.SubTask;
import tracker.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    protected int id = 1;

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    // Создание Task
    @Override
    public Task createTask(Task task) {
        task.setId(id++);
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    // создание Epic
    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(id++);
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    // создание SubTask
    @Override
    public SubTask createSubTask(SubTask subtask) {
        subtask.setId(id++);
        subtask.setStatus(Status.NEW);
        Epic epic = epics.get(subtask.getIdEpic());
        if(epic != null) {
            subTasks.put(subtask.getId(), subtask);
            epic.setIdSubTask(subtask.getId());
        }
        return subtask;
    }

    // Удаление всех Task
    @Override
    public void deleteAllTask(){
        tasks.clear();
    }

    // Удаление всех Epic
    @Override
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
    @Override
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
    @Override
    public ArrayList<Task> listAllTask(){
        return new ArrayList<>(tasks.values());
    }

    // Получение списка Epic
    @Override
    public ArrayList<Epic> listAllEpic() {
        return new ArrayList<>(epics.values());
    }

    // Получение списка SubTask
    @Override
    public ArrayList<SubTask> listAllSubTask(){
        return new ArrayList<>(subTasks.values());
    }

    // Получение Task по id
    @Override
    public Task getListTaskById(int id){
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    // Получение Epic по id
    @Override
    public Epic getListEpicById(int id){
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    // Получение SubTask по id
    @Override
    public SubTask getListSubTaskById(int id){
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    // Получение списка всех SubTask определенного Epic
    @Override
    public ArrayList<SubTask> getListSubTasksByIdEpic(int id) {
        ArrayList<Integer> idsSubTask = epics.get(id).getIdSubTask();
        ArrayList<SubTask> listSubTasksByIdEpic = new ArrayList<>();
        for(int idSubTask : idsSubTask){
            listSubTasksByIdEpic.add(subTasks.get(idSubTask));
        }
        return listSubTasksByIdEpic;
    }

    // Обновление Task
    @Override
    public void updateTask(Task updateTask){
        tasks.put(updateTask.getId(), updateTask);
    }

    // Обновление Epic
    @Override
    public void updateEpic(Epic updateEpic){
        epics.put(updateEpic.getId(), updateEpic);
    }

    // Обновление SubTask
    @Override
    public void updateSubTask(SubTask updateSabTask){
        subTasks.put(updateSabTask.getId(), updateSabTask);
        int idEpic = subTasks.get(updateSabTask.getId()).getIdEpic();
        checkStatus(idEpic);
    }

    // Удаление Task по id
    @Override
    public void deleteTaskId(int id){
        tasks.remove(id);
    }

    // Удаление Epic по id
    @Override
    public void deleteEpicId(int id){
        ArrayList<Integer> idSubTask = epics.get(id).getIdSubTask();
        for(Integer idSubtask : idSubTask){
            subTasks.remove(idSubtask);
        }
        epics.remove(id);
    }

    // Удаление SubTask по id
    @Override
    public void deleteSubTaskId(int id){
        int idEpic = subTasks.get(id).getIdEpic();
        ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
        idsSubTask.remove((Integer) id);
        subTasks.remove(id);
        checkStatus(idEpic);
    }

    //Проверка статуса
    @Override
    public void checkStatus(int idEpic) {
        int statusNEW = 0;
        int statusDONE = 0;
        ArrayList<Integer> idsSubTask = epics.get(idEpic).getIdSubTask();
        for (Integer idSubTask : idsSubTask) {
            if (subTasks.get(idSubTask).getStatus().equals(Status.NEW)) {
                statusNEW++;
            } else if (subTasks.get(idSubTask).getStatus().equals(Status.DONE)) {
                statusDONE++;
            }
        }
        if (idsSubTask.size() == statusNEW || idsSubTask.isEmpty()) {
            epics.get(idEpic).setStatus(Status.NEW);
        } else if (idsSubTask.size() == statusDONE) {
            epics.get(idEpic).setStatus(Status.DONE);
        } else {
            epics.get(idEpic).setStatus(Status.IN_PROGRESS);
        }
    }

    //Последние просмотренные пользователем задачи
    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();
    }
}