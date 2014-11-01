
package com.issac.texttospeechapp.speech;

import java.util.Locale;

import com.issac.texttospeechapp.MainActivity;

import android.speech.tts.TextToSpeech;

public class Speaker {

	private TextToSpeech tts;
	public TextToSpeech getTts() {
		return tts;
	}
	public void setTts(TextToSpeech tts) {
		this.tts = tts;
	}
	MainActivity mainActivity;
	public Speaker(MainActivity mainActiity){
		this.mainActivity = mainActiity;
		tts = new TextToSpeech(mainActiity, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				tts.setLanguage(Locale.US);
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.UK);
				}

			}
		});
	
	}
	
	public void speakFresh(String text) {
		if(tts!=null)
			tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
	}
	public void speakAppend(String text){
		if(tts!=null)
			tts.speak(text,TextToSpeech.QUEUE_ADD,null);
	}
	public void stop(){
		if(tts==null)
			return;
		if(tts.isSpeaking())
			tts.stop();
	}
}

