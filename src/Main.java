
public class Main {
    public static void main(String[] args) {

        Meneger meneger = new Meneger();

        Task task1 = new Task("Задача1", "Описание задачи1");
        Task task2 = new Task("Задача2", "Описание задачи2");

        Epic epic3 = new Epic("Эпик","Описание epic");
        SubTask subTask5 = new SubTask("Подзадача", "Описание подзадачи", 3);
        SubTask subTask6 = new SubTask("Подзадача1", "Описание подзадачи1", 3);

        Epic epic4 = new Epic("Эпик1","Описание epic1");
        SubTask subTask7 = new SubTask("Подзадача2", "Описание подзадачи2", 4);

        meneger.createTask(task1); // Создание Task
        meneger.createTask(task2); // Создание Task1
        meneger.createEpic(epic3); // Создание Epic3
        meneger.createEpic(epic4); // Создание Epic4
        meneger.createSubTask(subTask5); // Создание SubTask5
        meneger.createSubTask(subTask6); // Создание SubTask6
        meneger.createSubTask(subTask7); // Создание SubTask7

        meneger.listAllTask(); // Получение списка всех Task
        meneger.listAllEpic(); // Получение списка всех Epic
        meneger.listAllSubTask(); // Получение списка всех SubTask
        meneger.deleteAllTask(); // Удаление всех Task
        meneger.deleteAllEpic();  // Удаление всех Epic
        meneger.deleteAllSubTask(); // Удаление всех SubTask
        meneger.getListTaskById(task1.getId()); // Вывод Task по id
        meneger.getListEpicById(epic3.getId()); // Вывод Epic по id
        meneger.getListSubTaskById(subTask5.getId()); // Вывод SubTask по id

        // Получение списка SubTask определенного Epic
        System.out.println(meneger.getListSubTasksByIdEpic(epic4.getId()));

        // Обновление Task
        Task updateTask = new Task("Задача1", "Обновление задачи", "IN_PROGREES", task1.getId());
        meneger.updateTask(updateTask);

        // Обновление Epic
        Epic updateEpic = new Epic("Эпик", "Обновление epic", epic3.getStatus(), epic3.getId(),
                epic3.getIdSubTask());
        meneger.updateEpic(updateEpic);

        // Обновление SubTask
        SubTask updateSubtaskTask = new SubTask("Подзадача", "Обновление подзадачи",
                "IN_PROGRESS", subTask5.getId(), 4);
        meneger.updateSubTask(updateSubtaskTask);

        meneger.deleteTaskId(task1.getId()); // Удаление Task по id
        meneger.deleteEpicId(epic3.getId()); // Удаление Epic по id
        meneger.deleteSubTaskId(subTask7.getId()); // Удаление SubTask по id
    }
}