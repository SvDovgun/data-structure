package com.luxoft.datastructures.list;

import java.util.StringJoiner;

public class LinkedList implements List {

    Node head;
    Node tail;
    int size = 0;

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        checkValueOnNull(value);
        checkIndex(size, index);
        Node newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;
        } else if (index == size ) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            insertIn(newNode, index);
        }
        size++;
    }

    private void insertIn(Node newNode,int index){
        Node current = head;
        Node future = null;
        Node inserted = newNode;

        for (int i = 1; i < index  ; i++) {
            current = current.getNext();
        }
        future = current.getNext();
        newNode.prev = current.getNext();
        current.next = newNode;
        newNode.next = future;
        future.prev = newNode;
    }

    @Override
    public Object remove(int index) {
        checkIndex(size-1, index);
        Node current = head;
        Node removed = null;

        if (index == 0) {
            removed = head;
            head = removed.getNext();
            removed.setNext(null);
            removed.setValue(null);
        } else {
            for (int i = 1; i < index + 1; i++) {
                current = current.getNext();
            }
            removed = current;
            current = removed.getPrev();
            if(removed.getNext()!= null) {
                current.setNext(removed.getNext());
                current = current.getNext();
                current.setPrev(removed.getPrev());
            }
            removed.setNext(null);
            removed.setValue(null);
        }
        size--;
        return removed.getValue();
    }

    @Override
    public Object get(int index) {
        checkIndex(size-1, index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public Object set(Object value, int index) {
        checkValueOnNull(value);
        checkIndex(size-1, index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
            current.setValue(value);
        }
        return current.getValue();
    }

    @Override
    public void clear() {
        head = tail =  null;
        size = 0 ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object value) {
        checkValueOnNull(value);
        if (isEmpty()) {
            return false;
        }
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        checkValueOnNull(value);
        for (int i = 0; i < size; i++) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        checkValueOnNull(value);
        for (int i = size - 1; i >= 0; i--) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringJoiner result = new StringJoiner(", ", "[", "]");
        Node current = head;
        result.add(current.getValue().toString());
        while (current.getNext() != null) {
            current = current.getNext();
            result.add(current.getValue().toString());
        }
        return result.toString();
    }

    private void checkIndex(int size, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is more than array size!");
        }
        if (index < 0 ) {
            throw new ArrayIndexOutOfBoundsException("Negative index are not supported!");
        }
    }

    private void checkValueOnNull(Object value) {
        if (value == null) {
            throw new NullPointerException("Null is not supported");
        }
    }
}