package com.luxoft.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
//    void enqueue(Object value);
//    Object dequeue();
//    Object peek();
//    int size();
//    boolean isEmpty();
//    boolean contains(Object value);
//    void clear();
//    // [A, B, C] if size = 3
//    String toString();

    @Test
    public void testEnqueueAndDequeueWorkCorrectlyAndSizeChanges() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");
        arrayQueue.enqueue("D");

        assertEquals(4, arrayQueue.size());

        assertEquals("A", arrayQueue.dequeue());
        assertEquals("B", arrayQueue.dequeue());
        assertEquals("C", arrayQueue.dequeue());
        assertEquals("D", arrayQueue.dequeue());
     //   System.out.println(arrayQueue.toString());
        assertTrue( arrayQueue.isEmpty());
    }

    @Test
    public void testEnqueueAndPeekNoSizeChange(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");

        assertEquals(2, arrayQueue.size());

        assertEquals("A", arrayQueue.peek());
        assertEquals("A", arrayQueue.peek());
        assertEquals(2, arrayQueue.size());
    }

    @Test
    public void testIsEmptyTrueForNewEmptyQueue(){
        ArrayQueue arrayQueue = new ArrayQueue();

        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyFalseForQueueWithData(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("C");

        assertFalse(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyTrueForQueueAfterClear() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");
        assertEquals(3, arrayQueue.size());

        arrayQueue.clear();
        assertTrue(arrayQueue.isEmpty());
     //   System.out.println(arrayQueue.printArray());
    }

    @Test
    public void testContainReturnTrueForLatestValueOfQueueWithData(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("D");

        assertTrue(arrayQueue.contains("D"));
    }

    @Test
    public void testContainReturnTrueForFirstValueOfQueueWithData(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("D");

        assertTrue(arrayQueue.contains("A"));
    }

    @Test
    public void testContainReturnFalseForQueueWithData(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("D");

        assertFalse(arrayQueue.contains("C"));
    }

    @Test
    public void testContainReturnFalseForEmptyQueue(){
        ArrayQueue arrayQueue = new ArrayQueue();

        assertFalse(arrayQueue.contains("C"));
    }


    @Test
    public void testEmptyQueueToStringConvert(){
        ArrayQueue arrayQueue = new ArrayQueue();

        assertTrue(arrayQueue.isEmpty());
        assertEquals("[]", arrayQueue.toString());
    }

    @Test
    public void testQueueWithDataToStringConvert(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");

        assertEquals(3, arrayQueue.size());

        assertEquals("[A, B, C]", arrayQueue.toString());
    }

    @Test
    public void testThrowIllegalStateExceptionWhenPeekOfEmptyQueue(){
        ArrayQueue arrayQueue = new ArrayQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayQueue.peek();
        });
    }

    @Test
    public void testThrowIllegalStateExceptionWhenDequeueOfEmptyStack(){
        ArrayQueue arrayQueue = new ArrayQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayQueue.dequeue();
        });
    }

}
