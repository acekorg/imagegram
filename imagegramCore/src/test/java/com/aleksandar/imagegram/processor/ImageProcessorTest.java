package com.aleksandar.imagegram.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link ImageProcessor}
 */
@ExtendWith(MockitoExtension.class)
public class ImageProcessorTest {

  @Test
  public void shouldProcessImage() throws IOException {

    // Given
    byte[] inputImage = loadFile("src/test/resources/images/imageTestInput.bmp");
    byte[] outputImage = loadFile("src/test/resources/images/imageTestOutput.jpeg");
    StopWatch stopWatch = new StopWatch();

    // When
    stopWatch.start();
    byte[] processedImage = ImageProcessor.process(inputImage);
    stopWatch.stop();

    assertTrue(Arrays.equals(outputImage, processedImage));

    // We don't want to fail the build because of slow processing but only give warning.
    // As this test code is being executed on the build server, it can have different execution times when executed
    // locally or in production.
    long totalProcessingTime = stopWatch.getTotalTimeMillis();
    if (totalProcessingTime > 3000) {
      System.out.println("WARN: The image processing time is more than 3 second");
      System.out.println(String.format("WARN: It took %s milliseconds for total processing time", totalProcessingTime));
    }
  }

  public byte[] loadFile(String filePath) throws IOException {
    File file = new File(filePath);
    FileInputStream inputStream = new FileInputStream(file);
    return inputStream.readAllBytes();
  }
}
