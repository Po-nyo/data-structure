package map;

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
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
