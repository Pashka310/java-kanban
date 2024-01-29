package tracker.meneger;

import java.io.File;

public class Managers {

    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory(){
        return  new InMemoryHistoryManager();
    }

    public static TaskManager getDefaultFile(){
        return new FileBackedTasksManager(new File("src/tracker/files/historyfile.csv"));
    }
}
