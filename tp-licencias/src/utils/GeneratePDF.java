package utils;

import java.text.SimpleDateFormat;
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
import com.itextpdf.layout.element.ListItem;

import domain.LicenseType;
import dto.LicenseDTO;
import dto.TitularDTO;

@SuppressWarnings("deprecation")
public class GeneratePDF{
	
	private static String _TEMPLATE_LICENCIA_DOBLE = System.getProperty("user.home") + "\\eclipse-workspace\\tp-metodos-agiles\\tp-licencias\\src\\res\\images\\template_licencia_frente_dorso.jpg";
	private static String _FOTO_LICENCIA = System.getProperty("user.home") + "\\eclipse-workspace\\tp-metodos-agiles\\tp-licencias\\src\\res\\images\\bill-gates-licencia.jpg";
	private static String _FIRMA_LICENCIA = System.getProperty("user.home") + "\\eclipse-workspace\\tp-metodos-agiles\\tp-licencias\\src\\res\\images\\firma-bill-gates.png";
	
	//TODO borrar main
	/*public static void main(String args[]) throws IOException, java.io.IOException {
		String destino = "C:\\Users\\alan_\\Desktop\\licencia.pdf";
		TitularDTO titularDTO = new TitularDTO();
		titularDTO.setName("Alan Nahuel");
		titularDTO.setAdress("Calle 316 Nro 240");
		titularDTO.setOrganDonor(true);
		titularDTO.setBirthdate(new Date());
		titularDTO.setPersonalId("41057032");
		titularDTO.setSurname("Braidot");
		titularDTO.setBloodType("A+");
		LicenseDTO licenseDTO = new LicenseDTO();
		licenseDTO.setLicenseType(LicenseType.B);
		licenseDTO.setObservation("Necesita anteojos.");
		licenseDTO.setEmisionDate(new Date());
		licenseDTO.setExpiricyDate(new Date());
		GeneratePDF.generatePDF(titularDTO, licenseDTO, destino);
	}*/
	
	public static void generatePDF(TitularDTO titularDTO, LicenseDTO licenseDTO, String destino) throws IOException, java.io.IOException{
		try {
			PdfWriter writer = new PdfWriter(destino);
			PdfDocument pdf = new PdfDocument(writer);
			Document doc = new Document(pdf,new PageSize(1020, 324));
			Image templateLicencia = new Image(ImageDataFactory.create(_TEMPLATE_LICENCIA_DOBLE));
			templateLicencia.setFixedPosition(10, 10);
			doc.add(templateLicencia);
			Image fotoLicencia = new Image(ImageDataFactory.create(_FOTO_LICENCIA));
			fotoLicencia.setFixedPosition(29, 74);
			doc.add(fotoLicencia);
			Image firmaLicencia = new Image(ImageDataFactory.create(_FIRMA_LICENCIA));
			firmaLicencia.setFixedPosition(365, 118);
			doc.add(firmaLicencia);
			setLabels(doc);
			setData(doc,titularDTO,licenseDTO);
			doc.close();
		}catch(IOException e) {}
	}
	
	private static void setLabels(Document doc) throws java.io.IOException {
		PdfFont fontLabel = PdfFontFactory.createFont(FontConstants.HELVETICA);
		doc.setFont(fontLabel);
		doc.setFontSize(8);
		doc.add(new ListItem("5. N° Licencia / License N°").setMarginLeft(140).setMarginTop(35))
			.add(new ListItem("9. Clase / Class").setMarginLeft(375).setMarginTop(-12))
			.add(new ListItem("1. Apellido / Last name").setMarginLeft(140).setMarginTop(15))
			.add(new ListItem("2. Nombre / First name").setMarginLeft(140).setMarginTop(15))
			.add(new ListItem("8. Domicilio / Address").setMarginLeft(140).setMarginTop(15))
			.add(new ListItem("3. Fecha de nac. / Date of Birth").setMarginLeft(140).setMarginTop(28))
			.add(new ListItem("7. Firma del titular / Signature").setMarginLeft(326).setMarginTop(5))
			.add(new ListItem("4a. Otorgamiento / Date of Issue").setMarginLeft(140).setMarginTop(0))
			.add(new ListItem("4b. Vencimiento / Expires").setMarginLeft(340).setMarginTop(-12))
			.add(new ListItem("Donante / Donor:").setMarginLeft(600).setMarginTop(-80))
			.add(new ListItem("Grupo y factor / Blood type:").setMarginLeft(750).setMarginTop(-12))
			.add(new ListItem("Observaciones / Observations:").setMarginLeft(600).setMarginTop(5));
	}
	
	private static void setData(Document doc, TitularDTO titularDTO, LicenseDTO licenseDTO) throws java.io.IOException {
		PdfFont fontInfo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		doc.setFont(fontInfo);
		doc.setFontSize(15);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
		doc.add(new ListItem(titularDTO.getPersonalId()).setMarginLeft(140).setMarginTop(-102))
			.add(new ListItem(licenseDTO.getLicenseType().toString()).setMarginLeft(400).setMarginTop(-23))
			.add(new ListItem(titularDTO.getSurname().toUpperCase()).setMarginLeft(140).setMarginTop(4))
			.add(new ListItem(titularDTO.getName().toUpperCase()).setMarginLeft(140).setMarginTop(4))
			.add(new ListItem(titularDTO.getAdress().toUpperCase()).setMarginLeft(140).setMarginTop(4))
			.add(new ListItem(formatoFecha.format(titularDTO.getBirthdate()).toString().toUpperCase()).setMarginLeft(140).setMarginTop(18))
			.add(new ListItem(formatoFecha.format(licenseDTO.getEmisionDate()).toString().toUpperCase()).setMarginLeft(140).setMarginTop(7))
			.add(new ListItem(formatoFecha.format(licenseDTO.getExpiricyDate()).toString().toUpperCase()).setMarginLeft(340).setMarginTop(-23));
		doc.setFontSize(9);
		String donante = "NO";
		if(titularDTO.getOrganDonor() == true)
			donante="SI";
		doc.add(new ListItem(donante).setMarginLeft(665).setMarginTop(-98))
			.add(new ListItem(titularDTO.getBloodType().toUpperCase()).setMarginLeft(854).setMarginTop(-14))
			.add(new ListItem(licenseDTO.getObservation()).setMarginLeft(600).setMarginTop(15));
	}
}


