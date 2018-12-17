package com.github.edwnmrtz.borderedstyleview.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.github.edwnmrtz.borderedstyleedittext.NormalBorderedStyleEditText;

public class MainActivity extends AppCompatActivity {

    private NormalBorderedStyleEditText etFirstName;
    private AppCompatButton btnSetError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        btnSetError = findViewById(R.id.btn_set_error);

        btnSetError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFirstName.setError("Required!");
            }
        });
    }
}
