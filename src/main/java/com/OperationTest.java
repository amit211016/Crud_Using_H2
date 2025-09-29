package com;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class OperationTest {

    @Test
    public void testSum(){
        Operation op = new Operation();
         assertEquals(100, op.add(2,3));
    }


}
