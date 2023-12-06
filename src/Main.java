import tracker.meneger.Managers;
import tracker.meneger.TaskManager;
import tracker.tasks.Epic;
import tracker.tasks.Status;
import tracker.tasks.SubTask;
import tracker.tasks.Task;

public class Main {
    public static void main(String[] args) {

        TaskManager inMemoryTaskManager = Managers.getDefault();

        Task task1 = new Task("Задача1", "Описание задачи1");
        Task task2 = new Task("Задача2", "Описание задачи2");

        Epic epic3 = new Epic("Эпик","Описание epic");
        SubTask subTask5 = new SubTask("Подзадача", "Описание подзадачи", 3);
        SubTask subTask6 = new SubTask("Подзадача1", "Описание подзадачи1", 3);

        Epic epic4 = new Epic("Эпик1","Описание epic1");
        SubTask subTask7 = new SubTask("Подзадача2", "Описание подзадачи2", 4);

        inMemoryTaskManager.createTask(task1); // Создание Task
        inMemoryTaskManager.createTask(task2); // Создание Task1
        inMemoryTaskManager.createEpic(epic3); // Создание Epic3
        inMemoryTaskManager.createEpic(epic4); // Создание Epic4
        inMemoryTaskManager.createSubTask(subTask5); // Создание SubTask5
        inMemoryTaskManager.createSubTask(subTask6); // Создание SubTask6
        inMemoryTaskManager.createSubTask(subTask7); // Создание SubTask7

        inMemoryTaskManager.listAllTask(); // Получение списка всех Task
        inMemoryTaskManager.listAllEpic(); // Получение списка всех Epic
        inMemoryTaskManager.listAllSubTask(); // Получение списка всех SubTask
        inMemoryTaskManager.deleteAllTask(); // Удаление всех Task
        inMemoryTaskManager.deleteAllEpic();  // Удаление всех Epic
        inMemoryTaskManager.deleteAllSubTask(); // Удаление всех SubTask

        inMemoryTaskManager.getListTaskById(task1.getId()); // Вывод Task по id
        inMemoryTaskManager.getListEpicById(epic3.getId()); // Вывод Epic по id
        inMemoryTaskManager.getListSubTaskById(subTask5.getId()); // Вывод SubTask по id

        // Получение списка SubTask определенного Epic
        System.out.println(inMemoryTaskManager.getListSubTasksByIdEpic(epic4.getId()));

        // Обновление Task
        Task updateTask = new Task("Задача1", "Обновление задачи", Status.IN_PROGRESS, task1.getId());
        inMemoryTaskManager.updateTask(updateTask);

        // Обновление Epic
        Epic updateEpic = new Epic("Эпик", "Обновление epic", epic3.getId(),
                epic3.getIdSubTask());
        inMemoryTaskManager.updateEpic(updateEpic);

        // Обновление SubTask
        SubTask updateSubtaskTask = new SubTask("Подзадача", "Обновление подзадачи",
                Status.IN_PROGRESS, subTask5.getId(), 3);
        inMemoryTaskManager.updateSubTask(updateSubtaskTask);
        inMemoryTaskManager.deleteTaskId(task1.getId()); // Удаление Task по id
        inMemoryTaskManager.deleteEpicId(epic3.getId()); // Удаление Epic по id
        inMemoryTaskManager.deleteSubTaskId(subTask7.getId()); // Удаление SubTask по id

        System.out.println(inMemoryTaskManager.getHistory());//Последние просмотренные пользователем задачи

    }
}