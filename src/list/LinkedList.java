package list;

public class LinkedList<E> {

    private static class Node<E> {
        private E data;
        private Node<E> link;

        private Node(E data, Node<E> link) {
            this.data = data;
            this.link = link;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /** 맨 앞에 삽입 */
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data, null);

        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.link = head;
            head = newNode;
        }

        size++;
    }

    /** 맨 뒤에 삽입 */
    public void addLast(E data) {
        Node<E> newNode = new Node<>(data, null);

        if (head == null)
            head = newNode;
        else
            tail.link = newNode;

        tail = newNode;
        size++;
    }

    /** 맨 앞 삭제 */
    public void removeFirst() {
        if (size == 0)
            return;

        head = head.link;
        if (head == null)
            tail = null;

        size--;
    }

    /** 맨 뒤 삭제 */
    public void removeLast() {
        if (size == 0)
            return;

        if (size == 1) {
            head = tail = null;
            return;
        }

        Node<E> current = head;
        Node<E> prev = head;

        while (current.link != null) {
            prev = current;
            current = current.link;
        }

        prev.link = null;
        tail = prev;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
