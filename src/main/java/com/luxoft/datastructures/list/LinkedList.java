package com.luxoft.datastructures.list;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedList implements List , Iterable<Object>{

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
            insertIn(newNode, index-1);
        }
        size++;
    }

    private void insertIn(Node newNode,int index){
        Node current = getNode(index);
        Node future =  current.getNext();
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
        }else if (index == size -1) {
            removed = tail;
            tail = removed.getPrev();
            tail.next = null;
        } else {
            current= getNode(index-1);
            removed = current.getNext();
            current = removed.getPrev();
            current.setNext(removed.getNext());
            current = current.getNext();
            current.setPrev(removed.getPrev());
        }
        size--;
        return removed.getValue();
    }

    @Override
    public Object get(int index) {
        checkIndex(size-1, index);
        return getNode(index).value;
    }

    private Node getNode(int index) {
        if (index < size) {
            Node current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            return current;
        }
        else {
            return null;
        }

    }

    @Override
    public Object set(Object value, int index) {
        checkValueOnNull(value);
        checkIndex(size-1, index);
        Node current = getNode(index);
        current.setValue(value);
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

    @Override
    public String toIteratorString() {
        StringJoiner result = new StringJoiner(", ", "[", "]");
        LinkedList.MyIterator iterator = new LinkedList.MyIterator();
        while (iterator.hasNext()){
            Object value = iterator.next();
            result.add(value.toString());
        }
        return result.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator  {
        private int maxIteration = size;
        private int index = 0;
        private Node current = head ;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object value = current.value;
            current=current.next;
            return value;
        }

        @Override
        public void remove() {
        }

    }

    private static class Node {
        Node next;
        Node prev;
        Object value;

        public Node(Object value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}