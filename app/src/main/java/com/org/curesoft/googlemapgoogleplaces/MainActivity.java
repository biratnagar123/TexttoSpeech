package com.org.curesoft.googlemapgoogleplaces;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
    Button test;
    EditText text;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test= (Button) findViewById(R.id.buttonTest);
        text= (EditText) findViewById(R.id.editText);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
             if(status==TextToSpeech.SUCCESS){
                 int result=textToSpeech.setLanguage(Locale.ENGLISH);
                 if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                     Toast.makeText(MainActivity.this,"this language is not supported",Toast.LENGTH_LONG).show();
                 }
                 else
                 {
                     test.setEnabled(true);
                     textToSpeech.setPitch(0.3f);
                     textToSpeech.setSpeechRate(1.0f);
                     speak();
                 }
             }
            }
        });
    }

    private void speak() {
        String words=text.getText().toString();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            textToSpeech.speak(words,TextToSpeech.QUEUE_FLUSH,null,null);
        else
            textToSpeech.speak(words,TextToSpeech.QUEUE_FLUSH,null);


    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();

        }
        super.onDestroy();
    }
}
