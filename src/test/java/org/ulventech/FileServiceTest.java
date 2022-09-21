package org.ulventech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    @BeforeEach
    void setUp(){
    }

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

        String expectedLocation = new StringBuilder(Paths.get("").toAbsolutePath().toString())
                .append("\\src\\main\\resources\\")
                .append("uvTech.txt")
                .toString();

        String actualFilePath = FileService.getOutFilePath();
        assertEquals(expectedLocation,actualFilePath);
    }
    //endregion
}