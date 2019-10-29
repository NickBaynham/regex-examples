package io.nickbaynham.automation.regex;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static io.nickbaynham.automation.regex.RegEx.*;

public class TestRegEx {
    @Test
    public void testRegExIsMatch() {
        assertTrue(isMatch("foo", "foo"));
    }

    @Test
    public void testRegExGetNumberOfMatches() {
        assertEquals(getNumberOfMatches("foofoo", "foo"), 2);
    }

    @Test
    public void testRegExDotMatching() {
        assertTrue(getNumberOfMatches("foo", ".") > 0);
    }

    @Test
    public void testRegExDotMatchingOnce() {
        assertEquals(getNumberOfMatches("foofoo", "foo."), 1);
    }

    // https://regex101.com/r/V0StHc/1

    @Test
    public void testElementRegEx() {
        final String regex = "<(.+)>([^<]+)<\\/\\1>";
        assertEquals(getNumberOfMatches("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>", regex), 2);
        assertEquals(getNumberOfMatches("<h1>Nayeem loves counseling</h1>", regex), 1);
        assertEquals(getNumberOfMatches("<Amee>safat codes like a ninja</amee>", regex), 0);
        assertEquals(getNumberOfMatches("<SA premium>Imtiaz has a secret crush</SA premium>", regex), 1);
    }

    @Test
    public void testOrClass() {
        int matches = getNumberOfMatches("b", "[abc]");
        assertEquals(matches, 1);
    }

    @Test
    public void testOrClassMultiMatch() {
        int matches = getNumberOfMatches("cab", "[abc]");
        assertEquals(matches, 3);
    }

    @Test
    public void testOrClassAlternated() {
        int matches = getNumberOfMatches("bat cat rat","[bcr]at");
        assertEquals(matches, 3);
    }
}

