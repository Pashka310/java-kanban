package tracker.tasks;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    protected ArrayList<Integer> idSubTask;

    public Epic(String name, String description, String status, int id, ArrayList<Integer> idSubTask) {
        super(name, description, null, id);
        idSubTask = new ArrayList<>();
        this.status = status;
    }

    public Epic(String name, String description) {
        super(name, description);
        idSubTask = new ArrayList<>();
    }

    public ArrayList<Integer> getIdSubTask() {
        return idSubTask;
    }

    public void setIdSubTask(int id) {
        idSubTask.add(id);
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