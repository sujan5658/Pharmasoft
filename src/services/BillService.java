package services;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import beans.Bill;
import beans.Message;
import beans.PharmacyInfo;

public class BillService {
	@Autowired
	private Bill bill;
	@Autowired
	private Message message;
	@Autowired
	private PharmacyInfo pinfo;
	private SessionFactory factory;
	private Session session;
	private HttpServletRequest request;
	private String filePath;

	private long getBillNo() {
		String HQL;
		Query query;
		long billno;
		try {
			this.session.beginTransaction();
			HQL = "FROM Bill WHERE billNo=?";
			query = this.session.createQuery(HQL);
			query.setLong(0, 0);
			Bill bil = new Bill();
			bil = (Bill) query.uniqueResult();
			if (bil == null) {
				billno = 1;
			} else {
				HQL = "SELECT billNo FROM Bill ORDER BY id DESC LIMIT 1";
				query = this.session.createQuery(HQL);
				query.setMaxResults(1);
				billno = (long) query.uniqueResult();
				if (billno == 999999999) {
					billno = 1;
				} else {
					billno += 1;
				}
			}
			this.session.getTransaction().commit();
			return billno;
		} catch (Exception er) {
			System.out.println("Error : " + er);
			return 0;
		}
	}

	public BillService() {
		this.message = new Message();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.bill = new Bill();
		this.bill.setBilledDate(formatter.format(date));

		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Bill.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
		this.bill.setBillNo(this.getBillNo());
	}

	public BillService(Bill bill) {
		this.bill = bill;
		this.message = new Message();
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Bill.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}

	public Bill getBill() {
		return bill;
	}

	private void pdfHandler() {
		try {
			HttpSession session = this.request.getSession();
			ServletContext context = session.getServletContext();
			String path = context.getRealPath("uploads");
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdir();
			}
			int num = (int) (Math.random() * 999999 + 1);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String uniqueKey = formatter.format(new Date()) + num + "_";
			String fileName = uniqueKey + "bill" + this.getBillNo() + ".pdf";
			this.bill.setFileName(fileName);
			File file = new File(path + File.separator + fileName);
			this.filePath = path + File.separator + fileName;
			file.createNewFile();

			Document document = new Document(PageSize.A5, 15f, 15f, 15f, 15f);
			FileOutputStream fout = new FileOutputStream(file);
			PdfWriter.getInstance(document, fout);
			document.open();
			Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
			Font font3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

			this.pinfo = new PharmacyInfoService(this.pinfo).getPharmacyInfo();
			Paragraph pharmacyName = new Paragraph(this.pinfo.getPharmacyName(), font1);
			Paragraph billNo = new Paragraph("Bill No : " + this.bill.getBillNo(), font2);
			Paragraph pharmacyAddress = new Paragraph(this.pinfo.getPharmacyAddress(), font2);
			Paragraph registeredDate = new Paragraph("Registered Date : " + this.pinfo.getRegisteredDate(), font2);
			Paragraph telephone = new Paragraph("Telephone : " + this.pinfo.getTelephone(), font2);
			Paragraph panNo = new Paragraph("Pan No : " + this.pinfo.getPanNo(), font2);
			Paragraph billingDate = new Paragraph("Billed Date : " + this.bill.getBilledDate(), font2);

			Paragraph doctorName = new Paragraph(this.bill.getDoctorName(), font3);
			Paragraph patientName = new Paragraph(this.bill.getPatientName(), font3);
			Paragraph patientAddress = new Paragraph(this.bill.getPatientAddress(), font3);
			Paragraph gender;
			if (this.bill.getGender() == 'M') {
				gender = new Paragraph("Male", font3);
			} else {
				gender = new Paragraph("Female", font3);
			}
			Paragraph contactNo = new Paragraph(Long.toString(this.bill.getContactNo()), font3);
			Paragraph discountGiven = new Paragraph("Discount : " + this.bill.getDiscount() + " Rs", font3);
			Paragraph total = new Paragraph("Grand Total : " + this.bill.getGrandTotal() + " Rs", font3);
			Paragraph soldBY = new Paragraph("Sold By : " + this.bill.getSeller(), font3);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);

			PdfPCell cell1 = new PdfPCell(pharmacyName);
			cell1.setColspan(6);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setPadding(5);
			cell1.disableBorderSide(2);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell2 = new PdfPCell(pharmacyAddress);
			cell2.setColspan(6);
			cell2.disableBorderSide(1);
			cell2.setPadding(5);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell3 = new PdfPCell(registeredDate);
			cell3.setColspan(4);
			cell3.setPadding(3);
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell3.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell4 = new PdfPCell(panNo);
			cell4.setPadding(3);
			cell4.setColspan(4);
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell5 = new PdfPCell(telephone);
			cell5.setPadding(3);
			cell5.setColspan(2);
			cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell5.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell6 = new PdfPCell(billNo);
			cell6.setPadding(3);
			cell6.setPaddingLeft(10);
			cell6.setColspan(2);
			cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell6.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell7 = new PdfPCell(billingDate);
			cell7.setPadding(3);
			cell7.setColspan(2);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell8 = new PdfPCell(new Paragraph("Doctor's Name : ", font2));
			cell8.setPadding(5);
			cell8.setColspan(3);
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell8.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell9 = new PdfPCell(doctorName);
			cell9.setPadding(5);
			cell9.setColspan(3);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell10 = new PdfPCell(new Paragraph("Patient's Name : ", font2));
			cell10.setPadding(5);
			cell10.setColspan(3);
			cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell10.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell11 = new PdfPCell(patientName);
			cell11.setPadding(5);
			cell11.setColspan(3);
			cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell11.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell12 = new PdfPCell(new Paragraph("Patient's Address : ", font2));
			cell12.setPadding(5);
			cell12.setColspan(3);
			cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell12.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell13 = new PdfPCell(patientAddress);
			cell13.setPadding(5);
			cell13.setColspan(3);
			cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell13.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell14 = new PdfPCell(new Paragraph("Gender : ", font2));
			cell14.setPadding(5);
			cell14.setColspan(3);
			cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell14.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell15 = new PdfPCell(gender);
			cell15.setPadding(5);
			cell15.setColspan(3);
			cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell15.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell16 = new PdfPCell(new Paragraph("Contact Number : ", font2));
			cell16.setPadding(5);
			cell16.setColspan(3);
			cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell16.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell17 = new PdfPCell(contactNo);
			cell17.setPadding(5);
			cell17.setColspan(3);
			cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell17.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell18 = new PdfPCell(new Paragraph("SN", font2));
			cell18.setPadding(5);
			cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell18.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell19 = new PdfPCell(new Paragraph("Medicine", font2));
			cell19.setPadding(5);
			cell19.setColspan(2);
			cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell19.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell20 = new PdfPCell(new Paragraph("Quantity", font2));
			cell20.setPadding(5);
			cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell20.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell21 = new PdfPCell(new Paragraph("Rate (RS)", font2));
			cell21.setPadding(5);
			cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell21.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell22 = new PdfPCell(new Paragraph("Total (RS)", font2));
			cell22.setPadding(5);
			cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell22.setVerticalAlignment(Element.ALIGN_CENTER);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			table.addCell(cell11);
			table.addCell(cell12);
			table.addCell(cell13);
			table.addCell(cell14);
			table.addCell(cell15);
			table.addCell(cell16);
			table.addCell(cell17);
			table.addCell(cell18);
			table.addCell(cell19);
			table.addCell(cell20);
			table.addCell(cell21);
			table.addCell(cell22);

			String medicines[] = this.bill.getMedicines().split(",");
			String quantities[] = this.bill.getQuantities().split(",");
			String rates[] = this.bill.getRates().split(",");
			String totals[] = this.bill.getTotals().split(",");

			PdfPCell cell;
			for (int i = 0; i < medicines.length; i++) {
				cell = new PdfPCell(new Paragraph(Integer.toString(i + 1), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(medicines[i], font3));
				cell.setPadding(5);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(quantities[i], font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(rates[i], font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(totals[i], font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}

			PdfPCell discount = new PdfPCell(discountGiven);
			discount.setPadding(7);
			discount.setColspan(3);
			discount.setHorizontalAlignment(Element.ALIGN_LEFT);
			discount.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell grandTotal = new PdfPCell(total);
			grandTotal.setPadding(7);
			grandTotal.setColspan(3);
			grandTotal.setHorizontalAlignment(Element.ALIGN_LEFT);
			grandTotal.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell soldBy = new PdfPCell(soldBY);
			soldBy.setPadding(20);
			soldBy.setColspan(6);
			soldBy.setHorizontalAlignment(Element.ALIGN_LEFT);
			soldBy.setVerticalAlignment(Element.ALIGN_CENTER);

			table.addCell(discount);
			table.addCell(grandTotal);
			table.addCell(soldBy);
			document.add(table);
			document.close();

			File readFile = new File(path + File.separator + fileName);
			FileInputStream filein = new FileInputStream(readFile);
			byte[] data = new byte[(int) readFile.length()];
			filein.read(data);
			this.bill.setFileData(data);
		} catch (Exception er) {
			System.out.println("Error PDF : " + er.getMessage());
		}
	}

	public Message storeBill(HttpServletRequest request) {
		this.request = request;
		try {
			this.pdfHandler();
			this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Bill.class)
					.buildSessionFactory();
			this.session = this.factory.getCurrentSession();
			this.session.beginTransaction();
			this.session.save(this.bill);
			this.session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Bill Saved.!!");
			this.PdfViewer();
		} catch (Exception er) {
			System.out.println("Save Failed : " + er.getMessage());
			this.message.setStatus(true);
			this.message.setMessage("Internal Error ..!!!");
		}
		return this.message;
	}

	private void PdfViewer() {
		JFrame frame;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight());
		int width = (int) (screenSize.getWidth());
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setTitle("PDF View");
		frame.setLocationRelativeTo(null);

		SwingController controller = new SwingController();

		SwingViewBuilder factory = new SwingViewBuilder(controller);

		JPanel viewerComponentPanel = factory.buildViewerPanel();

		controller.getDocumentViewController().setAnnotationCallback(
				new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
		frame.getContentPane().add(viewerComponentPanel);
		frame.add(viewerComponentPanel);

		controller.openDocument(this.filePath);

		frame.pack();
		frame.setVisible(true);
	}

	public List<Bill> getBills() {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			this.session.beginTransaction();
			bills = this.session.createQuery("FROM Bill ORDER BY id DESC").list();
			bills.remove(bills.size() - 1);
			this.session.getTransaction().commit();
		} catch (Exception er) {
			bills = null;
		}
		return bills;
	}

	public void viewBill(HttpServletRequest request, String bill) {
		ServletContext context = request.getServletContext();
		this.filePath = context.getRealPath("uploads" + File.separator + bill);
		this.PdfViewer();
	}
}
