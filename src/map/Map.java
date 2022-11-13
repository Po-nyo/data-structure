package map;

public interface Map<K, V> {

    boolean put(K key, V value);
    V get(K key);
    int size();
    void remove(K key);
    boolean isEmpty();
}
