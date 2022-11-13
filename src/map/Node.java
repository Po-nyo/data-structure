package map;

public class Node<K, V> {

    private K key;
    private V value;
    private final int hashCode;
    private Node<K, V> link;

    public Node(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int getHashCode() {
        return hashCode;
    }

    public Node<K, V> getLink() {
        return link;
    }

    public void setLink(Node<K, V> next) {
        this.link = next;
    }
}
