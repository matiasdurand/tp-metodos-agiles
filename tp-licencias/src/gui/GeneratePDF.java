<<<<<<< HEAD
package gui;
=======
/*package gui;

import java.awt.EventQueue;
>>>>>>> 5d2ddb0a2a1ca1e03f8606fcf182332a49d463d0
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import dto.PdfDTO;

//import dto.LicenseDTO;

@SuppressWarnings("deprecation")
public class GeneratePDF{
	
	private PdfFont fontInfo;
	private static  String _DESTINO = "C:\\Users\\alan_\\Desktop\\licencia.pdf";
	private String _TEMPLATE_LICENCIA_DOBLE = "C:/Users/alan_/eclipse-workspace/tp-metodos-agiles/tp-licencias/src/res/images/template_licencia_frente_dorso.jpg";
	
	public static void main(String args[]) throws IOException, java.io.IOException {
		new GeneratePDF(_DESTINO);
	}
	
	public GeneratePDF(String destino) throws IOException, java.io.IOException{
		try {
			PdfWriter writer = new PdfWriter(destino);
			PdfDocument pdf = new PdfDocument(writer);
			Document doc = new Document(pdf,new PageSize(1020, 324)); //Tamaño doble
			Image templateLicencia = new Image(ImageDataFactory.create(_TEMPLATE_LICENCIA_DOBLE));
			templateLicencia.setFixedPosition(10, 10); //Posicion template doble
			doc.add(templateLicencia);
			fontInfo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			doc.setFont(fontInfo);
			doc.setFontSize(8);
			doc.add(new ListItem("5. N° Licencia                                                                      9. Clase"
					+"                                                                           Observaciones").setMarginLeft(150).setMarginTop(30)).setFontSize(14);
			doc.add(new ListItem("123456789                                   "+dto.getLicenseType()
					+ "                                        "+dto.getObservations()).setMarginLeft(150)).setFontSize(8);
			doc.add(new ListItem("1. Apellido"
					+ "                                                                                                       "
					+"                                                             Donante").setMarginLeft(150)).setFontSize(14);
			doc.add(new ListItem("BRAIDOT"
					+ "                                                                   "
					+"                     "+dto.getOrganDonnor().toString()).setMarginLeft(150)).setFontSize(8);
			doc.add(new ListItem("2. Nombre"
					+"                                                                                                        "
					+"                                                             Grupo y factor").setMarginLeft(150)).setFontSize(14);
			doc.add(new ListItem("ALAN NAHUEL"
					+"                                                                    "
					+"           A+").setMarginLeft(150)).setFontSize(8);
			doc.add(new ListItem("8. Domicilio").setMarginLeft(150)).setFontSize(14);
			doc.add(new ListItem("CALLE 316 N°240").setMarginLeft(150)).setFontSize(8);
			doc.add(new ListItem("3. Fecha nac.").setMarginLeft(150)).setFontSize(14);
			doc.add(new ListItem("17/04/1998").setMarginLeft(150)).setFontSize(8);
			doc.add(new ListItem("4a. Otorgamiento                                                          4b. Vencimiento").setMarginLeft(150)).setFontSize(14);
			doc.add(new ListItem("14/06/2018                                17/04/2020").setMarginLeft(150)).setFontSize(8);
			//doc.add(getInfo());
			doc.close();
		}catch(IOException e) {}
	}
	
	/*private List getLabels() throws java.io.IOException {
		List datos = new List().setSymbolIndent(0).setListSymbol("");
		datos.add(new ListItem("5. N° Licencia                                                                      9. Clase"))
			 .add(new ListItem(""+'\n'))
			 .add(new ListItem("1. Apellido"))
			 .add(new ListItem(""+'\n'))
			 .add(new ListItem("2. Nombre"))
			 .add(new ListItem(""+'\n'))
			 .add(new ListItem("8. Domicilio"))
			 .add(new ListItem(""+'\n'))
			 .add(new ListItem("3. Fecha nac."))
			 .add(new ListItem(""+'\n'))
			 .add(new ListItem("4a. Otorgamiento                                                          4b. Vencimiento"));
		fontInfo = PdfFontFactory.createFont(FontConstants.HELVETICA);
		datos.setFont(fontInfo);
		datos.setFontSize(9);
		datos.setMargins(50, 0, 0, 150);
		return datos;
	}*/
	
	/*private List getLabels() throws java.io.IOException {
		List datos = new List().setSymbolIndent(0).setListSymbol("");
		doc.add(new ListItem("5. N° Licencia                                                                      9. Clase")).setFontSize(8);
		doc.add(new ListItem("123456789                                                                      A1, B1")).setFontSize(15);
		doc.add(new ListItem("1. Apellido")).setFontSize(8);
		doc.add(new ListItem("BRAIDOT")).setFontSize(15);
		doc.add(new ListItem("2. Nombre")).setFontSize(8);
		doc.add(new ListItem("ALAN NAHUEL")).setFontSize(15);
		doc.add(new ListItem("8. Domicilio")).setFontSize(8);
		doc.add(new ListItem("CALLE 316 N°240")).setFontSize(15);
		doc.add(new ListItem("3. Fecha nac.")).setFontSize(8);
		doc.add(new ListItem("17/04/1998")).setFontSize(15);
		doc.add(new ListItem("4a. Otorgamiento                                                          4b. Vencimiento")).setFontSize(8);
		doc.add(new ListItem("15/06/2018                                                          17/04/2020")).setFontSize(15);
		fontInfo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		datos.setFont(fontInfo);
		datos.setMargins(50, 0, 0, 150);
		return datos;
	}*/
	
	private List getInfo() throws java.io.IOException {
		List datos = new List().setSymbolIndent(0).setListSymbol("");
		datos.add(new ListItem("123456789                                                                      A1, B1"))
			 .add(new ListItem("BRAIDOT"))
			 .add(new ListItem("ALAN NAHUEL"))
			 .add(new ListItem("CALLE 316 N°240"))
			 .add(new ListItem("17/04/1998"))
			 .add(new ListItem("15/06/2018                                                          17/04/2020"));
		fontInfo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		datos.setFont(fontInfo);
		datos.setFontSize(15);
		datos.setMargins(75, 0, 0, 150);
		return datos;
	}
<<<<<<< HEAD

	/*public GeneratePDF(PdfDTO dto, String destino) throws IOException, java.io.IOException{
		try {
			PdfWriter writer = new PdfWriter(destino);
			PdfDocument pdf = new PdfDocument(writer);
			//Document doc = new Document(pdf,new PageSize(499, 306)); //Tamaño solo frente
			Document doc = new Document(pdf,new PageSize(1066, 324)); //Tamaño frente + dorso
			Image templateLicencia = new Image(ImageDataFactory.create(_TEMPLATE_LICENCIA));
			templateLicencia.setFixedPosition(14, 4);
			doc.add(templateLicencia);
			doc.add(getInfo(dto));
			doc.close();
		}catch(IOException e) {}
	}
	
	private List getInfo(PdfDTO dto) throws java.io.IOException {
		List datos = new List().setSymbolIndent(0).setListSymbol("");
		datos.add(new ListItem("Licencia N°:	"+dto.getLicenseId()+"		Clase: "+dto.getLicenseType().toString()))
			.add(new ListItem("Apellido:	"+dto.getSurname()))
			.add(new ListItem("Nombre:		"+dto.getName()))
			.add(new ListItem("Domicilio:	"+dto.getAdress()))
			.add(new ListItem("Fecha nac.:	"+dto.getBirthday()))
			.add(new ListItem("Fecha ven.:	"+dto.getExpiricyDate()))
		fontInfo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		datos.setFont(fontInfo);
		datos.setMargins(50, 0, 0, 150);
		return datos;
	}*/
}
=======
}*/
>>>>>>> 5d2ddb0a2a1ca1e03f8606fcf182332a49d463d0
