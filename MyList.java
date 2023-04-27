
public class MyList {

    Node head, tail;

    // (1)
    MyList() {
        head = tail = null;
    }

    // (2)
    boolean isEmpty() {
        return (head == null);
    }

    // (3)
    void clear() {
        head = tail = null;
    }

    // (4) Thêm 1 phần tử vào cuối danh sách liên kết
    void addLast(Person x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    // (5)
    void visit(Node p) {
        if (p != null) {
            System.out.print(p.info);
        }
    }

    // (6) duyệt danh sách liên kết
    void traverse() {
        Node p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
        System.out.println();
    }

    // (7) optional
    void addMany(String[] a, int[] b) { // a mảng chứa tên. b mảng chứa tuổi A[A,B,C,D]; B[2,4,3,5]
        int n, i;
        n = a.length;
        for (i = 0; i < n; i++) {
            addLast(new Person(a[i], b[i]));
        }
    }

    // (8) //tìm node theo tên
    Node searchByName(String xName) {
        Node f = head;
        while (f != null) {
            if (f.info.name.equals(xName)) {
                return f;
            }
            f = f.next;
        }
        return null;
    }

    Node searchByAge(int xAge) {
        Node f = head;
        while (f != null) {
            if (f.info.age == xAge) {
                return f;
            }
            f = f.next;
        }
        return null;
    }

    // (9) thêm 1 phần tử vào đầu list
    void addFirst(Person x) {
//        Node q = new Node(x);
//        q.next = head;
//        head = q;
        head = new Node(x, head);
        if (tail == null) { //thêm lần đầu tiên
            tail = head;
        }

    }

    // (10) chèn node có info x sau node q
    void insertAfter(Node q, Person x) {
        if (q == null) {
            return;
        }
        Node qNext = q.next;
        Node newNode = new Node(x);
        newNode.next = qNext;
        q.next = newNode;
        if (tail == q) { //chèn vào sau node tail
            tail = newNode; //cập nhật lại tail
        }
    }

    // (11) chèn node có info là x vào trước node q
    void insertBefore(Node q, Person x) {
        if (q == null) {
            return;
        }
        if (q == head) { //chèn vào trước node đầu tiền <=> addFirst
            addFirst(x);
            return;
        }

        //Tìm node f trước node q
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) {
            return;//q not in the list
        }
        //chèn vào sau node f
        insertAfter(f, x);
    }

    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    // (12) xóa node q khỏi list
    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) { //xóa node đầu tiên
            removeFirst();
            return;
        }

        //Tìm node f trước node q
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) {
            return;//q not in the list
        }
        f.next = q.next;
        if (f.next == null) {//xóa node cuối cùng
            tail = f;
        }
    }

    // (13) //xóa node có tên bằng xName truyền vào
    void remove(String xName) {
        remove(searchByName(xName));
    }

    // (14) //xóa node đầu tiên có tuổi bằng tuổi truyền vào
    void remove(int xAge) {
        remove(searchByAge(xAge));
    }

    // (15) xóa tất cả các node có tuổi bằng tuổi truyền vào
    void removeAll(int xAge) {
//        Node f = head;
//        while(f!=null){
//            if(f.info.age==xAge){
//                remove(f);
//            }else{
//               f=f.next; 
//            }
//        }
        Node p;
        while (true) {
            p = searchByAge(xAge);
            if (p == null) {//tìm ko thấy node nào có tuổi thỏa mãn
                break;
            }
            remove(p);
        }
    }

    // (16) lấy ra node theo index truyền vào
    Node pos(int k) {
        int i = 0;
        Node f = head;
        while (f != null) {
            if (i == k) {
                return f;
            }
            i++;
            f = f.next;

        }
        return (null);
    }

    // (17) xóa node ở vị trí k
    void removePos(int k) {
        remove(pos(k));
    }

    // (18) Sắp xếp tăng dần theo tên
    void sortByName() {
        Node pi, pj;
        Person temp;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pi.info.name.compareTo(pj.info.name) > 0) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    // (19)
    void sortByAge() {
        Node pi, pj;
        Person temp;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pi.info.age > pj.info.age) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    //sắp xếp tăng dần theo tên
    //Nếu tên giống nhau-> sắp xếp tăng dần theo tuổi
    //vd: A,10 B,15 C,12 A,9 -> A,9 A,10 B,15 C,12
    void sortCombine() {

    }

    // (20) //trả về kích thước của list
    int size() {
        int count = 0;
        Node f = head;
        while (f != null) {
            count++;
            f = f.next;
        }
        return (count);
    }

    // (21) //chuyển từ linkedlist sang mảng
    Person[] toArray() {
        int n, i;
        n = size();
        Person[] a = new Person[n];
        Node f = head;
        i = 0;

        //duyet linkedlist-> add vào mảng
        while (f != null) {
            a[i++] = new Person(f.info.name, f.info.age);
            f = f.next;
        }
        return (a);
    }

    // (22) //đảo ngược linkedlist
    void reverse() {
        MyList temp = new MyList();
        Node f = head;
        while (f != null) {
            temp.addFirst(f.info);
            f = f.next;
        }
        head = temp.head;
        tail = temp.tail;
    }

    // (23) tìm node có tuổi lớn nhất
    Node findMaxAge() {
        Node f, nodeMax;
        int maxAge;
        f = nodeMax = head;
        maxAge = f.info.age;
        f = f.next;
        while (f != null) {
            if (f.info.age > maxAge) {
                maxAge = f.info.age;
                nodeMax = f;
            }
            f = f.next;
        }
        return (nodeMax);
    }

    // (24) 
    Node findMinAge() {
        Node f, nodeMin;
        int minAge;
        f = nodeMin = head;
        minAge = f.info.age;
        f = f.next;
        while (f != null) {
            if (f.info.age < minAge) {
                minAge = f.info.age;
                nodeMin = f;
            }
            f = f.next;
        }
        return (nodeMin);
    }

    // (24) 
    void setData(Node p, Person x) {
        if (p != null) {
            p.info = new Person(x.name, x.age);
        }
    }

    // (26) 
    void sortByAge(int k, int h) {
        if (k >= h) {
            return;
        }
        if (k < 0) {
            k = 0;
        }
        int n = size();
        if (h > n - 1) {
            h = n - 1;
        }
        Node u = pos(k);
        Node v = pos(h+1);
        Node pi, pj;
        Person temp;
        pi = u;
        while (pi != v) {
            pj = pi.next;
            while (pj != v) {
                if (pi.info.age > pj.info.age) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }

    }

    // (27) 
    void reverse(int k, int h) // reverse from k to h 
    {
        //chuyển linkedlist thành array
        //đảo ngược array
        //gọi hàm clear xóa linkedlist đi
        //duyệt array , gọi hàm addLast đề add vào linkedlist
    }
}
