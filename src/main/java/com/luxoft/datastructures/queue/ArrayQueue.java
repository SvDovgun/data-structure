package com.luxoft.datastructures.queue;

public class ArrayQueue  implements Queue{
    private int size;
    private Object[] array;

    public ArrayQueue() {
        array = new Object[10];
    }

    @Override
    public void enqueue(Object value) {
        array[size] = value;
        size++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("It is Empty!");
        }
        Object result = array[0];
        Object[] arrayNew = new Object[size-1];
        for (int i = 0; i < arrayNew.length; i++) {
            arrayNew[i] = array[i+1];
        }
        array = arrayNew;
        size--;
        return result;
    }

    @Override
    public Object peek() {
        if(isEmpty()) {
            throw new IllegalStateException("It is Empty!");
        }
        return array[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            Object valueSearch = array[i];
            if (value.equals(valueSearch)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String toString() {
        String result="";
        for(int i = 0; i < size ; i++){
           // Object value = array[i];
            result = result + array[i];
            if (i < size - 1 ) {
                result = result + ", ";
            } else {
                result = result + "";
            }
        }
        return "[" + result + "]";
    };

    public String printArray() {
        String result="";
        for(int i = 0; i < array.length ; i++){
            Object value = array[i];
            result = result + (String) value;
            System.out.println(" array[" + i + "] = " + value + "; ");
        }
        return result;
    };
}
