package com.lcl.list;

public class Template {

    /**
     * 保序过滤器模板
     * 只要在 if 判断中确定数据要或者不要即可，区别就是 if 内的判断条件
     * 保序过滤器：
     *      1、删除数组中的重复项：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
     *      2、合并两个有序数组：https://leetcode.cn/problems/merge-sorted-array/
     *      3、移动零：https://leetcode.cn/problems/move-zeroes/
     *
     */

    public int removeDuplicates(int[] nums) {
        int n=0;
        for (int i=0;i<nums.length;i++){
            if(true){
               nums[n] = nums[i];
               n++;
            }
        }
        return n;
    }


    /**
     * 反转链表
     *
     */
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = last;
            last = head;
            head = temp;
        }
        return last;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode protect = null;//new ListNode(0, head);
        ListNode last = protect;
        while(head != null){
            // 1、分组
            ListNode end = getEnd(head, k);
            if(end == null){break;}
            ListNode nextGroupHead = end.next;
            // 2、组内反转
            reverse(head, nextGroupHead);
            // 3、处理前后关系
            last.next = end;
            last = head;
            head.next = nextGroupHead;
            head = nextGroupHead;
        }
        return protect.next;
    }

    private ListNode getEnd(ListNode head, int k){
        while(head != null){
            k--;
            if(k == 0){
                return head;
            }
            head = head.next;
        }
        return null;
    }

    private void reverse(ListNode head, ListNode stop){
        ListNode last = head;
        while(head != stop){
            ListNode temp = head.next;
            head.next = last;
            last = head;
            head = head.next;
        }
    }
}


class ListNode{
    public ListNode next;
}
