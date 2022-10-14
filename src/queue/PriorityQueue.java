package queue;

import java.util.Comparator;

/** default: 최소 힙 */
public class PriorityQueue<E> implements Queue<E> {

    private final Comparator<? super E> comparator;
    private final static int CAPACITY = 11;
    private int size;
    private E[] heap;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        heap = (E[]) new Object[CAPACITY];
        this.comparator = comparator;
    }

    @Override
    public void add(E e) {
        resize();
        int idx = size + 1;

        while (idx > 1) {
            int parentIdx = getParent(idx);
            E parent = heap[parentIdx];

            if (compare(parent, e) <= 0)
                break;

            heap[idx] = parent;
            idx = parentIdx;
        }

        heap[idx] = e;
        size++;
    }

    private int compare(E e1, E e2) {
        if (comparator == null) {
            Comparable<? super E> comp = (Comparable<? super E>) e1;
            return comp.compareTo(e2);
        }

        return comparator.compare(e1, e2);
    }

    @Override
    public E poll() {
        resize();
        if (size == 0)
            return null;

        E result = heap[1];
        E tail = heap[size];

        heap[size--] = null;
        int idx = 1;
        heap[idx] = tail;

        while (getLeftChild(idx) <= size) {
            int leftChild = getLeftChild(idx);
            int rightChild = getRightChild(idx);
            int target = leftChild;

            if (rightChild <= size && compare(heap[rightChild], heap[leftChild]) < 0)
                target = rightChild;

            E child = heap[target];
            if (compare(heap[idx], child) <= 0)
                break;

            heap[target] = heap[idx];
            heap[idx] = child;
            idx = target;
        }

        return result;
    }

    @Override
    public E peek() {
        return size == 0 ? null : heap[1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int capacity;
        if (heap.length <= size + 1)
            capacity = heap.length * 2;
        else if (CAPACITY < heap.length && size < heap.length / 4)
            capacity = heap.length / 2;
        else
            return;

        E[] newHeap = (E[]) new Object[capacity];
        for (int i = 1; i <= size; i++)
            newHeap[i] = heap[i];
        heap = newHeap;
    }

    private int getParent(int idx) {
        return idx / 2;
    }

    private int getLeftChild(int idx) {
        return idx * 2;
    }

    private int getRightChild(int idx) {
        return idx * 2 + 1;
    }
}
