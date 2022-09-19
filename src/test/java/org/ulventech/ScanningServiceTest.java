package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScanningServiceTest {

    @Test
    void givenStringSystemWillReturnString() {
        // given
        String expectedString = "Test";
        // when
        String actualString = ScanningService.doScanningAndGetIntValue();
        // then
        assertEquals(expectedString,actualString);
    }


}