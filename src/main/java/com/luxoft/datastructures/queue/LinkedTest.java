//package com.luxoft.datastructures.queue;
//
//public class LinkedTest {
//    public static void main(String[] args) {
//        Node head = new Node(1);
//        Node second = new Node(2);
//        head.next = second;
//        Node third = new Node(3);
//        second.next = third;
//
//        System.out.println(getByIndex(head, 0));
//        System.out.println(getByIndex(head, 1));
//        System.out.println(getByIndex(head, 2));
//    }
//
//    static Object getByIndex(Node head, int index) {
//        Node current = head;
//        for (int i = 0; i < index; i++) {
//            current = current.next;
//        }
//
//        return current.value;
//    }
//}
//
//class Node {
//    Object value;
//    Node next;
//
//    public Node(Object value) {
//        this.value = value;
//    }
//}