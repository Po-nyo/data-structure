package set;

public interface Set<E> {

    /** set 에 요소 추가*/
    boolean add(E e);

    /** set 에서 요소 삭제 */
    boolean remove(E e);

    /** 요소 포함 여부 반환 */
    boolean contains(E e);

    /** 비어있는 set 인지 여부 반환 */
    boolean isEmpty();

    /** 요소의 개수 반환 */
    int size();

    /** 모든 요소 삭제 */
    void clear();
}
