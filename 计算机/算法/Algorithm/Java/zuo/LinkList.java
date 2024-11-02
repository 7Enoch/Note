package Java.zuo;

public class LinkList {

    public static void main(String[] args) {
        System.out.println("\n=====单链表反转测试====");
        ListNode head;
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println("单链表反转前");
        ListNode headTest = head;
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        head = new Solution().reverseList(headTest);
        System.out.println("\n单链表反转后");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println("\n=====双链表反转测试====");
        DoubleListNode doubleHead;
        doubleHead = new DoubleListNode(1);
        doubleHead.next = new DoubleListNode(2);
        doubleHead.next.last = doubleHead;
        doubleHead.next.next = new DoubleListNode(3);
        doubleHead.next.next.last = doubleHead.next;
        doubleHead = new Solution().reverseList(doubleHead);
        System.out.println("双链表反转后");
        while (doubleHead != null) {
            System.out.print(doubleHead.val + " ");
            doubleHead = doubleHead.next;
        }

        System.out.println("\n=====合并链表测试====");
        ListNode head1;
        head1 = new ListNode(0);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(4);
        ListNode headTest1=head1;
        System.out.println("h1原链表");
        while (head1 != null) {
            System.out.print(head1.val + " ");
            head1 = head1.next;
        }
        ListNode head2;
        head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode headTest2=head2;
        System.out.println("\nh2原链表");
        while (head2 != null) {
            System.out.print(head2.val + " ");
            head2 = head2.next;
        }
        head = new Solution().mergeTwoLists(headTest1, headTest2);
        System.out.println("\n合并后链表");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println("\n=====链表相加测试====");

    }

}

// 单链表节点
class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// 双链表节点
class DoubleListNode {
    // 节点值
    public int val;
    // 上一个指针
    public DoubleListNode last;
    // 下一个指针
    public DoubleListNode next;

    // 赋值val构造器
    public DoubleListNode(int val) {
        this.val = val;
    }

    // 全参构造器
    public DoubleListNode(int val, DoubleListNode last, DoubleListNode next) {
        this.val = val;
        this.last = last;
        this.next = next;
    }

}

class Solution {
    // 反转单链表
    public ListNode reverseList(ListNode head) {
        // 上一个节点
        ListNode pre = null;
        // 下一个节点
        ListNode next = null;
        // 只要头节点不指向空，就一直循环
        while (head != null) {
            // 将next节点指向头节点的下一个节点
            next = head.next;
            // 将头节点的下一个节点指向pre节点
            head.next = pre;
            // 将pre节点指向头节点
            pre = head;
            // 将头节点指向next节点
            head = next;
        }
        return pre;

    }

    // 反转双链表
    public DoubleListNode reverseList(DoubleListNode head) {
        // 上一个节点
        DoubleListNode pre = null;
        // 下一个节点
        DoubleListNode next = null;

        while (head != null) {
            // next节点指向头节点的下一个节点
            next = head.next;
            // 改变头节点的上一个节点为next节点
            head.last = next;
            // 改变头节点的下一个节点为pre节点
            head.next = pre;
            // pre节点来到头节点
            pre = head;
            // 头节点去到next节点
            head = next;

        }
        return pre;
    }

    // 合并两个有序链表：将两个有序链表合并成一个新的升序链表
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        // 如果两个链表其中一个为空 就返回另一个了链表的头节点
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        // 将值小的节点作为头节点
        ListNode head = head1.val >= head2.val ? head2 : head1;
        // 获取到头节点的下一个节点
        ListNode cur1 = head.next;
        // 获取另一个节点
        ListNode cur2 = head == head1 ? head2 : head1;
        // 将pre节点指向头节点 ：当前节点
        ListNode pre = head;
        // 两个节点都不为空
        while (cur1 != null && cur2 != null) {
            // 判断两个节点的大小
            // 将pre的下一个节点指向小值节点 然后pre往后移动
            if (cur1.val >= cur2.val) {
                pre.next = cur2;
                cur2 = cur2.next;
            } else {
                pre.next = cur1;
                cur1 = cur2.next;
            }
            pre = pre.next;
        }
        // 判断pre的下一个节点是否为空
        // 当循环结束的时候 肯定至少存在一个节点为空节点了
        // 所以判断哪个节点为空，pre的下一个节点指向不等于空的那个节点
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

    // 两个链表相加：两个非空链表，表示两个非负整数，他们每一位都是按照逆序的方式储存
    // 每一个节点只能储存一位数字
    // 将两个数相加，并以相同的形式返回一个表示和的链表
    // 你可以假设除了数字0之外，这两个数都不会以0开头
    public ListNode addTwoNumbers(ListNode h1, ListNode h2) {
        ListNode ans = null;
        ListNode cur = null;
        int carry = 0;// 进位数
        int sum = 0;// 两数相加的和
        int val = 0;// 本位数
        for (;
                // 终止条件
                h1 != null || h2 != null;
                // 下一次循环
                h1 = h1 == null ? null : h1.next, h2 = h2 == null ? null : h2.next) {
            // 获取两节点相加加上进位 存在一个节点为空的情况
            sum = (h1 == null ? 0 : h1.val) + (h2 == null ? 0 : h1.val) + carry;
            // 获取进位数字
            carry = sum / 10;
            // 获取本位数字
            val = sum % 10;
            if (ans == null) {
                ans = new ListNode(val);
                cur = ans;
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }

        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return ans;

    }
}