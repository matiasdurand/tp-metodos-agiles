package gui;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dto.LicenseDTO;

public class GeneratePDF{
	
	private Font fontInfo = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
	
	public GeneratePDF(LicenseDTO dto, String rutaDestino) {
		try {
			Document doc = new Document(PageSize.A4, 254, 254, 254, 254);
			PdfWriter.getInstance(doc, new FileOutputStream(rutaDestino));
			doc.open();
			Image img = Image.getInstance("/res/images/template_licencia.jpg");
			img.scaleAbsolute(100, 100);
			img.setAlignment(Element.ALIGN_CENTER);
			doc.add(img);
			doc.add(getInfo(dto));
			doc.close();
		}catch(Exception e) {}
	}
	
	private Paragraph getInfo(LicenseDTO dto) {
		Paragraph p = new Paragraph();
		Chunk c = new Chunk();
		p.setAlignment(Element.ALIGN_LEFT);
		c.append(dto.getName());
		c.setFont(fontInfo);
	}
}
