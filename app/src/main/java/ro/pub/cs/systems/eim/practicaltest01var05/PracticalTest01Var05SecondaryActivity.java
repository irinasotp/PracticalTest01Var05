package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {
    private EditText gainText = null;
    private Button okButton = null;
    private int gain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        gainText = (EditText)findViewById(R.id.center_edit_text);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("gain")) {
            int gainLocal = intent.getIntExtra("gain", -1);
            gainText.setText(String.valueOf(gainLocal));
            Toast.makeText(this, "Transmit gain " + gainLocal, Toast.LENGTH_LONG).show();
            setResult(gainLocal, null);
        }

        okButton = (Button) findViewById(R.id.ok_button);
    }
}
