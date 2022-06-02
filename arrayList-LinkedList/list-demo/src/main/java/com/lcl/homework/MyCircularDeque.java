package com.lcl.homework;

public class MyCircularDeque {
    class ListNode{
        public int val;
        public ListNode pre;
        public ListNode next;

        public ListNode(int val){
            this.val = val;
        }
        public ListNode(){
        }
    }

    public ListNode head;
    public ListNode tail;
    public int size;
    private int capacity;

    public MyCircularDeque(int k) {
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
        size = 0;
        capacity = k;
    }

    public boolean insertFront(int value) {
        if(size >= capacity){
            return false;
        }
        ListNode nextNode = head.next;
        ListNode newNode = new ListNode(value);
        head.next = newNode;
        newNode.pre = head;
        newNode.next = nextNode;
        nextNode.pre = newNode;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if(size >= capacity){
            return false;
        }
        ListNode preNode = tail.pre;
        ListNode newNode = new ListNode(value);
        preNode.next = newNode;
        newNode.pre = preNode;
        newNode.next = tail;
        tail.pre = newNode;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if(size <= 0){
            return false;
        }
        ListNode nextNextNode = head.next.next;
        head.next = nextNextNode;
        nextNextNode.pre = head;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if(size <= 0){
            return false;
        }
        ListNode prePreNode = tail.pre.pre;
        tail.pre = prePreNode;
        prePreNode.next = tail;
        size--;
        return true;
    }

    public int getFront() {
        if(size <= 0){
            return -1;
        }
        return head.next.val;
    }

    public int getRear() {
        if(size <= 0){
            return -1;
        }
        return tail.pre.val;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean isFull() {
        return size >= capacity;
    }
}
