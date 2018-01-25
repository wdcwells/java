package com.wdc.learnning.algorithms;

public class AddNumbers {


    /**
     * 只适合两个长度一样的链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp = l1;
        for (ListNode t1 = l1, t2 = l2; null != t1 && null != t2; t1 = t1.next, t2 = t2.next) {
            int sum = t1.val + t2.val;
            if (sum >= 10) {
                tmp.val = sum % 10;
                if (null != tmp.next) {
                    tmp.next.val++;
                } else {
                    tmp.next = new ListNode(1);
                }
            } else {
                tmp.val = sum;
            }
            tmp = tmp.next;
        }
        return l1;
    }

    /**
     * 长度不一样时
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode tmp = new ListNode(0);
        for (ListNode t = tmp, t1 = l1, t2 = l2; (null != t1 || null != t2) && null != t; ) {
            int sum;
            boolean hasNext = true;
            if (null == t1) {
                sum = t2.val;
                if (null == t2.next) hasNext = false;
                t2 = t2.next;
            } else if (null == t2) {
                sum = t1.val;
                if (null == t1.next) hasNext = false;
                t1 = t1.next;
            } else {
                sum = t1.val + t2.val;
                if (null == t1.next && null == t2.next) hasNext = false;
                t1 = t1.next;
                t2 = t2.next;
            }
            if (sum >= 10) {
                t.val += sum % 10;
                t.next = new ListNode(1);
            } else {
                t.val += sum;
                if (t.val == 10) {
                    t.val = 0;
                    t.next = new ListNode(1);
                } else {
                    if (hasNext) {
                        t.next = new ListNode(0);
                    }
                }
            }
            t = t.next;

        }
        return tmp;
    }

    /**
     * 网络写法-精炼
     * The pseudocode is as following:
     * <p>
     *  Initialize current node to dummy head of the returning list.
     *  Initialize carry to 00.
     *  Initialize pp and qq to head of l1l1 and l2l2 respectively.
     *  Loop through lists l1l1 and l2l2 until you reach both ends.
     *  Set xx to node pp's value. If pp has reached the end of l1l1, set to 00.
     *  Set yy to node qq's value. If qq has reached the end of l2l2, set to 00.
     *  Set sum = x + y + carrysum=x+y+carry.
     *  Update carry = sum / 10carry=sum/10.
     *  Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
     *  Advance both pp and qq.
     *  Check if carry = 1carry=1, if so append a new node with digit 11 to the returning list.
     *  Return dummy head's next node.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        AddNumbers obj = new AddNumbers();
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        System.out.println(obj.addTwoNumbers2(new ListNode(1), l2));
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
