package set;

public class Node<E> {

    private final int hashcode;
    private final E data;
    private Node<E> link;

    public Node(int hashcode, E data) {
        this.hashcode = hashcode;
        this.data = data;
    }

    public int getHashcode() {
        return hashcode;
    }

    public E getData() {
        return data;
    }

    public Node<E> getLink() {
        return link;
    }

    public void setLink(Node<E> link) {
        this.link = link;
    }
}
