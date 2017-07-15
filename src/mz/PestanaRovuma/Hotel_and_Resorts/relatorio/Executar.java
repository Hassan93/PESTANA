package mz.PestanaRovuma.Hotel_and_Resorts.relatorio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Silvestre
 */
public class Executar {
    
         public void imprirNotas(List<ReservaRelatorio> l)throws JRException{
         
       JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(l);
        JasperReport jasperReport = JasperCompileManager.compileReport("/home/hassan/Projecto_Eclipse/SISPESTANA/src/mz/PestanaRovuma/Hotel_and_Resorts/relatorio/Reserva.jrxml");
        JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/hassan/Projecto_Eclipse/SISPESTANA/relatorios/Gerado.pdf"); 
   }
    
}