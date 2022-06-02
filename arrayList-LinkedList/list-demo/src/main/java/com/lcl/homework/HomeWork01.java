package com.lcl.homework;

public class HomeWork01 {


    /**
     * 加一
     * 如果有一个位置不是9，则说明当前数组可以存下，直接将当前位置加一，返回数组即可，如果当前位置是9，则置为0，然后向前移动
     * 如果移动完进位还是1，说明原来数组内的值都变成0，并且数组长度需要长一位，那么新创建一个长度+1的数组，将第一位置为1即可
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int num = 1;
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i] != 9){
                digits[i] = digits[i]+1;
                num = 0;
                break;
            }else{
                digits[i] = 0;
                continue;
            }
        }
        if(num == 1){
            digits = new int[digits.length+1];
            digits[0] = 1;
        }
        return digits;
    }


    /**
     * 合并有序链表
     * 由于有两个链表，因此需要有一个保护节点，同时设置一个last节点，用于向下循环，最终保护节点的next即为返回值，这里将保护节点的next先设置为null，可以保证两个链表都为空时也可以返回正确
     * 典型的保序过滤器场景，哪个小就要哪个，将其赋值给last节点，然后较小的节点向后移一位，继续循环判断
     * 在判断场景中，要保证数组越界、空指针等情况，因此除了正常情况下的大小值判断外，还需要判断空值问题
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode protect = new ListNode(0);
        ListNode last = protect;
        while(list1 != null || list2 != null){
            if(list2 == null || (list1 != null && list1.val <= list2.val)){
                last.next = list1;
                list1 = list1.next;
                last = last.next;
            }else{
                last.next = list2;
                list2 = list2.next;
                last = last.next;
            }
        }
        return protect.next;
    }


    public class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int val){
            this.val = val;
        }
    }
}
