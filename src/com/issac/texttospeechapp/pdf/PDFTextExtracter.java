package com.issac.texttospeechapp.pdf;

import android.util.Log;

import com.issac.texttospeechapp.MainActivity;
import com.qoppa.android.pdf.PDFException;
import com.qoppa.android.pdfProcess.*;
import com.qoppa.android.pdfViewer.fonts.StandardFontTF;

public class PDFTextExtracter {

	MainActivity mainActivity;
	public PDFTextExtracter(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	
	public String loadTextFromPDF(String path,int start,int end) {
		StringBuilder builder = new StringBuilder();
		try {
			StandardFontTF.mAssetMgr = mainActivity.getAssets();
			PDFDocument pdf = new PDFDocument(path,null);
			PDFPage page;
			for(int i=start;i<=end;i++) {
				page = pdf.getPage(i);
				builder.append(page.getAllText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("error", Log.getStackTraceString(e));
		}
		return builder.toString();
	}
}
