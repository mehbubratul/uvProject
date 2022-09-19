package org.ulventech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringGenerationUtilTest {

    private static final int STRING_LENGTH_FOR_EACH_LINE = 100;

    @BeforeEach
    void setUp() {
        // given
        // when
        // then
    }

    //region Test related to method "isInputValueValid"
    @Test
    void givenEmptyAsInputWillReturnFalse() {
        // given
        String givenString = "";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertFalse(actualValue);
    }

    @Test
    void givenNullAsInputWillReturnFalse() {
        // given
        String givenString = null;
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertFalse(actualValue);
    }

    @Test
    void givenStringThatIsNotConvertibleToIntegerAsInputWillReturnFalse() {
        // given
        String givenString = "abc";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertFalse(actualValue);
    }

    @Test
    void givenValidStringThatIsConvertibleToIntegerAsInputWillReturnTrue() {
        // given
        String givenString = "2";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertTrue(actualValue);
    }

    @Test
    void givenValidStringThatIsConvertibleToIntegerButLowerThanAcceptedLimitAsInputWillReturnFalse() {
        // given
        String givenString = "0";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertFalse(actualValue);
    }

    @Test
    void givenValidStringThatIsConvertibleToIntegerButUpperThanAcceptedLimitAsInputWillReturnFalse() {
        // given
        String givenString = "1073741824";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertFalse(actualValue);
    }

    @Test
    void givenValidStringThatIsConvertibleToIntegerAndEqualToUpperAcceptedLimitAsInputWillReturnTrue() {
        // given
        String givenString = "1073741823";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertTrue(actualValue);
    }

    @Test
    void givenValidStringThatIsConvertibleToIntegerAndEqualToLowerAcceptedLimitAsInputWillReturnTrue() {
        // given
        String givenString = "1";
        // when
        boolean actualValue = RandomStringGenerationUtil.isInputValueValid(givenString);
        // then
        assertTrue(actualValue);
    }
    //endregion

    //region Test related to method "getInitialBytes"
    @Test
    void givenExpectedBytesArrayInStringWillMatchWithInitialBytesInString() {
        // given
        String expectedBytesInString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        // when
        byte[] actualBytes = RandomStringGenerationUtil.getInitialBytes();
        // then
        assertEquals(expectedBytesInString, new String(actualBytes));
    }
    //endregion

    //region Test related to method "getConsecutiveBytes"
    @Test
    void givenBytesArrayWillReturnNextBytesArray() {
        // given
        String givenBytesInString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        byte[] givenBytes = givenBytesInString.getBytes();

        // expected
        String expectedBytesInString = "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        byte[] expectedBytes = expectedBytesInString.getBytes();

        // when
        byte[] actualBytes = RandomStringGenerationUtil.getConsecutiveBytes(givenBytes);

        // then
        assertTrue(Arrays.equals(expectedBytes, actualBytes));
    }

    @Test
    void givenEmptyBytesArrayWillReturnEmptyBytesArray() {
        // given
        String givenBytesInString = "";
        byte[] givenBytes = givenBytesInString.getBytes();

        // expected
        String expectedBytesInString = "";
        byte[] expectedBytes = expectedBytesInString.getBytes();

        // when
        byte[] actualBytes = RandomStringGenerationUtil.getConsecutiveBytes(givenBytes);
        System.out.println(new String(actualBytes));
        // then
        assertTrue(Arrays.equals(expectedBytes, actualBytes));
    }

    @Test
    void givenBytesArrayThatIsLessThenTheRequiredLengthWillReturnTrue() {
        // given
        String givenBytesInString = "aaa";
        byte[] givenBytes = givenBytesInString.getBytes();

        // expected
        int expectedBytesLength = STRING_LENGTH_FOR_EACH_LINE;

        // when
        byte[] actualBytes = RandomStringGenerationUtil.getConsecutiveBytes(givenBytes);
        int actualBytesLength = actualBytes.length;

        System.out.println(actualBytesLength);
        // then
        assertTrue(expectedBytesLength != actualBytesLength);
    }

    //endregion
}