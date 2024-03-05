public class Main {
    /*public static void main(String[] args) {

        TaskManager inMemoryTaskManager = Managers.getDefault();

        Task task1 = new Task("Задача1", "Описание задачи1");
        Task task2 = new Task("Задача2", "Описание задачи2");

        Epic epic3 = new Epic("Эпик1","Описание epic1");
        SubTask subTask5 = new SubTask("Подзадача1", "Описание подзадачи1", 3);
        SubTask subTask6 = new SubTask("Подзадача2", "Описание подзадачи2", 3);
        SubTask subTask7 = new SubTask("Подзадача3", "Описание подзадачи3", 3);

        Epic epic4 = new Epic("Эпик2","Описание epic2");

        inMemoryTaskManager.createTask(task1); // Создание Task
        inMemoryTaskManager.createTask(task2); // Создание Task1
        inMemoryTaskManager.createEpic(epic3); // Создание Epic3
        inMemoryTaskManager.createEpic(epic4); // Создание Epic4
        inMemoryTaskManager.createSubTask(subTask5); // Создание SubTask5
        inMemoryTaskManager.createSubTask(subTask6); // Создание SubTask6
        inMemoryTaskManager.createSubTask(subTask7); // Создание SubTask7

        inMemoryTaskManager.getListTaskById(task1.getId()); // Вывод Task по id
        inMemoryTaskManager.getListTaskById(task2.getId()); // Вывод Task по id
        inMemoryTaskManager.getListTaskById(task2.getId()); // Вывод Task по id
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.getListEpicById(epic3.getId()); // Вывод Epic по id
        inMemoryTaskManager.getListEpicById(epic4.getId()); // Вывод Epic по id
        inMemoryTaskManager.getListEpicById(epic4.getId()); // Вывод Epic по id
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.getListSubTaskById(subTask5.getId()); // Вывод SubTask по id
        inMemoryTaskManager.getListSubTaskById(subTask6.getId()); // Вывод SubTask по id
        inMemoryTaskManager.getListSubTaskById(subTask7.getId()); // Вывод SubTask по id
        inMemoryTaskManager.getListSubTaskById(subTask6.getId()); // Вывод SubTask по id
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteTaskId(task1.getId()); // Удаление Task по id
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteSubTaskId(subTask7.getId()); // Удаление SubTask по id
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteEpicId(epic3.getId()); // Удаление Epic по id
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteEpicId(epic4.getId()); // Удаление Epic по id
        System.out.println(inMemoryTaskManager.getHistory());

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
        inMemoryTaskManager.deleteSubTaskId(subTask7.getId()); // Удаление SubTask по id

        System.out.println(inMemoryTaskManager.getHistory());//Последние просмотренные пользователем задачи
    }   */
}