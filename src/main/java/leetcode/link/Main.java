package leetcode.link;

import leetcode.util.ListNode;

public class Main {

    public static void main(String[] args) {

    }

    /**
     * 链表反转
     */

    /**
     * 链表反转
     */
    public ListNode reverseList(ListNode head) {
        if(head == null) { return head; }
        ListNode cur = head, pre = head.next;
        cur.next = null; // 少了这个会有环的出现
        while (pre != null){
            ListNode temp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = temp;
        }
        return cur;
    }
}
