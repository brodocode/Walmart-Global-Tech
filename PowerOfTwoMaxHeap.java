import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {
    private final List<T> heap;
    private final int numChildren;

    public PowerOfTwoMaxHeap(int numChildren) {
        if (numChildren <= 0) {
            throw new IllegalArgumentException("Number of children must be positive");
        }
        this.heap = new ArrayList<>();
        this.numChildren = numChildren;
    }

    public void insert(T value) {
        heap.add(value);
        bubbleUp(heap.size() - 1);
    }

    public T popMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T max = heap.get(0);
        int lastIdx = heap.size() - 1;
        heap.set(0, heap.get(lastIdx));
        heap.remove(lastIdx);
        bubbleDown(0);
        return max;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }

        int parentIdx = (index - 1) / numChildren;
        if (heap.get(index).compareTo(heap.get(parentIdx)) > 0) {
            swap(index, parentIdx);
            bubbleUp(parentIdx);
        }
    }

    private void bubbleDown(int index) {
        int maxIdx = index;
        int startChildIdx = index * numChildren + 1;
        int endChildIdx = Math.min(startChildIdx + numChildren, heap.size());

        for (int i = startChildIdx; i < endChildIdx; i++) {
            if (heap.get(i).compareTo(heap.get(maxIdx)) > 0) {
                maxIdx = i;
            }
        }

        if (maxIdx != index) {
            swap(index, maxIdx);
            bubbleDown(maxIdx);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
class PowerOfTwoMaxHeapTest {
    public static void main(String[] args) {
        // Test with small value of numChildren
        testPowerOfTwoMaxHeap(2);

        // Test with large value of numChildren
        testPowerOfTwoMaxHeap(4);
    }

    private static void testPowerOfTwoMaxHeap(int numChildren) {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(numChildren);

        // Insert some elements
        heap.insert(20);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);
        heap.insert(25);

        // Test popMax method
        while (!heap.isEmpty()) {
            System.out.print(heap.popMax() + " ");
        }
        System.out.println();
    }
}

