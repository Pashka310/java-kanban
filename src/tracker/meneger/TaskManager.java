package tracker.meneger;

import tracker.tasks.Epic;
import tracker.tasks.SubTask;
import tracker.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    // Создание Task
    Task createTask(Task task);

    // создание Epic
    Epic createEpic(Epic epic);

    // создание SubTask
    SubTask createSubTask(SubTask subtask);

    // Удаление всех Task
    void deleteAllTask();

    // Удаление всех Epic
    void deleteAllEpic();

    // Удаление всех SubTask
    void deleteAllSubTask();

    // Получение списка Task
    ArrayList<Task> listAllTask();

    // Получение списка Epic
    ArrayList<Epic> listAllEpic();

    // Получение списка SubTask
    ArrayList<SubTask> listAllSubTask();

    // Получение Task по id
    Task getListTaskById(int id);

    // Получение Epic по id
    Epic getListEpicById(int id);

    // Получение SubTask по id
    SubTask getListSubTaskById(int id);

    // Получение списка всех SubTask определенного Epic
    ArrayList<SubTask> getListSubTasksByIdEpic(int id);

    // Обновление Task
    void updateTask(Task updateTask);

    // Обновление Epic
    void updateEpic(Epic updateEpic);

    // Обновление SubTask
    void updateSubTask(SubTask updateSabTask);

    // Удаление Task по id
    void deleteTaskId(int id);

    // Удаление Epic по id
    void deleteEpicId(int id);

    // Удаление SubTask по id
    void deleteSubTaskId(int id);

    //Проверка статуса
    void checkStatus(int idEpic);

    //Последние просмотренные пользователем задачи
    List<Task> getHistory();
}
