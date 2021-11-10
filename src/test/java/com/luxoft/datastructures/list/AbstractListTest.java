package com.luxoft.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractListTest {

    private List list;

    @BeforeEach
    protected void setUp()  {
        list = getList();
    }

    protected abstract List getList();


    @DisplayName("Test Simple Add 1 Value and check size")
    @Test
    public void testAddValueByDefaultToEmptyListCorrectWithSizeChange() {
        list.add("A");

        assertEquals(1, list.size());
    }

    @DisplayName("Test Attempt Add Value By Negative Index Return Exception")
    @Test
    public void testAttemptAddValueByNegativeIndexReturnException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {list.add("A", -1);});

    }

    @DisplayName("Test Simple Add/Remove 1 Value Twice and check by size and isEmpty functions")
    @Test
    public void testAddAndRemoveValueByDefaultToEmptyListCorrect() {
        list.add("A");

        assertEquals(1, list.size());

        list.remove(0);

        assertTrue(list.isEmpty());

        list.add("B");
        assertEquals(1, list.size());
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @DisplayName("Test Add/Remove Values and check by size and toString functions")
    @Test
    public void testAddAndRemoveValuesCorrect(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("F");
        assertEquals(5, list.size());

        list.remove(2);
        assertEquals(4, list.size());

        assertEquals("[A, B, D, F]",list.toString() );
    }

    @DisplayName("Test Add Values and Remove First Value from List")
    @Test
    public void testAddAndRemoveFirstValuesAddNewCorrect(){
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size());

        list.remove(0);
        assertEquals(2, list.size());

        assertEquals("[B, C]",list.toString() );

        list.add("D");  //2
        assertEquals(3, list.size());

        assertEquals("[B, C, D]",list.toString() );
    }


    @DisplayName("Test Add Values and Remove Last Value from List")
    @Test
    public void testAddAndRemoveLastValuesCorrect(){
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove(2);
        assertEquals("[A, B]",list.toString() );

    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesUponInitialCapacityCorrect(){
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }

        assertEquals(12, list.size());
    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesTwiceUponInitialCapacityCorrect(){
        for (int i = 0; i < 23; i++) {
            list.add(i);
        }

        assertEquals(23, list.size());
    }

    @DisplayName("Test Add New Value with shift in middle List")
    @Test
    public void testAddValuesWithShiftMiddleListCorrect(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("F", 2);
        assertEquals(5, list.size());
        assertEquals("[A, B, F, C, D]",list.toString() );
    }

    @DisplayName("Test Add New Value with shift in begin List")
    @Test
    public void testAddValuesWithShiftBeginListCorrect(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("F", 0);
        assertEquals(5, list.size());
        assertEquals("[F, A, B, C, D]",list.toString() );
    }

    @DisplayName("Attempt to Add null Value with shift in begin List return Exception")
    @Test
    public void testAddNullValuesWithShiftBeginListFail(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        assertThrows(NullPointerException.class, () -> { list.add(null, 0); });
        assertEquals(4, list.size());
        assertEquals("[A, B, C, D]",list.toString() );
    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesWithIndexUponInitialCapacityCorrect(){
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(10, list.size());
        list.add("F", 5);
        System.out.println(list.toString());
        assertEquals("[0, 1, 2, 3, 4, F, 5, 6, 7, 8, 9]",list.toString() );
    }

    @DisplayName("Test attempt to Remove not existed index in List")
    @Test
    public void testAttemptRemoveByNoExistedIndexReturnException(){
        list.add("A");
        list.add("B");
        list.add("C");

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(3);});
    }

    @DisplayName("Test attempt to Add null value to List, returns NullPointException")
    @Test
    public void testAttemptAddNullAsValueReturnException() {
        assertThrows(NullPointerException.class, () -> {list.add(null);});
        assertTrue(list.isEmpty());
    }

    @DisplayName("Test attempt to Get(0) value from empty List, returns Exception")
    @Test
    public void testAttemptOfGetValueFromEmptyListReturnException(){
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(0);});
    }

    @DisplayName("Test get value by index from List")
    @Test
    public void testGetByExistedIndexTrueFromList(){
        list.add("A");
        list.add("B");
        list.add("S");
        list.add("D");

        assertEquals("S",list.get(2));
    }

    @DisplayName("Test attempt to Get value by not existed index from List, returns Exception")
    @Test
    public void testAttemptGetByNoExistedIndexInListReturnException(){
        list.add("A");
        list.add("B");

        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(2);});
    }

    @DisplayName("Test attempt to Get value by already not existed index(because removed) from List, returns Exception")
    @Test
    public void testAttemptGetByNoExistedAlreadyIndexReturnException(){
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove(2);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(2);});
    }

    @DisplayName("Test attempt to Set value by not existed index from List, returns Exception")
    @Test
    public void testAttemptSetToNoExistedIndexReturnException(){
        list.add("A");
        assertThrows(IndexOutOfBoundsException.class, () -> {list.set("B" , 1);});
    }

    @DisplayName("Test Set(update) value by index to List")
    @Test
    public void testSetValueByIndexNoSizeChangesExpected(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(3, list.size());
        list.set("F" , 1);
        assertEquals(3, list.size());
        list.set("E" , 2);
        assertEquals("[A, F, E]", list.toString());
    }

    @DisplayName("Test Clear Existed List and check size")
    @Test
    public void testClearOfExistedListAndSizeChangesExpected(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @DisplayName("Test Clear Empty List and check size")
    @Test
    public void testClearOfEmptyList(){
        list.clear();
        assertTrue(list.isEmpty());
    }

    @DisplayName("Test Contain of existed value in List")
    @Test
    public void testContainOfExistedValueInListReturnTrue(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
        assertTrue(list.contains("C"));
    }

    @DisplayName("Test Contain of existed value (existed twice) in List")
    @Test
    public void testContainOfExistedTwiceValueInListReturnTrue(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("C");

        assertEquals(5, list.size());
        assertTrue(list.contains("C"));
    }

    @DisplayName("Test Contain of not existed value in List")
    @Test
    public void testNotContainOfValueInListReturnFalse(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
        assertFalse(list.contains("F"));
    }

    @DisplayName("Find index of existed value in List")
    @Test
    public void testIndexOfExistedValueInListReturnIndex(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(2, list.indexOf("C"));
    }

    @DisplayName("Attempt to find index of NULL value in List")
    @Test
    public void testIndexOfNullValueInListReturn(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertThrows(NullPointerException.class, () -> { list.indexOf(null); });
    }

    @DisplayName("Attempt to find index of not existed value in List return -1")
    @Test
    public void testIndexOfNotExistedValueInListReturnMinus1(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(-1, list.indexOf("F"));
    }

    @DisplayName("Find index of first existed search value in List")
    @Test
    public void testIndexOfFirstExistedValueInListReturnIndex(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("C");

        assertEquals(2, list.indexOf("C"));
    }

    @DisplayName("Find latest index of existed value in List")
    @Test
    public void testLatestIndexOfExistedValueInListReturnIndex(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(2, list.lastIndexOf("C"));
    }

    @DisplayName("Attempt to find latest index of not existed value in List return -1")
    @Test
    public void testLatestIndexOfNotExistedValueInListReturnMinus1(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(-1, list.lastIndexOf("F"));
    }

    @DisplayName("Find latest index of multiple existed search values in List")
    @Test
    public void testLatestIndexOfMultiExistedValueInListReturnIndex(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("C");
        list.add("F");
        list.add("E");
        list.add("C");
        list.add("R");

        assertEquals(7, list.lastIndexOf("C"));
    }
}
