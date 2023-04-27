package circularlinklistInteger;

/**
 * @author hoangnhhe150070 12:12, 11/09/2021
 */
public class CircularLinkListInteger {

    private Node head, tail;

    public CircularLinkListInteger() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    // 1.add new node at head
    public void addToHead(int x) {
        Node node = new Node(x, head);
        head = node;
        if (tail == null) {
            tail = node;
        } else {
            tail.setNext(head);
        }
    }

    // 2.add new node at tail 
    public void addToTail(int x) {
        Node node = new Node(x, null);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            if (head.getNext() == null) {
                head = tail;
            }
            tail = node;
            tail.setNext(head);
        }
    }

    // 3.add new node after node x
    public void addAfter(Node p, int x) {
        Node node = new Node(x, p.getNext());
        if (p == tail) {
            tail = node;
            tail.setNext(head);
        }
        p.setNext(node);
    }

    // 4.loop head to tail and show
    public void traverse() {
        Node node = head;
        while (node != tail) {
            System.out.print(node.getValue() + ", ");
            node = node.getNext();
        }
        System.out.println(node.getValue());
    }

    // 5.delete head
    public int deleteFromHead() {
        Node node = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            tail.setNext(head);
        }
        return node.getValue();
    }

    // 6.delete tail
    public int deleteFromTail() {
        Node node = head;
        if (head == tail) {
            head = null;
            tail = null;
            return node.getValue();
        }
        while (node.getNext() != tail) {
            node = node.getNext();
        }
        tail = node;
        if (tail == head) {
            tail.setNext(null);
            head.setNext(null);
        } else {
            tail.setNext(head);
        }
        return node.getNext().getValue();
    }

    // 7.delete after node x
    public int deleteAfter(Node p) {
        Node node = head;
        while (node != p) {
            node = node.getNext();
        }
        int inf = node.getNext().getValue();
        if (node == head) {
            head.setNext(null);
            tail = head;
        } else {
            node.setNext(node.getNext().getNext());
            if (node.getNext() == null) {
                tail = node;
                tail.setNext(head);
            }
        }

        return inf;
    }

    // 8.delete the first node whose value equals x
    public void dele(int x) {
        Node node = head, before = null;
        while (node != tail && node.getValue() != x) {
            before = node;
            node = node.getNext();
        }
        if (node != null && node.getValue() == x) {
            if (before == null) {
                head = head.getNext();
                if (head == null) {
                    tail = null;
                }
                if (head != tail) {
                    tail.setNext(head);
                }
            } else if (node == tail) {
                before.setNext(node.getNext());
                tail = before;
                if (tail != head) {
                    tail.setNext(head);
                }
            } else {
                before.setNext(node.getNext());
            }
        }
    }

    // 9.find the the first node with value x
    public Node search(int x) {
        Node node = head;
        while (node != tail && node.getValue() != x) {
            node = node.getNext();
        }
        if (node.getValue() == x) {
            return node;
        } else {
            return null;
        }
    }

    // 10.return size of link list
    public int count() {
        int sum = 0;
        Node node = head;
        while (node != tail) {
            sum++;
            node = node.getNext();
        }
        if (head == null) {
            return 0;
        } else {
            return sum + 1;
        }
    }

    // 11.delete node-i
    public void deleteByIndex(int i) {
        int index = 0;
        Node node = head, before = null;
        while (index < i) {
            before = node;
            node = node.getNext();
            index++;
        }
        if (before == null) {
            head = node.getNext();
            if (head == null) {
                tail = null;
            }
            if (head != tail) {
                tail.setNext(head);
            }
        } else if (node == tail) {
            if (before == head) {
                head.setNext(null);
                tail = head;
            } else {
                before.setNext(null);
                tail = before;
                tail.setNext(head);
            }
        } else {
            before.setNext(node.getNext());
        }
    }

    // 12.sort list ascending
    public void sort() {
        if (head == null) {
            return;
        }
        Node index = head, tmp = null;
        while (index != tail) {
            tmp = index.getNext();
            while (tmp != head) {
                if (tmp.getValue() < index.getValue()) {
                    int tg = tmp.getValue();
                    tmp.setValue(index.getValue());
                    index.setValue(tg);
                }
                tmp = tmp.getNext();
            }
            index = index.getNext();
        }
    }

    // 13.delete the first node equal node x
    public void dele(Node p) {
        Node node = head, before = null;
        while (node != tail && node != p) {
            before = node;
            node = node.getNext();
        }
        if (node == p) {
            if (before == null) {
                head = head.getNext();
                if (head == null) {
                    tail = null;
                }
                if (tail != head) {
                    tail.setNext(head);
                }
            } else {
                if (before == head) {
                    head.setNext(null);
                    tail = head;
                } else {
                    before.setNext(node.getNext());
                    if (before.getNext() == null) {
                        tail = before;
                        tail.setNext(head);
                    }
                }
            }
        }
    }

    // 14.return all value of node in Array
    public int[] toArray() {
        int[] arr = new int[count()];
        Node node = head;
        int index = 0;
        while (node != tail) {
            arr[index] = node.getValue();
            index++;
            node = node.getNext();
        }
        arr[index] = node.getValue();
        index++;
        return arr;
    }

    // 15.merge two link list to one link list
    public void mergeWith(CircularLinkListInteger list) {
        Node headList = list.getHead(), headMe = head, newHead = null, newTail = null, node, before = null;        
        if (headList.getValue() < headMe.getValue()){            
            newTail = new Node(headMe.getValue(), null);
            newHead = new Node(headList.getValue(), newTail);            
        } else {
            newTail = new Node(headList.getValue(), null);
            newHead = new Node(headMe.getValue(), newTail);
        }
        before = newTail;
//        System.out.println(newHead.getValue());
//        System.out.println(newTail.getValue());
        headList = headList.getNext();
        if (headList == null) 
            headList = list.getHead();
        headMe = headMe.getNext();
        if (headMe == null)
            headMe = head;
        while (headList != list.getHead() || headMe != head) {                        
            if (headList == list.getHead()) {
                node = new Node(headMe.getValue(), null);
                headMe = headMe.getNext();
            } else if (headMe == head) {
                node = new Node(headList.getValue(), null);
                headList = headList.getNext();
            } else if (headList.getValue() < headMe.getValue()) {
                node = new Node(headList.getValue(), null);
                headList = headList.getNext();
            } else {
                node = new Node(headMe.getValue(), null);
                headMe.getNext();
            }
            newTail = node;
//            System.out.println("bfefore" + before.getValue());
            before.setNext(node);
            before = node;
//            System.out.println(node.getValue());
        }                
        
        head = newHead;
        tail = newTail;
        if (newHead != newTail) {
            tail.setNext(head);
        }
    }

    // 16. add a new node before node x
    public void addBefore(Node p, int x) {
        Node node = head, before = null;
        while (node != p) {
            before = node;
            node = node.getNext();
        }

        if (before != null) {
            Node newNode = new Node(x, node);
            before.setNext(newNode);            
        } else {
            Node newNode = new Node(x, head);
            head = newNode;
            tail.setNext(head);
        }
    }

    // 17. attach other link list to the end current link list
    public void attachToEnd(CircularLinkListInteger list) {
        int[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            addToTail(arr[i]);
        }
    }

    // 18. return max value in list
    public int max() {
        int maxx = tail.getValue();
        Node node = head;
        while (node != tail) {
            maxx = Math.max(maxx, node.getValue());
            node = node.getNext();
        }
        return maxx;
    }

    // 19. return min value in list
    public int min() {
        int minn = tail.getValue();
        Node node = head;
        while (node != tail) {
            minn = Math.min(minn, node.getValue());
            node = node.getNext();
        }
        return minn;
    }

    // 20. return total of all values
    public int sum() {
        int sum = tail.getValue();
        Node node = head;
        while (node != tail) {
            sum += node.getValue();
            node = node.getNext();
        }
        return sum;
    }

    // 21.return average off value
    public int avg() {
        return sum() / count();
    }

    // 22. check list has sorted?
    public boolean sorted() {
        int[] arr = toArray();
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // 23. insert node to sorted list and sort again
    public void insert(int x) {
        CircularLinkListInteger list = new CircularLinkListInteger();
        list.addToTail(x);
        mergeWith(list);
    }

    // 24. reverse list using one pass through
    public void reverse() {
        Node node = head;
        int[] arr = toArray();
        int i = count() - 1;
        while (node != tail) {
            node.setValue(arr[i]);
            node = node.getNext();
            i--;
        }
        tail.setValue(arr[0]);
    }

    // 25. check same content with other link list
    public boolean equals(CircularLinkListInteger list) {
        int[] arrThis = toArray();
        int[] arrList = list.toArray();
        if (arrList.length != arrThis.length) {
            return false;
        }
        boolean check = true;
        for (int i = 0; i < arrList.length; i++) {
            if (arrList[i] != arrThis[i]) {
                check = false;
            }
        }
        return check;
    }
}
