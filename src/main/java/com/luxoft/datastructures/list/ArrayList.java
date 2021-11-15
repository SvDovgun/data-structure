package com.luxoft.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> , Iterable<T>{
    private T[] array;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int capacity){
        this.array = (T[]) new Object[capacity];

    }

    private void ensureCapacity(){
        if (array.length == size) {
            T[] newArray =(T[]) new Object[array.length * 3 /2 ];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }
    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
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
    public T remove(int index) {
        checkIndex(size-1, index);
//        for (int i = index+1; i < size ; i++) {
//            array[i-1] = array[i];
//        }
        System.arraycopy(array, index + 1, array, index, size - 1 - index ); // instead of forI
        array[size - 1] = null; // memory lack, without it
        size--;
        return (T) array;
    }

    @Override
    public T get(int index) {
        checkIndex(size-1, index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
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
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        checkValueOnNull(value);
        for (int i = 0; i < size-1; i++) {
            T valueArray = array[i];
            if (valueArray.equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            T valueArray = (T) array[i];
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

    @Override
    public String toIteratorString() {
        StringBuilder result = new StringBuilder();
        MyIterator<T> iterator = new MyIterator<>();
        while (iterator.hasNext()){
            T next = (T) iterator.next();
            result.append(next);
            if (iterator.index < size  ) {
                result.append( ", ");
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

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>();
    }


    private class MyIterator<T> implements Iterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T value ;
            if ( (T) array[index] == null) {
                throw new NoSuchElementException("Null is not supported");
            } else {
                value = (T) array[index];
            }
            index++ ;
            return value;
        }

        @Override
        public void remove() {
            //remove the latest element
            while (hasNext()) {
                next();
            }
            if ( (T) array[index-1] == null) {
                throw new NoSuchElementException("Null is not supported");
            } else {
                array[index-1] = null;
            }
            index--;
            size--;
          //  ArrayList.this.remove(this.index-1);
        }
    }

}