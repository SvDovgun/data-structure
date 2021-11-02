package com.luxoft.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedQueueTest {

    private LinkedQueue queue;

    @BeforeEach
    protected void setUp() throws Exception {
        queue = new LinkedQueue();
    }

    @Test
    public void testEmptyQueue() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testPeekValue() {
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals("A", queue.peek());
    }

    @Test
    public void testEnqueueAndDequeueWorkCorrectlyAndChangeSize() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size());
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnqueueOverInitialCapacityAndDequeueWorkCorrectlyAndChangeSize() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals(3, queue.size());
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testClearQueue() {
        assertTrue(queue.isEmpty());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");

        assertFalse(queue.isEmpty());

        queue.clear();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testStringOutput() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        String expected = "[A, B, C]";
        String actual = queue.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertTrue(queue.isEmpty());
        Assertions.assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    @Test
    public void testContainsValue() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertTrue(queue.contains("B"));
        assertFalse(queue.contains("D"));

        Object a = queue.dequeue();
        assertTrue("A".equals(a));

        assertTrue(queue.contains("B"));
    }
}