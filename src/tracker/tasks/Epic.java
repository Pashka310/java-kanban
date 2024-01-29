package tracker.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    protected List<Integer> idSubTask;

    public Epic(String name, String description, int id, List<Integer> idSubTask) {
        super(name, description, id);
        idSubTask = new ArrayList<>();
    }

    public Epic(String name, String description, int id, Status status) {
        super(name, description, status, id);
    }

    public Epic(String name, String description) {
        super(name, description);
        idSubTask = new ArrayList<>();
    }

    public List<Integer> getIdSubTask() {
        return idSubTask;
    }

    public void setIdSubTask(int id) {
        idSubTask.add(id);
    }

    public TaskType getTaskType(){
        return TaskType.EPIC;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Epic epic = (Epic) object;
        return Objects.equals(idSubTask, epic.idSubTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSubTask);
    }
}