package io.nickbaynham.automation.regex;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestRegEx {
    @Test
    public void testRegExIsMatch() {
        assertTrue(RegEx.isMatch("foo", "foo"));
    }

    @Test
    public void testRegExGetNumberOfMatches() {
        assertEquals(RegEx.getNumberOfMatches("foofoo", "foo"), 2);
    }

    @Test
    public void testRegExDotMatching() {
        assertTrue(RegEx.getNumberOfMatches("foo", ".") > 0);
    }

    @Test
    public void testRegExDotMatchingOnce() {
        assertEquals(RegEx.getNumberOfMatches("foofoo", "foo."), 1);
    }
}
