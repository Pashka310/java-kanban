package tracker.meneger;

import tracker.tasks.Task;

import java.util.List;

public interface HistoryManager {
    List<Task> getHistory();
    void add(Task task);
}
