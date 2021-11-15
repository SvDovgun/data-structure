package com.luxoft.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractListTest<T> {

    private List<T> list;
    private final T valueA = (T) "A";
    private final T valueB = (T) "B";
    private final T valueC = (T) "C";
    private final T valueD = (T) "D";
    private final T valueF = (T) "F";
    private final T valueE = (T) "E";
    private final T valueS = (T) "S";
    private final T valueT = (T) "T";
    private final T valueR = (T) "R";
    private final String[] arrayL =  { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};


    @BeforeEach
    protected void setUp()  {
        list = getList();
    }

    protected abstract List<T> getList();


    @DisplayName("Test Simple Add 1 Value and check size")
    @Test
    public void testAddValueByDefaultToEmptyListCorrectWithSizeChange() {
        list.add(valueA);

        assertEquals(1, list.size());
    }

    @DisplayName("Test Attempt Add Value By Negative Index Return Exception")
    @Test
    public void testAttemptAddValueByNegativeIndexReturnException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {list.add(valueA, -1);});

    }

    @DisplayName("Test Simple Add/Remove 1 Value Twice and check by size and isEmpty functions")
    @Test
    public void testAddAndRemoveValueByDefaultToEmptyListCorrect() {
        list.add(valueA);

        assertEquals(1, list.size());

        list.remove(0);

        assertTrue(list.isEmpty());

        list.add(valueB);
        assertEquals(1, list.size());
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @DisplayName("Test Add/Remove Values and check by size and toString functions")
    @Test
    public void testAddAndRemoveValuesCorrect(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        list.add(valueF);
        assertEquals(5, list.size());

        list.remove(2);
        assertEquals(4, list.size());

        assertEquals("[A, B, D, F]",list.toString() );
    }

    @DisplayName("Test Add Values and Remove First Value from List")
    @Test
    public void testAddAndRemoveFirstValuesAddNewCorrect(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);

        assertEquals(3, list.size());

        list.remove(0);
        assertEquals(2, list.size());

        assertEquals("[B, C]",list.toString() );

        list.add(valueD);  //2
        assertEquals(3, list.size());

        assertEquals("[B, C, D]",list.toString() );
    }


    @DisplayName("Test Add Values and Remove Last Value from List")
    @Test
    public void testAddAndRemoveLastValuesCorrect(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);

        list.remove(2);
        assertEquals("[A, B]",list.toString() );

    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesUponInitialCapacityCorrect(){
        for (int i = 0; i < 12; i++) {
            T value = (T) arrayL[i];
            list.add(value);
        }

        assertEquals(12, list.size());
    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesTwiceUponInitialCapacityCorrect(){
        for (int i = 0; i < 23; i++) {
            T value = (T) arrayL[i];
            list.add(value);
        }

        assertEquals(23, list.size());
    }

    @DisplayName("Test Add New Value with shift in middle List")
    @Test
    public void testAddValuesWithShiftMiddleListCorrect(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        list.add(valueF, 2);
        assertEquals(5, list.size());
        assertEquals("[A, B, F, C, D]",list.toString() );
    }

    @DisplayName("Test Add New Value with shift in begin List")
    @Test
    public void testAddValuesWithShiftBeginListCorrect(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        list.add(valueF, 0);
        assertEquals(5, list.size());
        assertEquals("[F, A, B, C, D]",list.toString() );
    }

    @DisplayName("Attempt to Add null Value with shift in begin List return Exception")
    @Test
    public void testAddNullValuesWithShiftBeginListFail(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        assertThrows(NullPointerException.class, () -> { list.add(null, 0); });
        assertEquals(4, list.size());
        assertEquals("[A, B, C, D]",list.toString() );
    }

    @DisplayName("Test Add Values and check by Capacity functions")
    @Test
    public void testAddValuesWithIndexUponInitialCapacityCorrect(){
        for (int i = 0; i < 10; i++) {
            T value = (T) arrayL[i];
            list.add(value);
        }
        assertEquals(10, list.size());
        list.add(valueF, 5);
    //    System.out.println(list.toString());
        assertEquals("[0, 1, 2, 3, 4, F, 5, 6, 7, 8, 9]",list.toString() );
    }

    @DisplayName("Test attempt to Remove not existed index in List")
    @Test
    public void testAttemptRemoveByNoExistedIndexReturnException(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);

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
        list.add(valueA);
        list.add(valueB);
        list.add(valueS);
        list.add(valueD);

        assertEquals("S",list.get(2));
    }

    @DisplayName("Test attempt to Get value by not existed index from List, returns Exception")
    @Test
    public void testAttemptGetByNoExistedIndexInListReturnException(){
        list.add(valueA);
        list.add(valueB);

        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(2);});
    }

    @DisplayName("Test attempt to Get value by already not existed index(because removed) from List, returns Exception")
    @Test
    public void testAttemptGetByNoExistedAlreadyIndexReturnException(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);

        list.remove(2);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(2);});
    }

    @DisplayName("Test attempt to Set value by not existed index from List, returns Exception")
    @Test
    public void testAttemptSetToNoExistedIndexReturnException(){
        list.add(valueA);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.set(valueB , 1);});
    }

    @DisplayName("Test Set(update) value by index to List")
    @Test
    public void testSetValueByIndexNoSizeChangesExpected(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        assertEquals(3, list.size());
        list.set(valueF , 1);
        assertEquals(3, list.size());
        list.set(valueE , 2);
        assertEquals("[A, F, E]", list.toString());
    }

    @DisplayName("Test Clear Existed List and check size")
    @Test
    public void testClearOfExistedListAndSizeChangesExpected(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
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
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        assertEquals(4, list.size());
        assertTrue(list.contains(valueC));
    }

    @DisplayName("Test Contain of existed value (existed twice) in List")
    @Test
    public void testContainOfExistedTwiceValueInListReturnTrue(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        list.add(valueC);

        assertEquals(5, list.size());
        assertTrue(list.contains(valueC));
    }

    @DisplayName("Test Contain of not existed value in List")
    @Test
    public void testNotContainOfValueInListReturnFalse(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        assertEquals(4, list.size());
        assertFalse(list.contains(valueF));
    }

    @DisplayName("Find index of existed value in List")
    @Test
    public void testIndexOfExistedValueInListReturnIndex(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        assertEquals(2, list.indexOf(valueC));
    }

    @DisplayName("Attempt to find index of NULL value in List")
    @Test
    public void testIndexOfNullValueInListReturn(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        assertThrows(NullPointerException.class, () -> { list.indexOf(null); });
    }

    @DisplayName("Attempt to find index of not existed value in List return -1")
    @Test
    public void testIndexOfNotExistedValueInListReturnMinus1(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        assertEquals(-1, list.indexOf(valueF));
    }

    @DisplayName("Find index of first existed search value in List")
    @Test
    public void testIndexOfFirstExistedValueInListReturnIndex(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        list.add(valueC);

        assertEquals(2, list.indexOf(valueC));
    }

    @DisplayName("Find latest index of existed value in List")
    @Test
    public void testLatestIndexOfExistedValueInListReturnIndex(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        assertEquals(2, list.lastIndexOf(valueC));
    }

    @DisplayName("Attempt to find latest index of not existed value in List return -1")
    @Test
    public void testLatestIndexOfNotExistedValueInListReturnMinus1(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        assertEquals(-1, list.lastIndexOf(valueF));
    }

    @DisplayName("Find latest index of multiple existed search values in List")
    @Test
    public void testLatestIndexOfMultiExistedValueInListReturnIndex(){
        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);
        list.add(valueC);
        list.add(valueF);
        list.add(valueE);
        list.add(valueC);
        list.add(valueR);

        assertEquals(7, list.lastIndexOf(valueC));
    }

    @Test
    public void testIteratorHasNextCorrect(){
        list.add(valueA);
        list.add(valueD);

        Iterator iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
    //    assertTrue(iterator.hasNext());
        assertEquals("D", iterator.next());
        assertFalse(iterator.hasNext());

    }

    @Test
    public void testIteratorStringOneValue(){
        list.add(valueS);
        Iterator iterator = list.iterator();
        assertEquals("S", iterator.next());
        assertEquals("[S]", list.toIteratorString());
    }

    @Test
    public void testIteratorString(){
        list.add(valueA);
        list.add(valueD);
        list.add(valueC);
        list.add(valueT);
        assertEquals("[A, D, C, T]", list.toIteratorString());
    }

    @Test
    public void testIteratorStringNoValue(){
        Iterator iterator = list.iterator();
        assertThrows(RuntimeException.class, () -> {iterator.next();});
   //     assertEquals("[]", list.toIteratorString());
    }

    @Test
    public void testIteratorRemoveLastElement(){
        list.add(valueA);
        list.add(valueD);
        list.add(valueC);
        list.add(valueT);
        Iterator iterator = list.iterator();
        iterator.remove();
            assertEquals("[A, D, C]", list.toIteratorString());
    }

    @Test
    public void testIteratorRemoveAllElements(){
        list.add(valueA);
        list.add(valueC);
        list.add(valueT);
        Iterator iterator = list.iterator();
        for (int i = list.size(); i > 0; i--) {
            iterator.remove();
        }
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIteratorAttemptRemoveEmptyList(){
        assertTrue(list.isEmpty());
        Iterator iterator = list.iterator();
        assertThrows(RuntimeException.class, () -> {iterator.remove();});

    }
}
