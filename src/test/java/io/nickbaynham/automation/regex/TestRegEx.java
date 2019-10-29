package io.nickbaynham.automation.regex;

import org.testng.annotations.Test;

import java.util.regex.Pattern;

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

    @Test
    public void testNORSet() {
        int matches = getNumberOfMatches("g", "[^abc]");
        assertEquals(matches, 1);
    }

    @Test
    public void testNORSet2() {
        int matches = getNumberOfMatches("sat mat eat", "[^bcr]at");
        assertEquals(matches, 3);
    }

    @Test
    public void testUppercaseWithRanges() {
        int matches = getNumberOfMatches("Two Uppercase alphabets 34 overall", "[a-z]");
        assertEquals(matches, 26);
    }

    @Test
    public void testUppercaseLowercaseWithRanges() {
        int matches = getNumberOfMatches("Two Uppercase alphabets 34 overall", "[aA-zZ]");
        assertEquals(matches, 28);
    }

    @Test
    public void testNumbersWithRanges() {
        int matches = getNumberOfMatches("Two Uppercase alphabets 34 overall", "[1-5]");
        assertEquals(matches, 2);
    }

    @Test
    public void testNumbersWithAnotherRange() {
        int matches = getNumberOfMatches("Two Uppercase alphabets 34 overall", "[30-35]");
        assertEquals(matches, 1);
    }

    @Test
    public void testPredefinedCharacterClasses() {
        assertEquals(getNumberOfMatches("123", "\\d"), 3);      // Digit
        assertEquals(getNumberOfMatches("a6c", "\\D"), 2);      // Non Digit
        assertEquals(getNumberOfMatches("a c", "\\s"), 1);      // White Space Characters
        assertEquals(getNumberOfMatches("a c", "\\S"), 2);      // Non White Space
        assertEquals(getNumberOfMatches("hi!", "\\W"), 1);      // Non Word Character
    }

    @Test
    public void testZeroOrOneQuantifier() {
        assertEquals(getNumberOfMatches("hi", "\\a?"), 3);
        assertEquals(getNumberOfMatches("hi", "\\a{0,1}"), 3);
    }

    @Test
    public void testOneOrManyQuantifier() {
        assertFalse(getNumberOfMatches("hi", "\\a+")>0);
        assertEquals(getNumberOfMatches("hi", "\\a{1,}"),0);
    }

    @Test
    public void testBraceQuantifier() {
        assertEquals(getNumberOfMatches("aaaaaa", "a{3}"), 2);
        assertEquals(getNumberOfMatches("aa", "a{3}"), 0);
        assertEquals(getNumberOfMatches("aaaa", "a{2,3}"), 1);
        assertEquals(getNumberOfMatches("aaaa", "a{2,3}?"), 2);
    }

    @Test
    public void testCapturingGroups() {
        assertEquals(getNumberOfMatches("12","(\\d\\d)"), 1);
        assertEquals(getNumberOfMatches("1212", "(\\d\\d)"), 2);
        assertEquals(getNumberOfMatches("1212","(\\d\\d)\\1"), 1);              // back referencing
        assertEquals(getNumberOfMatches("1212", "(\\d\\d)(\\d\\d)"), 1);        // same result without back referencing
        assertEquals(getNumberOfMatches("1213", "(\\d\\d)\\1"), 0);
    }

    @Test
    public void testBoundaryMatchers() {
        assertTrue(getNumberOfMatches("dogs are friendly", "^dog") > 0);        // begins with dog
        assertFalse(getNumberOfMatches("is a dog man's best friend?", "dog$") > 0);
        assertTrue(getNumberOfMatches("Man's best friend is a dog", "dog$") > 0);
    }

    @Test
    public void testWordBoundary() {
        assertTrue(getNumberOfMatches("a dog is friendly","\\bdog\\b") > 0);
        assertTrue(getNumberOfMatches("dog is man's best friend","\\bdog\\b") > 0);
        assertFalse(getNumberOfMatches("snoop dogg is a wrapper","\\bdog\\b") > 0);
        assertTrue(getNumberOfMatches("snoop dogg is a wrapper","\\bdog\\B") > 0);
    }

    @Test
    public void testCaseInsensitiveFlag() {
        assertTrue(getNumberOfMatches("This is a Dog", "dog", Pattern.CASE_INSENSITIVE) > 0);
    }
}

