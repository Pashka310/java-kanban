package tracker.meneger;

import tracker.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> history = new HashMap<>();

    private static class Node {
        public Task task;
        public Node next;
        public Node prev;

        public Node(Node prev, Task task, Node next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

    public Node head;
    public Node tail;

    @Override
    public List<Task> getHistory() {
        return getTask();
    }

    @Override
    public void add(Task task) {
        int taskId = task.getId();
        remove(taskId);
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        removeNode(id);
    }

    public void linkLast(Task task) {
        final Node oldTail = tail;
        final Node newNode = new Node(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        history.put(task.getId(), newNode);
    }

    private void removeNode(Node node) {
        final Node prev = node.prev;
        final Node next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
    }

    private void removeNode(int id) {
        if (history.containsKey(id)) {
            removeNode(history.get(id));
            history.remove(id);
        }
    }

    private List<Task> getTask() {
        List<Task> tasks = new ArrayList<>();
        for (Node node = head; node != null; node = node.next) {
            tasks.add(node.task);
        }
        return tasks;
    }
}