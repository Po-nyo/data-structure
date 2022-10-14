package queue;

public interface Queue<E> {

    /** 큐에 요소 추가 */
    void add(E e);

    /** 큐에서 요소 꺼내기 */
    E poll();

    /** top 요소 반환 */
    E peek();

    /** 큐가 비었는지 여부 반환 */
    boolean isEmpty();

    /** 큐 크기 반환 */
    int size();

}
