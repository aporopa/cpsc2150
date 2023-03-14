package cpsc2150.MyDeque;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestListDeque {
    private IDeque<Integer> MakeADeque(){
        return new ListDeque<Integer>(); //Could not return IDeque itself FIXME?
    }

    @Test
    public void test_enqueue1(){
        IDeque<Integer> d = MakeADeque();
        for(int i = 0; i < 5; i++){
            d.enqueue(i);
        }

        String expected = "<0, 1, 2, 3, 4>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_enqueue2(){
        IDeque<Integer> d = MakeADeque();
        d.enqueue(10);

        String expected = "<10>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_enqueue3(){
        IDeque<Integer> d = MakeADeque();
        d.enqueue(1);
        d.enqueue(2);
        d.enqueue(3);
        d.enqueue(4);
        d.enqueue(5);

        String expected = "<1, 2, 3, 4, 5>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_dequeue1(){
        IDeque<Integer> d = MakeADeque();
        for(int i = 0; i < 5; i++){
            d.enqueue(i);
        }

        d.dequeue();

        String expected = "<1, 2, 3, 4>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_dequeue2(){
        IDeque<Integer> d = MakeADeque();
        d.enqueue(6);
        d.dequeue();

        String expected = "<>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_dequeue3(){
        IDeque<Integer> d = MakeADeque();

        d.enqueue(1);
        d.enqueue(2);
        d.enqueue(3);
        d.enqueue(4);
        d.enqueue(5);

        d.dequeue();
        d.dequeue();
        d.dequeue();

        String expected = "<4, 5>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_Inject1(){
        IDeque<Integer> d = MakeADeque();

        d.inject(1);
        d.inject(2);
        d.inject(3);

        String expected = "<3, 2, 1>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_Inject2(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 1; i <= 5; i++){
            d.inject(i);
        }

        String expected = "<5, 4, 3, 2, 1>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_Inject3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 3; i >= 0; i--){
            d.inject(i);
        }

        String expected = "<0, 1, 2, 3>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));

        d.inject(-1);
        d.inject(-2);
        d.inject(-3);

        String expected2 = "<-3, -2, -1, 0, 1, 2, 3>";
        String actual2 = d.toString();
        assertTrue(expected2.equals(actual2));

    }

    @Test
    public void test_RemoveLast1(){
        IDeque<Integer> d = MakeADeque();
        d.enqueue(250);
        int removedLast = d.removeLast();

        assertEquals(250,removedLast);
        String expected = "<>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_RemoveLast2(){
        IDeque<Integer> d = MakeADeque();
        d.enqueue(10);
        d.enqueue(20);
        d.enqueue(30);

        int removedLast = d.removeLast();
        assertEquals(30,removedLast);

        String expected = "<10, 20>";
        String actual = d.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_RemoveLast3(){
        IDeque<Integer> d = MakeADeque();
        for(int i = 3; i >= 1; i--){
            d.inject(i);
        }

        d.enqueue(4);
        d.enqueue(5);
        d.enqueue(6);

        String expected = "<1, 2, 3, 4, 5, 6>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));

        int removedLast = d.removeLast();
        assertEquals(6,removedLast);

        String expected2 = "<1, 2, 3, 4, 5>";
        String actual2 = d.toString();

        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void test_clear1(){
        IDeque<Integer> d = MakeADeque();

        d.enqueue(null);
        d.clear();

        String expected = "<>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_clear2(){
        IDeque<Integer> d = MakeADeque();

        d.enqueue(1);
        d.enqueue(10);
        d.enqueue(100);
        d.enqueue(1000);
        d.enqueue(10000);
        d.enqueue(100000);
        d.clear();

        String expected = "<>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_clear3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }
        d.inject(-1);
        d.removeLast();

        String expected = "<-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));

        d.clear();

        String expected2 = "<>";
        String actual2 = d.toString();
        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void test_peek1(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer peekVal = d.peek();

        assertTrue(peekVal == 0);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_peek2(){
        IDeque<Integer> d = MakeADeque();
        d.enqueue(5);
        d.enqueue(10);
        d.enqueue(15);

        d.dequeue();
        d.inject(0);

        Integer peekVal = d.peek();
        assertTrue(peekVal == 0);

        String expected = "<0, 10, 15>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_peek3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = -10; i <= 10; i++){
            d.enqueue(i);
        }

        d.dequeue();
        d.dequeue();
        d.dequeue();
        d.enqueue(11);
        d.enqueue(12);
        d.enqueue(13);

        String expected = "<-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));

        Integer peekVal = d.peek();

        assertTrue(peekVal == -7);

        String expected2 = "<-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13>";
        String actual2 = d.toString();
        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void test_endOfDeque1(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer EODVal = d.endOfDeque();

        assertTrue(EODVal == 10);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_endOfDeque2(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        d.dequeue();
        d.dequeue();
        d.dequeue();

        String expected = "<3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));

        Integer EODVal = d.endOfDeque();

        assertTrue(EODVal == 10);

        String expected2 = "<3, 4, 5, 6, 7, 8, 9, 10>";
        String actual2 = d.toString();
        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void test_endOfDeque3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        d.dequeue();
        d.dequeue();
        d.dequeue();
        d.enqueue(11);
        d.enqueue(12);
        d.enqueue(13);

        String expected = "<3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));

        Integer EODVal = d.endOfDeque();

        assertTrue(EODVal == 13);

        String expected2 = "<3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13>";
        String actual2 = d.toString();
        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void test_insert1(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        d.insert(47, 5);

        String expected = "<0, 1, 2, 3, 47, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_insert2(){
        IDeque<Integer> d = MakeADeque();

        d.insert(47, 1);

        String expected = "<47>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_insert3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        d.insert(47, 12);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 47>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_remove1(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer rmVal = d.remove(6);

        assertTrue(rmVal == 5);

        String expected = "<0, 1, 2, 3, 4, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_remove2(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer rmVal = d.remove(1);

        assertTrue(rmVal == 0);

        String expected = "<1, 2, 3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_remove3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer rmVal = d.remove(11);

        assertTrue(rmVal == 10);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_get1(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer getVal = d.get(6);

        assertTrue(getVal == 5);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_get2(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer getVal = d.get(1);

        assertTrue(getVal == 0);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test_get3(){
        IDeque<Integer> d = MakeADeque();

        for(int i = 0; i <= 10; i++){
            d.enqueue(i);
        }

        Integer getVal = d.get(11);

        assertTrue(getVal == 10);

        String expected = "<0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10>";
        String actual = d.toString();
        assertTrue(expected.equals(actual));
    }
}