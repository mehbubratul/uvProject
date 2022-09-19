package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

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

    @Test
    void systemWillGenerateValidOutFilePath(){
        String expectedFilePath = "E:\\git\\uvProject\\src\\main\\resources\\uvTech.txt";
        String actualFilePath = FileService.getOutFilePath();
        assertEquals(expectedFilePath,actualFilePath);
    }
    //endregion
}