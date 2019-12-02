package utils;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
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
            PDDocument document = PDDocument.load(sourceFile);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int dpi = 70;// use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi 
            BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, dpi, ImageType.RGB);
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