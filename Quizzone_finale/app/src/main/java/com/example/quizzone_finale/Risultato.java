package com.example.quizzone_finale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Risultato extends AppCompatActivity {

    String ricevuto_finale="";
    Integer highscore= 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato);
        TextView texView12= (TextView) findViewById(R.id.textView12);
        TextView punteggio_finale= (TextView) findViewById(R.id.punteggio_finale);
        Button fine= (Button) findViewById(R.id.button_finale);
        final Integer ricevuto= getIntent().getExtras().getInt("extraScore");
        String ricevuto_finale="Il tuo punteggio finale è: " + ricevuto.toString();
        punteggio_finale.setText(ricevuto_finale);

        if((ricevuto < 3)){
                texView12.setText("Peccato, non ti seghi abbastanza :D ");
        }else if((ricevuto < 7)  && (ricevuto > 3)){
                texView12.setText("Non male, comunque non sei un rullo compressore");
        }else if(ricevuto == highscore){
            texView12.setText("Sono emozionato!! Qualcuno che non ha un cazzo da fare tutto il giorno ahaha");
        }else{
            texView12.setText("Impossibile che tu abbia un punteggio cosi alto");
        }

        fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ritorno= new Intent(getApplicationContext(),MainActivity.class);
                ritorno.putExtra("score_finale",ricevuto);
                startActivity(ritorno);
            }
        });

    }
}
