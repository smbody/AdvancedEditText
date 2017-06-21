package ru.itsln.smilesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.itsln.advancededittext.AdvancedEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButton(R.id.addsmile).setOnClickListener(getAddSmileClick());
        getButton(R.id.addsadsmile).setOnClickListener(getAddSadSmileClick());
        getButton(R.id.addFunction).setOnClickListener(getAddFunctionClick());
        getAdvancedEditText().setSpans(new SmilesSpanFactory(this));
    }

    public Button getButton(int resource) {
        return  (Button)findViewById(resource);
    }

    public AdvancedEditText getAdvancedEditText() {
        return (AdvancedEditText) findViewById(R.id.advancedEditText);
    }


    public View.OnClickListener getAddSmileClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdvancedEditText().insert(":-)");
            }
        };
    }


    public View.OnClickListener getAddSadSmileClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdvancedEditText().insert(":-(");
            }
        };
    }

    public View.OnClickListener getAddFunctionClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdvancedEditText().insert(":clickable:");
            }
        };
    }
}
