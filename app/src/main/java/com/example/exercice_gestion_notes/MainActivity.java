package com.example.exercice_gestion_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    Button btn;
    EditText note;
    TextView moyenne_view;
    ListView listView;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<Float> notes = new ArrayList<>();
    ArrayAdapter<String> adapter;


    public boolean verifyElements(ArrayList<String> list, String txt) {
        for (String s : list) {
            if (s.contains(txt))
                return true;
        }
        return false;
    }

    private float calculateAverage(ArrayList<Float> marks) {
        float sum = 0;
        float res=0;
        for (Float a : marks) {
            sum += a;
        }
        res = sum / marks.size();
        return res;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.btn);
        note = findViewById(R.id.note_text);
        moyenne_view = findViewById(R.id.moyenne);
        listView = findViewById(R.id.bulletin_show);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNote = note.getText().toString();
                String getSpinner_text = spinner.getSelectedItem().toString();
//                Context context = getApplicationContext();

//                Toast.makeText(getBaseContext(), String.valueOf(getSpinner_text) , Toast.LENGTH_LONG).show();

                if (getNote == null || getNote.trim().equals("") || getNote.length() == 0){
                    Toast.makeText(getBaseContext(),"Please Fill the mark Field", Toast.LENGTH_LONG).show();
                }
                else if(verifyElements(items,getSpinner_text)){
                    Toast.makeText(getBaseContext(),"Module mark already Added", Toast.LENGTH_LONG).show();
                }
                else {
                    String text;
                    float res;
                    float mark=0;

                    text = getSpinner_text+"        "+getNote;
//                    Toast.makeText(getBaseContext(), String.valueOf(mark), Toast.LENGTH_SHORT).show();
                    mark = Float.parseFloat(getNote);

                    items.add(text);
                    notes.add(mark);
                    res=calculateAverage(notes);
//                    Toast.makeText(getBaseContext(), String.valueOf(notes), Toast.LENGTH_SHORT).show();

                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);

                    listView.setAdapter(adapter);
                    moyenne_view.setText(String.valueOf(res));
                    note.setText(null);
                }


            }
        });




    }
}