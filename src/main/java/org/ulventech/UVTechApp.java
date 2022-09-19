package org.ulventech;

public class UVTechApp {

    public static void main(String[] args) {
        performProcess();
    }

    private static void performProcess() {

        String inputValue = ScanningService.doScanningAndGetIntValue();

        if (!RandomStringGenerationService.isInputValueValid(inputValue)) {
            return;
        }

        System.out.println("Please wait while the program is running... ");

        String filePath = FileService.getOutFilePath();

        FileService.writeContentToFile(Integer.parseInt(inputValue), filePath);

        System.out.println("Writing to file is done in location : " + filePath);
    }
}
