package base;

/**
 * @author sunguiyong
 * @date 2022/4/8 7:17 下午
 */
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
