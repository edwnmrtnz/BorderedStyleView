package com.github.edwnmrtz.borderedstyleview.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.github.edwnmrtz.borderedstyleedittext.NormalBorderedStyleEditText;
import com.github.edwnmrtz.borderedstyleedittext.PasswordBorderedStyleEditText;

public class MainActivity extends AppCompatActivity {

    private NormalBorderedStyleEditText etFirstName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         etFirstName = findViewById(R.id.etFirstName);

        findViewById(R.id.btnSetError).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFirstName.setError("Invalid first name");
            }
        });



    }
}
