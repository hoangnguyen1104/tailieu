package doublylinklistinterger;

/**
 *
 * @author hoangnhhe150070
 */
public class DoublyLinkListInterger {
    private Node head, tail;

    public DoublyLinkListInterger() {
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
        Node node = new Node(x, head, null);
        if (head != null)
            head.setPre(node);
        else
            tail = node;
        head = node;
    }

    // 2.add new node at tail 
    public void addToTail(int x) {
        Node node = new Node(x, null, tail);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            if (head.getNext() == null) {
                head = tail;
            }
            tail = node;
        }
    }

    // 3.add new node after node x
    public void addAfter(Node p, int x) {
        Node node = new Node(x, p.getNext(), p);
        if (p == tail) {
            tail = node;
        }
        p.getNext().setPre(node);
        p.setNext(node);
    }

    // 4.loop head to tail and show
    public void traverse() {
        Node node = head;
        while (node != null) {
            System.out.print(node.getValue() + ", ");            
            node = node.getNext();
        }
        System.out.println();
    }

    // 5.delete head
    public int deleteFromHead() {
        Node node = head;
        if (head.getNext() != null)
            head.getNext().setPre(null);
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return node.getValue();
    }

    // 6.delete tail
    public int deleteFromTail() {        
        if (head == tail) {
            head = null;
            tail = null;
            return head.getValue();
        }        
        Node node = tail.getPre();
        int inf = tail.getValue();
        if (node == head)
            head.setNext(null);
        node.setNext(null);
        tail= node;        
        return inf;
    }

    // 7.delete after node x
    public int deleteAfter(Node p) {        
        int inf = p.getNext().getValue();
        Node node = p.getNext().getNext();
        p.setNext(node);
        node.setPre(p);
        if (p.getNext() == null)
            tail = p;
        return inf;
    }

    // 8.delete the first node whose value equals x
    public void dele(int x) {
        Node node = head;
        while (node != null && node.getValue() != x) {           
            node = node.getNext();
        }                
        if (node != null && node.getValue() == x) {
            if (node.getPre() == null) {                
                head = node.getNext();
                head.setPre(null);
                if (head == null) {
                    tail = null;
                }
            } else if (node == tail) {
                node.getPre().setNext(null);
                tail = node.getPre();
            } else {
                Node node1 = node.getPre();
                Node node2 = node.getNext();
                node1.setNext(node2);
                node2.setPre(node1);                                
            }
        }
    }

    // 9.find the the first node with value x
    public Node search(int x) {
        Node node = head;
        while (node != null && node.getValue() != x) {
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
        while (node != null) {
            sum++;
            node = node.getNext();
        }
        if (head == null) {
            return 0;
        } else {
            return sum;
        }
    }

    // 11.delete node-i
    public void deleteByIndex(int i) {
        int index = 0;
        Node node = head;
        while (index < i) {
            node = node.getNext();
            index++;
        }
        Node node1 = node.getPre();
        Node node2 = node.getNext();
        if (node1 == null && node2 == null){
            head = null;
            tail = null;
        } else if (node1 == null) {
            node2.setPre(null);
            head = node2;
        } else if (node2 == null) {
            node1.setNext(null);
            tail = node1;
        } else {
            node1.setNext(node2);
            node2.setPre(node1);
        }
    }

    // 12.sort list ascending
    public void sort() {
        if (head == null) {
            return;
        }
        Node index = head, tmp = null;
        while (index.getNext() != null) {
            tmp = index.getNext();
            while (tmp != null) {
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
        Node node = head;
        while (node != null && node != p) {
            node = node.getNext();
        }
        if (node == p) {
            if (node.getPre() == null) {
                head = head.getNext();
                if (head != null)
                    head.setPre(null);
                else 
                    tail = null;                
            } else if (node.getNext() == null){
                tail = node.getPre();
                tail.setNext(null);
            } else {
                Node node1 = node.getPre();
                Node node2 = node.getNext();
                node1.setNext(node2);
                node2.setPre(node1);
            }
        }
    }

    // 14.return all value of node in Array
    public int[] toArray() {
        int[] arr = new int[count()];
        Node node = head;
        int index = 0;
        while (node != null) {
            arr[index] = node.getValue();
            index++;
            node = node.getNext();
        }
        return arr;
    }

    // 15.merge two link list to one link list
    public void mergeWith(DoublyLinkListInterger list) {
        Node headList = list.getHead(), headMe = head, newHead = null, newTail = null, node, before = null;
        while (headList != null || headMe != null) {
            if (headList == null) {
                node = new Node(headMe.getValue(), null, null);                
                headMe = headMe.getNext();
            } else if (headMe == null) {                
                node = new Node(headList.getValue(), null, null);
                headList = headList.getNext();
            } else if (headList.getValue() < headMe.getValue()) {                
                node = new Node(headList.getValue(), null, null);
                headList = headList.getNext();
            } else {               
                node = new Node(headMe.getValue(), null, null);
                headMe.getNext();
            }
            if (newHead == null) 
                newHead = node;
            newTail = node;            
            if (before != null){
                before.setNext(node);
                before.getNext().setPre(before);
            }
            before = node;
        }        
        head = newHead;
        tail = newTail;
    }

    // 16. add a new node before node x
    public void addBefore(Node p, int x) {
        Node node = head;
        while (node != p) {
            node = node.getNext();
        }

        if (node.getPre() != null) {
            Node newNode = new Node(x, node, node.getPre());
            node.getPre().setNext(newNode);
            node.setPre(newNode);
        } else {
            Node newNode = new Node(x, head, null);
            head = newNode;
        }
    }

    // 17. attach other link list to the end current link list
    public void attachToEnd(DoublyLinkListInterger list) {
        int[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            addToTail(arr[i]);
        }
    }

    // 18. return max value in list
    public int max() {
        int maxx = head.getValue();
        Node node = head;
        while (node != null) {
            maxx = Math.max(maxx, node.getValue());
            node = node.getNext();
        }
        return maxx;
    }

    // 19. return min value in list
    public int min() {
        int minn = head.getValue();
        Node node = head;
        while (node != null) {
            minn = Math.min(minn, node.getValue());
            node = node.getNext();
        }
        return minn;
    }

    // 20. return total of all values
    public int sum() {
        int sum = 0;
        Node node = head;
        while (node != null) {
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
        DoublyLinkListInterger list = new DoublyLinkListInterger();
        list.addToTail(x);
        mergeWith(list);
    }

    // 24. reverse list using one pass through
    public void reverse() {
        Node node = head;
        int[] arr = toArray();
        int i = count() - 1;
        while (node != null) {
            node.setValue(arr[i]);
            node = node.getNext();
            i--;
        }
    }

    // 25. check same content with other link list
    public boolean equals(DoublyLinkListInterger list) {
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
