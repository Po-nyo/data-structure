package set;

public class HashSet<E> implements Set<E> {

    /** buckets 의 default size  */
    private final static int CAPACITY = 1 << 4;
    /** buckets 가 75% 이상 차면 resize */
    private final static float LOAD_FACTOR = 0.75f;

    private Node<E>[] buckets;
    private int size;

    public HashSet() {
        this.buckets = (Node<E>[]) new Node[CAPACITY];
    }

    @Override
    public boolean add(E e) {
        int hashcode = e.hashCode();
        Node<E> newNode = new Node<>(hashcode, e);
        int idx = Math.abs(hashcode) % buckets.length;

        if (buckets[idx] == null) {
            buckets[idx] = newNode;
        }
        else {
            Node<E> node = buckets[idx];
            Node<E> prev = null;

            while (node != null) {
                if (node.getHashcode() == hashcode && (node.getData() == e || node.getData().equals(e)))
                    return false;
                prev = node;
                node = node.getLink();
            }

            prev.setLink(newNode);
        }

        if (++size >= buckets.length * LOAD_FACTOR)
            resize();

        return true;
    }

    @Override
    public boolean remove(E e) {
        int hashcode = e.hashCode();
        int idx = Math.abs(hashcode) % buckets.length;

        Node<E> node =buckets[idx];
        Node<E> prev = null;

        while (node != null) {
            if (node.getHashcode() == hashcode && (node.getData() == e || node.getData().equals(e))) {
                if (prev == null)
                    buckets[idx] = node.getLink();
                else
                    prev.setLink(node.getLink());
                size--;
                return true;
            }
            prev = node;
            node = node.getLink();
        }

        return false;
    }

    @Override
    public boolean contains(E e) {
        int hashcode = e.hashCode();
        int idx = Math.abs(hashcode) % buckets.length;

        Node<E> node = buckets[idx];

        while (node != null) {
            if (node.getHashcode() == hashcode && (node.getData() == e || node.getData().equals(e)))
                return true;

            node = node.getLink();
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i=0; i<buckets.length; i++)
            buckets[i] = null;

        size = 0;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        Node<E>[] newBuckets = (Node<E>[]) new Node[newCapacity];

        for (Node<E> node : buckets) {
            while (node != null) {
                int idx = Math.abs(node.getHashcode()) % newCapacity;

                if (newBuckets[idx] == null)
                    newBuckets[idx] = node;
                else {
                    Node<E> tail = newBuckets[idx];

                    while (tail.getLink() != null)
                        tail = tail.getLink();

                    tail.setLink(node);
                }

                Node<E> temp = node;
                node = node.getLink();
                temp.setLink(null);
            }
        }

        buckets = newBuckets;
    }
}
