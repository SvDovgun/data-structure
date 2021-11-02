package com.luxoft.datastructures.list;

public class ArrayList implements List {
    private Object[] array;
    private int size;

    public ArrayList() { array = new Object[10];
    }

    private void ensureCapacity(){
        if (array.length == size) {
            Object[] newArray = new Object[array.length * 3 /2 ];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }
    @Override
    public void add(Object value) {
//        checkValueOnNull(value);
//        ensureCapacity();
//        array[size] = value;
//        size++;
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        checkIndex(size, index);
        checkValueOnNull(value);
        ensureCapacity();
        size++;
        for (int i = size-2; i > index - 1 ; i--) {
            array[i+1] =  array[i];
        }
        array[index] = value;
    }

    @Override
    public Object remove(int index) {
        checkIndex(size-1, index);
//        for (int i = index+1; i < size ; i++) {
//            array[i-1] = array[i];
//        }
        System.arraycopy(array, index + 1, array, index, size - 1 - index ); // instead of forI
        array[size - 1] = null; // memory lack? without it
        size--;
        return array;
    }

    @Override
    public Object get(int index) {
        checkIndex(size-1, index);
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        checkIndex(size-1, index);
        checkValueOnNull(value);
        return array[index] = value;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size-1; i++) {
            array[i] = null;
        }
        size = 0;
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
//        for (int i = 0; i < size; i++) {
//            Object valueArray = array[i];
//            if (valueArray.equals(value)) {
//                return true;
//            }
//        }
//        return false;
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size-1; i++) {
            Object valueArray = array[i];
            if (valueArray.equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            Object valueArray = array[i];
            if (valueArray.equals(value)) {
                index =  i;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size ; i++) {
            result.append(array[i]);
            if (i < size - 1 ) {
                result.append(", ");
            }

        }
        return "[" + result + "]";
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