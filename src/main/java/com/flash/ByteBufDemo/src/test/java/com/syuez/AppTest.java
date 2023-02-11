package com.syuez;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void test1 () {
        byte[] bytes = new byte[] {1, 2, 3, 4};

        for (byte b : bytes) {
            String binaryString = Integer.toBinaryString(b & 0xFF);
            System.out.println(binaryString);
        }
    }

    public void test2 () {
        byte[] bytes = new byte[] {1, 2, 3, 4};
        int result = ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
        System.out.println(result);
    }

    public void test3() {
        // Create a byte array with the values 1, 2, and 3
        byte[] myArray = new byte[] {(byte) 1990, 2, 3};

        // Print the values of the array
        for (byte b : myArray) {
            System.out.println(b);
        }

        // Modify the second element of the array
        myArray[1] = 4;

        // Print the new values of the array
        for (byte b : myArray) {
            System.out.println(b);
        }

        System.out.println(myArray.length);

    }
}
