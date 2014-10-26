package com.issac.texttospeechapp.pdf;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
public class PDFReader {
	private PDFTextStripper stripper;
    private PDDocument document = null;
    public PDFReader(){
        try {
            stripper = new PDFTextStripper();
            
        } catch (IOException ex) {
            Logger.getLogger(PDFReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setPDF(String path) throws IOException{
        if(document==null)
            document = new PDDocument();
        document = PDDocument.load(path,true);
        PrintWriter out = new PrintWriter(new File("G:\\out.txt"));
        
        stripper.setStartPage(1);
        stripper.setEndPage(2);
        stripper.writeText(document, out);
        out.flush();
        out.close();
        document.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PDFReader reader = new PDFReader();
        String source = "G:\\PDF32000_2008.pdf";
        try {
            
            reader.setPDF(source);
            System.out.println("SUCCESS !!!");
        } catch (IOException ex) {
            Logger.getLogger(PDFReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
}
