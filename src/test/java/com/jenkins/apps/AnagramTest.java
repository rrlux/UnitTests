package com.jenkins.apps;

import static org.junit.Assert.*;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class AnagramTest {

    @Test (expected = NullPointerException.class)
    public void testAnagramNull() throws NullPointerException {

        Anagram.anagram(null);

    }

    @Test
    public void testAnagramLongLine() {

        String result = Anagram.anagram(" 1ytr2ewq3 hgfdsa  123vcxz456_ ");

        assertThat(result, equalTo(" 1qwe2rty3 asdfgh  123zxcv456_ "));
    }

    @Test
    public void testAnagramEmptyLine() {

        String result = Anagram.anagram("");

        assertThat(result, equalTo(""));

    }

    @Test
    public void testAnagramOneWord() {

        String result = Anagram.anagram(" 1ytr2ewq3_");

        assertThat(result, equalTo(" 1qwe2rty3_"));

    }

    @Test
    public void testAnagramOnlyLetters() {

        String result = Anagram.anagram("LkjhgfdsA");

        assertThat(result, equalTo("AsdfghjkL"));
    }

    @Test
    public void testAnagramOnlyNonLetters() {

        String result = Anagram.anagram("176:>?987-_ 23<>");

        assertThat(result, equalTo("176:>?987-_ 23<>"));
    }

    @Test
    public void testAnagramOnlySpaces() {

        String result = Anagram.anagram("       ");

        assertThat(result, equalTo("       "));
    }

    @Test
    public void testAnagramOnlyNumbers() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("1234567890"));
    }

    /*@Test
    public void testFailed1() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("12345678"));
    }

    @Test
    public void testFailed2() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("12345678"));
    }

    @Test
    public void testFailed3() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("12345678"));
    }

    @Test
    public void testFailed4() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("12345678"));
    }

    @Test
    public void testFailed5() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("12345678"));
    }

    @Test
    public void testFailed6() {

        String result = Anagram.anagram("1234567890");

        assertThat(result, equalTo("12345678"));
    }
*/




}
