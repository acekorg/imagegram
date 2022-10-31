package com.aleksandar.imagegram.processor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Image processor for converting images to desired format and resolution.
 */
public final class ImageProcessor {

  private static final int IMAGE_WIDTH = 600;
  private static final int IMAGE_HEIGHT = 600;
  private static final String IMAGE_FORMAT = "jpg";

  /**
   * Converts image to jpg format and 600x600 resolution.
   * @param image The provided image.
   * @return The result image.
   */
  public static byte[] process(byte[] image) {

    try {
      InputStream imageInputStream = new ByteArrayInputStream(image);
      BufferedImage bufferedImage = ImageIO.read(imageInputStream);

      final BufferedImage processedImage =
          new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

      Graphics2D graphics = processedImage.createGraphics();
      graphics.drawImage(bufferedImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, Color.WHITE,null);

      ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
      ImageIO.write(processedImage, IMAGE_FORMAT, imageOutputStream);
      return imageOutputStream.toByteArray();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
