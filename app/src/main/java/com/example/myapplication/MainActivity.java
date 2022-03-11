package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText editTextButton;
    EditText editTextFile;
    EditText editTextName;
    Context context = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        text.setText("Press button to\nupdate this text!");
        editTextButton = (EditText) findViewById(R.id.editTextTextPersonName2);
        context = MainActivity.this;
        editTextName = (EditText)  findViewById((R.id.editTextTextPersonName3));
        editTextFile = (EditText)  findViewById((R.id.editTextTextPersonName4));
        // kommenttien väliin jäävä koodi muokattu alemmassa kommentissa olevasta linkistä tähän ohjelmaan sopivaksi
        final EditText edittext = (EditText) findViewById(R.id.editTextTextPersonName);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(MainActivity.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    text.setText(edittext.getText());
                    return true;
                }
                return false;
            }
        });
        // https://stackoverflow.com/questions/1489852/android-handle-enter-in-an-edittext/6832095#6832095

        System.out.println("Directory: " + context.getFilesDir());
    }

    public void buttonPrint (View v) {
        text.setText(editTextButton.getText());
    }


    public void printWorld (View v) {
        System.out.println("Hello world!");
        text.setText("Hello world!");
    }

    public void loadFile (View v) {
        try {
            //String name = "teksti.txt";

            InputStream ins = context.openFileInput(String.valueOf(editTextName.getText()));
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            String s = "";
            editTextFile.getText().clear();
            while((s = br.readLine()) != null) {
                //System.out.println(s);
                editTextFile.append(s);
            }

            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("READ");
        }
    }

    public void saveFile (View v) {
        try {
            //String name = "teksti.txt";
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(String.valueOf(editTextName.getText()), context.MODE_PRIVATE));


            osw.write(String.valueOf(editTextFile.getText()));
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("WRITE");
        }
    }
}