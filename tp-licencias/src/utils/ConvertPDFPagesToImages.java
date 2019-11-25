package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ConvertPDFPagesToImages {
	
    public static BufferedImage convertPDFPagesToImages(String dirPDF, String dirImg) {
        try {
        String sourceDir = dirPDF; // Pdf files are read from this folder
        String destinationDir = dirImg; // converted images from pdf document are saved here
        File sourceFile = new File(sourceDir);
        File destinationFile = new File(destinationDir);
        if (!destinationFile.exists()) {
            destinationFile.mkdir();
            System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
        }
        if (sourceFile.exists()) {
        	System.out.println("Images copied to Folder Location: "+ destinationFile.getAbsolutePath());             
            PDDocument document = PDDocument.load(sourceFile);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();
            String fileName = sourceFile.getName().replace(".pdf", "");             
            String fileExtension= "jpg";
            /*
             * 600 dpi give good image clarity but size of each image is 2x times of 300 dpi.
             * Ex:  1. For 300dpi 04-Request-Headers_2.png expected size is 797 KB
             *      2. For 600dpi 04-Request-Headers_2.png expected size is 2.42 MB
             */
            int dpi = 300;// use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi 
            File outPutFile = new File(destinationDir + fileName +"."+ fileExtension);
            BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, dpi, ImageType.RGB);
            ImageIO.write(bImage, fileExtension, outPutFile);
            document.close();
            return bImage;
        } else
            System.err.println(sourceFile.getName() +" File not exists");
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
    }
}