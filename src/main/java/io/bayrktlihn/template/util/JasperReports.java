package io.bayrktlihn.template.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReports {

	public static byte[] createPdf(InputStream inputStream, Map<String, Object> params) {
		if (params == null) {
			params = new HashMap<>();
		}
		return createPdf(inputStream, params, new JREmptyDataSource());
	}

	public static byte[] createPdf(InputStream inputStream, Map<String, Object> params, JRDataSource jrDataSource) {
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrDataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
