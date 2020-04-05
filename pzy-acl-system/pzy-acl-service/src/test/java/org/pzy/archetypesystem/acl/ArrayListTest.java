package org.pzy.archetypesystem.acl;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayListTest
 *
 * @author pan
 * @date 2020/4/3 17:15
 */
public class ArrayListTest {
    @Test
    public void test(){
        List<String> list =new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add(1,"insert");
        System.out.println(list);
    }
}
