package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScanningUtilTest {

    @Test
    void givenStringSystemWillReturnString() {
        // given
        String expectedString = "Test";
        // when
        String actualString = ScanningUtil.doScanningAndGetIntValue();
        // then
        assertEquals(expectedString,actualString);
    }


}