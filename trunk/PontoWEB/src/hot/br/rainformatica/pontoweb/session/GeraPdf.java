package br.rainformatica.pontoweb.session;

import java.sql.SQLException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.rainformatica.pontoweb.entity.TbHorasColab;

public class GeraPdf {

	public void gerar(List<TbHorasColab> tbHorasColab)throws JRException, SQLException{
		
		
		
		// compilacao do JRXML 
		JasperReport report = JasperCompileManager .compileReport("PDF/RelatorioClientes.jrxml");
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(tbHorasColab));
		// exportacao do relatorio para outro formato, no caso PDF 
		JasperExportManager.exportReportToPdfFile(print, "PDF/RelatorioClientes.pdf"); 
	}
	
}
