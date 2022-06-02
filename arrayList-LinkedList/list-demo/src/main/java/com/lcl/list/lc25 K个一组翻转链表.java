package com.lcl.list;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 链表的保护节点（提供入口，防止null异常）
        ListNode entry = new ListNode();
        entry.next = head;
        ListNode last = entry;
        while (head != null) {
            // 分组
            ListNode tail = getGroup(head, k);
            if (tail == null) break;

            ListNode nextHead = tail.next;

            // 当前组：head->...->tail
            // 下一组：nextHead->...

            // 组内反转
            reverseList(head, nextHead);

            // 当前组跟下一组、上一组的关系
            // 当前组：变成了tail->...->head
            last.next = tail;

            last = head;
            head = nextHead;
        }
        last.next = head;
        return entry.next;
    }

    // 返回k个一组，组的尾部，null表示这组不够k个
    private ListNode getGroup(ListNode head, int k) {
        while (head != null) {
            k--;
            if (k == 0) return head;
            head = head.next;
        }
        return null;
    }

    // 组的内部反转
    private void reverseList(ListNode head, ListNode stop) {
        ListNode now = head.next;
        ListNode last = head;
        // 遍历
        while (now != stop) {
            // 备份now.next
            ListNode next = now.next;
            // 操作：now指向last
            now.next = last;
            // 往后移动
            last = now;
            now = next;
        }
    }


    public static void main(String[] args) {
        int[] digits = {9,9,9};
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
        System.out.printf("==================="+ calculate1("2-(1 + 2)"));
    }

    public static int calculate1(String s) {
        Stack<Integer> numStack = new Stack();
        Stack<Character> opsStack = new Stack();
        String number = "";
        s+=" ";
        for(char c : s.toCharArray()){
            if(c >='0' && c<='9'){
                number += c;
                continue;
            }else{
                if(!"".equals(number)){
                    numStack.push(Integer.parseInt(number));
                    number = "";
                }
            }
            if(' ' == c){
                continue;
            }
            if('(' == c){
                opsStack.push(c);
                continue;
            }
            if(')' == c){
                while(opsStack.peek() == '('){
                    int y = numStack.pop();
                    int x = numStack.pop();
                    char ops = opsStack.pop();
                    numStack.push(getNum(x, y, ops));
                }
                opsStack.pop();
                continue;
            }
            while(!opsStack.isEmpty() && getRank(c) == getRank(opsStack.peek())){
                int y = numStack.pop();
                int x = numStack.pop();
                char ops = opsStack.pop();
                numStack.push(getNum(x, y, ops));
            }
            opsStack.push(c);
        }
        while(!opsStack.isEmpty()){
            int y = numStack.pop();
            int x = numStack.pop();
            char ops = opsStack.pop();
            numStack.push(getNum(x, y, ops));
        }
        return numStack.pop();
    }

    public static int calculate(String s) {
        Stack<Character> opsStack = new Stack();
        Stack<Integer> numStack = new Stack();
        String numStr = "";
        for(char c : s.toCharArray()){
            if(c >= '0' && c <= '9'){
                numStr = numStr + c;
                continue;
            }else{
                if(!"".equals(numStr)){
                    numStack.push(Integer.parseInt(numStr));
                    numStr = "";
                }
            }
            if(' ' == c){
                continue;
            }
            while(!opsStack.isEmpty() && getRank(c) <= getRank(opsStack.peek())){
                int y = numStack.pop();
                int x = numStack.pop();
                char ops = opsStack.pop();
                numStack.push(getNum(x, y, ops));
            }
            opsStack.push(c);
        }
        while(!opsStack.isEmpty()){
            int y = numStack.pop();
            int x = numStack.pop();
            char ops = opsStack.pop();
            numStack.push(getNum(x, y, ops));
        }
        return numStack.pop();
    }

    private static int getNum(int x, int y, char c){
        if('+' == c){return x+y;}
        if('-' == c){return x-y;}
        if('*' == c){return x*y;}
        return x/y;

    }

    private static int getRank(char c){
        if(c == '-' || c == '+'){
            return 1;
        }
        return 2;
    }
}