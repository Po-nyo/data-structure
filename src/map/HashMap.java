package map;

/** 해시 출동 전략: separate chaining */
public class HashMap<K, V> implements Map<K, V> {

    /** buckets 의 default size  */
    private final static int CAPACITY = 1 << 4;
    /** buckets 가 75% 이상 차면 resize */
    private final static float LOAD_FACTOR = 0.75f;

    private Node<K, V>[] buckets;

    private int size;

    public HashMap() {
        this.buckets = (Node<K, V>[]) new Node[CAPACITY];
    }

    @Override
    public boolean put(K key, V value) {
        int hashCode = key.hashCode();
        Node<K, V> newNode = new Node<>(key, value, hashCode);

        int idx = Math.abs(hashCode % buckets.length);

        if (buckets[idx] == null) {
            buckets[idx] = newNode;
        }
        else {
            Node<K, V> current = buckets[idx];
            Node<K, V> prev = null;

            while (current != null) {
                if (current.getKey().equals(key) && current.getHashCode() == hashCode)
                    break;
                prev = current;
                current = current.getLink();
            }

            if (prev == null) {
                newNode.setLink(current.getLink());
                buckets[idx] = newNode;
            }
            else
                prev.setLink(newNode);
        }

        if (++size >= buckets.length * LOAD_FACTOR)
            resize();

        return true;
    }

    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int idx = Math.abs(hashCode % buckets.length);

        if (buckets[idx] == null)
            return null;
        else {
            Node<K, V> node = buckets[idx];

            while (node != null) {
                if (node.getKey().equals(key) && node.getHashCode() == hashCode)
                    return node.getValue();
                node = node.getLink();
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(K key) {
        int hashCode = key.hashCode();
        int idx = Math.abs(hashCode % buckets.length);

        if (buckets[idx] == null)
            return;

        Node<K, V> target = buckets[idx];
        Node<K, V> prev = null;

        while (target != null) {
            if (target.getKey().equals(key) && target.getHashCode() == hashCode)
                break;
            prev = target;
            target = target.getLink();
        }

        if (target == null)
            return;

        if (prev == null)
            buckets[idx] = null;
        else
            prev.setLink(target.getLink());

        size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newSize = buckets.length * 2;
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[newSize];

        for (Node<K, V> node : buckets) {
            while (node != null) {
                int newIdx = Math.abs(node.getHashCode() % newSize);

                if (newBuckets[newIdx] == null) {
                    newBuckets[newIdx] = node;
                }
                else {
                    Node<K, V> current = newBuckets[newIdx];

                    while (current.getLink() != null) {
                        current = current.getLink();
                    }

                    current.setLink(node);
                }

                Node<K, V> prev = node;
                node = node.getLink();
                prev.setLink(null);
            }
        }

        buckets = newBuckets;
    }
}
