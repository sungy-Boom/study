import java.util.*;

/**
 * @author guiyong
 * @date 2022/5/12 14:33
 */
class Task {
    int nodeIndex;
    Task next;

    public Task() {
    }

    public Task(int index) {
        this.nodeIndex = index;
    }

    public static Task generate(int[] arr) {
        Task head = new Task(-1);
        Task tail = head;
        for (int i : arr) {
            tail.next = new Task(i);
            tail = tail.next;
        }

        return head.next;
    }

    public static Task generate(List<Integer> arr) {
        Task head = new Task(-1);
        Task tail = head;
        for (int i : arr) {
            tail.next = new Task(i);
            tail = tail.next;
        }

        return head.next;
    }

    public static void print(Task task) {
        while (task != null) {
            System.out.println(task.nodeIndex);
            task = task.next;
        }
    }
}

class Task2 {
    int nodeIndex;
    Task2 left;
    Task2 right;

    public Task2() {
    }

    public Task2(int index) {
        this.nodeIndex = index;
    }

    public static Task2 generate(int[] arr) {

        return null;
    }
}

public class Main6 {
    public static void main(String[] args) {
        /*task1 1,2,3,4
            2 3,4,5
            4 5*/
        Map<Integer, Task> taskMap = new HashMap<>();
        Task head = Task.generate(new int[]{1, 2, 3, 4});

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> taskList = new ArrayList<>();
        taskList.addAll(Arrays.asList(1, 2, 3, 4));
        list.add(taskList);
        taskList = new ArrayList<>(Arrays.asList(3, 4, 5));
        list.add(taskList);
        taskList = new ArrayList<>(Arrays.asList(5));
        list.add(taskList);

        for (List<Integer> integers : list) {
            Task t = Task.generate(integers);
            //taskMap.put()
        }


        head = new Main6().reverseTask(head);
        Task.print(head);
    }

    public Task reverseTask(Task task) {
        if (task == null || task.next == null) {
            return task;
        }
        Task res = null;
        Task tmp = null;
        while (task != null) {
            Task originNext = task.next;
            res = task;
            res.next = tmp;
            tmp = res;
            task = originNext;
        }
        return res;
    }
}
