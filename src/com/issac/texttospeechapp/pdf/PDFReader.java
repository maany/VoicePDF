package com.issac.texttospeechapp.pdf;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import android.content.Context;
import android.util.Log;
public class PDFReader {
	private Context context;
	private static final String FOLDER_NAME = "IssacTextToSpeech";
	private PDFTextStripper stripper;
    private PDDocument document = null;
    public PDFReader(Context context){
        this.context = context;
    	try {
            stripper = new PDFTextStripper();
            
        } catch (IOException ex) {
            Log.e(this.getClass().getName(),"ERROR IN PDFReader constructor. " + ex.getMessage());
        }
    }
    public PrintWriter setPDF(String path,int start,int end) throws IOException{
        if(document==null)
            document = new PDDocument();
        document = PDDocument.load(path,true);
        File file =new File(path);
       // String parent = file.getParent();
        PrintWriter out = new PrintWriter(new File(context.getCacheDir().toString() + file.getName()+ ".txt"));
        
        stripper.setStartPage(start);
        stripper.setEndPage(end);
        stripper.writeText(document, out); 
        out.flush();
        //out.close();
        document.close();
        return out;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //PDFReader reader = new PDFReader();
        String source = "G:\\PDF32000_2008.pdf";
      
    }
}
