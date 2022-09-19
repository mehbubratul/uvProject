package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UVTechAppTest {

    //region valid test cases
    @Test
    void givenFilePathAndLineNumberWillReturnTrue() {
        // given
        int number = 10;
        // when
        boolean isDone = FileService.writeContentToFile(number, FileService.getOutFilePath());
        // then
        assertTrue(isDone);
    }

    @Test
    void givenFilePathAndLineNumberIsZeroWillReturnFalse() {
        // given
        int number = 0;
        // when
        boolean isDone = FileService.writeContentToFile(number, FileService.getOutFilePath());
        // then
        assertFalse(isDone);
    }

    @Test
    void givenNullOrEmptyAsFilePathAndLineNumberIsZeroWillReturnFalse() {
        // given
        int number = 0;
        // when
        boolean isDone = FileService.writeContentToFile(number, null);
        // then
        assertFalse(isDone);
    }
    //endregion
}