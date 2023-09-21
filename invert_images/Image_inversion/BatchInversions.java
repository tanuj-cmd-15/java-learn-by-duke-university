import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        // Create a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        // Iterate through each pixel in the input image
        for (Pixel pixel : outImage.pixels()) {
            // Get the corresponding pixel in the input image
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            
            // Calculate the inverted values for red, green, and blue
            int invertedRed = 255 - inPixel.getRed();
            int invertedGreen = 255 - inPixel.getGreen();
            int invertedBlue = 255 - inPixel.getBlue();
            
            // Set the red, green, and blue values of the output pixel to the inverted values
            pixel.setRed(invertedRed);
            pixel.setGreen(invertedGreen);
            pixel.setBlue(invertedBlue);
        }
        
        // Return the inverted output image
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            // Create the inverted image
            ImageResource invertedImage = makeInversion(inImage);
            // Get the original filename
            String oldFilename = inImage.getFileName();
            // Create a new filename by adding "inverted-" in front of the old filename
            String newFilename = "inverted-" + oldFilename;
            // Save the inverted image with the new filename
            invertedImage.setFileName(newFilename);
            invertedImage.save();
            // Display the inverted image
            invertedImage.draw();
        }
    }

    public static void main(String[] args) {
        BatchInversions batch = new BatchInversions();
        batch.selectAndConvert();
        
    }
}
