import java.util.List;
import java.util.ArrayList;
/**
 * Created by grizzly on 3/14/15.
 */

// опис черги
public class cpu_queue {
    private List<CPUProcess> list;
    private int maxSize;

    cpu_queue(int maxSize) {
        this.maxSize = maxSize;
        list = new ArrayList <> ();
    }

    int size() {
        return list.size();
    }

    boolean isEmpty() {
        return list.isEmpty();
    }

    boolean isFull() {
        return list.size() == maxSize;
    }

    void push(CPUProcess item) {
        list.add(item);
    }

    CPUProcess pop() {
        if(this.isEmpty()) {
            throw new RuntimeException("List is empty");
        }

        CPUProcess item = list.get(0);
        list.remove(0);
        return item;
    }
}
