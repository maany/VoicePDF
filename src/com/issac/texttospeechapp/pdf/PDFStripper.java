package com.issac.texttospeechapp.pdf;

import com.issac.texttospeechapp.MainActivity;
import com.qoppa.android.pdf.PDFException;
import com.qoppa.android.pdfProcess.PDFDocument;
import com.qoppa.android.pdfViewer.fonts.StandardFontTF;

public class PDFStripper {

	MainActivity context = null;
	public PDFStripper(MainActivity context){
		this.context = context;
	}
	public StringBuilder stripText(String path,int start,int end ) throws PDFException{
		StringBuilder builder = new StringBuilder();
		StandardFontTF.mAssetMgr = context.getAssets();
		PDFDocument doc = new PDFDocument(path,null);
		for(int i=start;i<=end;i++) {
			builder.append("Page 1\n");
			builder.append(doc.getPage(0).getAllText());
		}
		return builder;
	}
}
