import java.util.ArrayList;

public class MaxHeap {

    private ArrayList<Long> values;

    public MaxHeap() {
        values = new ArrayList<Long>();
        values.add(null);
    }

    public void insert(long n) {
        Long newVal = n;

        values.add(null);
        int index = values.size() - 1;

        while (index > 1 && getParent(index).compareTo(newVal) < 0) {
            values.set(index, getParent(index));
        }

        values.set(index, newVal);
    }

    public long deleteMax() {
        Long maximum = values.get(1);

        int lastIndex = values.size() - 1;
        Long last = values.remove(lastIndex);

        if (lastIndex > 1) {
            values.set(1, last);
            maxHeapify();
        }

        return maximum;
    }

    private void maxHeapify() {
        Long root = values.get(1);
        int lastIndex = values.size() - 1;

        int index = 1;
        boolean more = true;
        while (more) {
            int childIndex = getLeftChildIndex(index);

            if (childIndex <= lastIndex) {
                Long child = getLeftChild(index);

                if (getRightChildIndex(index) <= lastIndex && getRightChild(index).compareTo(child) > 0) {
                    childIndex = getRightChildIndex(index);
                    child = getRightChild(index);
                }

                if (child.compareTo(root) > 0) {
                    values.set(index, child);
                    index = childIndex;
                } else {
                    more = false;
                }
            } else {
                more = false;
            }
        }

        values.set(index, root);
    }

    public int size() { return values.size() - 1; }

    private Long getParent(int index) { return values.get(index / 2); }

    private static int getParentIndex(int index) { return index / 2; } //unused but left for future debugging

    private Long getLeftChild(int index) { return values.get(2 * index); }

    private static int getLeftChildIndex(int index) { return 2 * index; }

    private Long getRightChild(int index) { return values.get(2 * index + 1); }

    private static int getRightChildIndex(int index) { return 2 * index + 1; }
}
