package com.dvsnier.test.widget;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_one() {
        CharSequence charSequence = "12\n\n21";
        String[] strings = charSequence.toString().split("\n");
        System.out.println(strings);
        System.out.println(strings.length);
    }

    @Test
    public void test_two() {
        List item = new ArrayList();
        item.add("a");
        item.add("b");
        item.add("c");
        item.add(" ");
        item.add("\n");
        System.out.println(item);
        item.add(3, "d");
        System.out.println(item);
        item.set(3, " ");
        item.add(4, "e");
        System.out.println(item);
    }

    @Test
    public void test_three() {
        String item = "0987654321";  // substring belong to [startIndex,endIndex)
        String firstItem = item.substring(0, 5);
        String lastItem = item.substring(5, item.length());
        System.out.println(item);
    }
}