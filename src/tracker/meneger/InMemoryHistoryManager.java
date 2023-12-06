package tracker.meneger;

import tracker.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private int SIZE_HISTORY = 10;
    private List<Task> history = new ArrayList<>();

    @Override
    public List<Task> getHistory(){
        return List.copyOf(history);
    }

    @Override
    public void add(Task task) {
        if(task != null) {
            if (history.size() > SIZE_HISTORY) {
                history.remove(0);
            }
            history.add(task);
        }
    }
}
