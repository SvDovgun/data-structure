package com.luxoft.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T>, Iterable<T>{

    Node<T> head;
    Node<T> tail;
    int size = 0;

    @Override
    public void add(T value) {
        add( value, size);
    }

    @Override
    public void add(T value, int index) {
        checkValueOnNull(value);
        checkIndex(size, index);
        Node<T> newNode = new Node<>(value);
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

    private void insertIn(Node<T> newNode,int index){
        Node<T> current = getNode(index);
        Node<T> future =  current.getNext();
        newNode.prev = current.getNext();
        current.next = newNode;
        newNode.next = future;
        future.prev = newNode;
    }

    @Override
    public T remove(int index) {
        checkIndex(size-1, index);
        Node<T> current = head;
        Node<T> removed = null;

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
    public T get(int index) {
        checkIndex(size-1, index);
        return (T) getNode(index).value;
    }

    private Node<T> getNode(int index) {
        if (index < size) {
            Node<T> current = head;
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
    public T set(T value, int index) {
        checkValueOnNull(value);
        checkIndex(size-1, index);
        Node<T> current = getNode(index);
        current.setValue(value);
        return (T) current.getValue();
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
    public boolean contains(T value) {
        checkValueOnNull(value);
        if (isEmpty()) {
            return false;
        }
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        checkValueOnNull(value);
        for (int i = 0; i < size; i++) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
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
        Node<T> current = head;
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

    private void checkValueOnNull(T value) {
        if ((T) value == null) {
            throw new NullPointerException("Null is not supported");
        }
    }

    @Override
    public String toIteratorString() {
        StringJoiner result = new StringJoiner(", ", "[", "]");
        MyIterator<T> iterator = new LinkedList.MyIterator();
        do {
            T value = (T) iterator.next();
            if (value != null) {
                result.add(value.toString());
            }
        }  while (iterator.hasNext());
//        while (iterator.hasNext()){
//            T value = (T) iterator.next();
//            result.add(value.toString());
//        }
        return result.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>();
    }

    private class MyIterator<T> implements Iterator<T>  {
        private Node<T> current = (Node<T>) head;
     //   private Node<T> tail = (Node<T>) LinkedList.this.tail;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            T value;
            value = (T) current.value;
            if (hasNext() == true) {
                current = current.next;
            }
            System.out.println("value in next() " + value);
            return value;
        }

        @Override
        public void remove() {
            while (hasNext()) {
                next();
            }
//            if (current.getPrev() != null) {
//                tail = current.getPrev();
//                tail.next = null;
//            } else if (current == head){
//                tail = current = (Node<T>) head;
//                current.setNext(null);
//                current.setValue(null);
//            }
//            size--;
//            current = (Node<T>) head;
            LinkedList.this.remove(size-1);
        }

    }

    private static class Node<T> {
        Node<T> next;
        Node<T> prev;
        T value;

        public Node(T value) {
            this.value = (T) value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public T getValue() {
            return (T) value;
        }

        public void setValue(T value) {
            this.value = (T) value;
        }
    }
}