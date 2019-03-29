package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private EditText leftEditText = null;
    private EditText centerEditText = null;
    private EditText rightEditText = null;

    private CheckBox leftCheckBox = null;
    private CheckBox centerCheckBox = null;
    private CheckBox rightCheckBox = null;
    private Random random = new Random();
    private int scor = 0;

    private Button playButton = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.play_button:
                    int nr1 = random.nextInt(4);
                    int nr2 = random.nextInt(4);
                    int nr3 = random.nextInt(4);
                    int gain = 0;
                    int nrBife = 3;
                    if(!leftCheckBox.isChecked()){
                        leftEditText.setText(String.valueOf(nr1));
                        nrBife--;
                    }
                    if(!centerCheckBox.isChecked()){
                        centerEditText.setText(String.valueOf(nr2));
                        nrBife--;
                    }
                    if(!rightCheckBox.isChecked()){
                        rightEditText.setText(String.valueOf(nr3));
                        nrBife--;
                    }

                    if (nrBife == 0) {
                        gain = 100;
                    } else if (nrBife == 1) {
                        gain = 50;
                    } else if (nrBife == 2) {
                        gain = 10;
                    }

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                    intent.putExtra("gain", gain);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        leftEditText = (EditText) findViewById(R.id.left_edit_text);
        //leftEditText.setText(0);
        centerEditText = (EditText) findViewById(R.id.center_edit_text);
        //centerEditText.setText(0);
        rightEditText = (EditText)findViewById(R.id.right_edit_text);
        //rightEditText.setText(0);

        leftCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        centerCheckBox = (CheckBox) findViewById(R.id.checkBox2);
        rightCheckBox = (CheckBox) findViewById(R.id.checkBox3);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("left")) {
                leftEditText.setText(savedInstanceState.getString("left"));
            }
            if (savedInstanceState.containsKey("right")) {
                rightEditText.setText(savedInstanceState.getString("right"));
            }
            if (savedInstanceState.containsKey("center")) {
                rightEditText.setText(savedInstanceState.getString("center"));
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            scor += resultCode;
        }
        Toast.makeText(this, "Scor final " + scor, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("left", leftEditText.getText().toString());
        savedInstanceState.putString("right", rightEditText.getText().toString());
        savedInstanceState.putString("center", centerEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("left")) {
            leftEditText.setText(savedInstanceState.getString("leftCount"));
        }
        if (savedInstanceState.containsKey("right")) {
            rightEditText.setText(savedInstanceState.getString("rightCount"));
        }
        if (savedInstanceState.containsKey("center")) {
            rightEditText.setText(savedInstanceState.getString("centerCount"));
        }
    }
}
