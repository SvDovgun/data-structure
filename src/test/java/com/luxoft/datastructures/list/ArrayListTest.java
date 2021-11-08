package com.luxoft.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    private ArrayList arrayList;

    @BeforeEach
    protected void setUp() throws Exception {
        arrayList = new ArrayList();
    }


    @DisplayName("Test Simple Add 1 Value and check size")
    @Test
    public void testAddValueByDefaultToEmptyListCorrectWithSizeChange() {
        arrayList.add("A");

        assertEquals(1, arrayList.size());
    }

    @DisplayName("Test Attempt Add Value By Negative Index Return Exception")
    @Test
    public void testAttemptAddValueByNegativeIndexReturnException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {arrayList.add("A", -1);});

    }

    @DisplayName("Test Simple Add/Remove 1 Value Twice and check by size and isEmpty functions")
    @Test
    public void testAddAndRemoveValueByDefaultToEmptyListCorrect() {
        arrayList.add("A");

        assertEquals(1, arrayList.size());

        arrayList.remove(0);

        assertTrue(arrayList.isEmpty());

        arrayList.add("B");
        assertEquals(1, arrayList.size());
        arrayList.remove(0);
        assertTrue(arrayList.isEmpty());
    }

    @DisplayName("Test Add/Remove Values and check by size and toString functions")
    @Test
    public void testAddAndRemoveValuesCorrect(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("F");
        assertEquals(5, arrayList.size());

        arrayList.remove(2);
        assertEquals(4, arrayList.size());

        assertEquals("[A, B, D, F]",arrayList.toString() );
    }

    @DisplayName("Test Add Values and Remove First Value from List")
    @Test
    public void testAddAndRemoveFirstValuesAddNewCorrect(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals(3, arrayList.size());

        arrayList.remove(0);
        assertEquals(2, arrayList.size());

        assertEquals("[B, C]",arrayList.toString() );

        arrayList.add("D");  //2
        assertEquals(3, arrayList.size());

        assertEquals("[B, C, D]",arrayList.toString() );
    }


    @DisplayName("Test Add Values and Remove Last Value from List")
    @Test
    public void testAddAndRemoveLastValuesCorrect(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        arrayList.remove(2);
        assertEquals("[A, B]",arrayList.toString() );

    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesUponInitialCapacityCorrect(){
        for (int i = 0; i < 12; i++) {
            arrayList.add(i);
        }

        assertEquals(12, arrayList.size());
    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesTwiceUponInitialCapacityCorrect(){
        for (int i = 0; i < 23; i++) {
            arrayList.add(i);
        }

        assertEquals(23, arrayList.size());
    }

    @DisplayName("Test Add New Value with shift in middle List")
    @Test
    public void testAddValuesWithShiftMiddleListCorrect(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("F", 2);
        assertEquals(5, arrayList.size());
        assertEquals("[A, B, F, C, D]",arrayList.toString() );
    }

    @DisplayName("Test Add New Value with shift in begin List")
    @Test
    public void testAddValuesWithShiftBeginListCorrect(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("F", 0);
        assertEquals(5, arrayList.size());
        assertEquals("[F, A, B, C, D]",arrayList.toString() );
    }

    @DisplayName("Attempt to Add null Value with shift in begin List return Exception")
    @Test
    public void testAddNullValuesWithShiftBeginListFail(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        assertThrows(NullPointerException.class, () -> { arrayList.add(null, 0); });
        assertEquals(4, arrayList.size());
        assertEquals("[A, B, C, D]",arrayList.toString() );
    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesWithIndexUponInitialCapacityCorrect(){
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        assertEquals(10, arrayList.size());
        arrayList.add("F", 5);
        System.out.println(arrayList.toString());
        assertEquals("[0, 1, 2, 3, 4, F, 5, 6, 7, 8, 9]",arrayList.toString() );
    }

    @DisplayName("Test attempt to Remove not existed index in List")
    @Test
    public void testAttemptRemoveByNoExistedIndexReturnException(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertThrows(IndexOutOfBoundsException.class, () -> {arrayList.remove(3);});
    }

    @DisplayName("Test attempt to Add null value to List, returns NullPointException")
    @Test
    public void testAttemptAddNullAsValueReturnException() {
        assertThrows(NullPointerException.class, () -> {arrayList.add(null);});
        assertTrue(arrayList.isEmpty());
    }

    @DisplayName("Test attempt to Get(0) value from empty List, returns Exception")
    @Test
    public void testAttemptOfGetValueFromEmptyListReturnException(){
        assertThrows(IndexOutOfBoundsException.class, () -> {arrayList.get(0);});
    }

    @DisplayName("Test get value by index from List")
    @Test
    public void testGetByExistedIndexTrueFromList(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("S");
        arrayList.add("D");

        assertEquals("S",arrayList.get(2));
    }

    @DisplayName("Test attempt to Get value by not existed index from List, returns Exception")
    @Test
    public void testAttemptGetByNoExistedIndexInListReturnException(){
        arrayList.add("A");
        arrayList.add("B");

        assertThrows(IndexOutOfBoundsException.class, () -> {arrayList.get(2);});
    }

    @DisplayName("Test attempt to Get value by already not existed index(because removed) from List, returns Exception")
    @Test
    public void testAttemptGetByNoExistedAlreadyIndexReturnException(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        arrayList.remove(2);
        assertThrows(IndexOutOfBoundsException.class, () -> {arrayList.get(2);});
    }

    @DisplayName("Test attempt to Set value by not existed index from List, returns Exception")
    @Test
    public void testAttemptSetToNoExistedIndexReturnException(){
        arrayList.add("A");
        assertThrows(IndexOutOfBoundsException.class, () -> {arrayList.set("B" , 1);});
    }

    @DisplayName("Test Set(update) value by index to List")
    @Test
    public void testSetValueByIndexNoSizeChangesExpected(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        assertEquals(3, arrayList.size());
        arrayList.set("F" , 1);
        assertEquals(3, arrayList.size());
        arrayList.set("E" , 2);
        assertEquals("[A, F, E]", arrayList.toString());
    }

    @DisplayName("Test Clear Existed List and check size")
    @Test
    public void testClearOfExistedListAndSizeChangesExpected(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        assertEquals(3, arrayList.size());
        arrayList.clear();
        assertEquals(0, arrayList.size());
        assertTrue(arrayList.isEmpty());
    }

    @DisplayName("Test Clear Empty List and check size")
    @Test
    public void testClearOfEmptyList(){
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @DisplayName("Test Contain of existed value in List")
    @Test
    public void testContainOfExistedValueInListReturnTrue(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(4, arrayList.size());
        assertTrue(arrayList.contains("C"));
    }

    @DisplayName("Test Contain of existed value (existed twice) in List")
    @Test
    public void testContainOfExistedTwiceValueInListReturnTrue(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("C");

        assertEquals(5, arrayList.size());
        assertTrue(arrayList.contains("C"));
    }

    @DisplayName("Test Contain of not existed value in List")
    @Test
    public void testNotContainOfValueInListReturnFalse(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(4, arrayList.size());
        assertFalse(arrayList.contains("F"));
    }

    @DisplayName("Find index of existed value in List")
    @Test
    public void testIndexOfExistedValueInListReturnIndex(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(2, arrayList.indexOf("C"));
    }

    @DisplayName("Attempt to find index of not existed value in List return -1")
    @Test
    public void testIndexOfNotExistedValueInListReturnMinus1(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(-1, arrayList.indexOf("F"));
    }

    @DisplayName("Find index of first existed search value in List")
    @Test
    public void testIndexOfFirstExistedValueInListReturnIndex(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("C");

        assertEquals(2, arrayList.indexOf("C"));
    }

    @DisplayName("Find latest index of existed value in List")
    @Test
    public void testLatestIndexOfExistedValueInListReturnIndex(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(2, arrayList.lastIndexOf("C"));
    }

    @DisplayName("Attempt to find latest index of not existed value in List return -1")
    @Test
    public void testLatestIndexOfNotExistedValueInListReturnMinus1(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(-1, arrayList.lastIndexOf("F"));
    }

    @DisplayName("Find latest index of multiple existed search values in List")
    @Test
    public void testLatestIndexOfMultiExistedValueInListReturnIndex(){
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("C");
        arrayList.add("F");
        arrayList.add("E");
        arrayList.add("C");
        arrayList.add("R");

        assertEquals(7, arrayList.lastIndexOf("C"));
    }

}
