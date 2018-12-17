package com.github.edwnmrtz.borderedstyleview.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.github.edwnmrtz.borderedstyleedittext.NormalBorderedStyleEditText;
import com.github.edwnmrtz.borderedstyleedittext.PasswordBorderedStyleEditText;

public class MainActivity extends AppCompatActivity {

    private NormalBorderedStyleEditText etMobileNumber;
    private PasswordBorderedStyleEditText etPassword;
    private AppCompatButton btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMobileNumber = findViewById(R.id.etMobileNumber);
        etPassword      = findViewById(R.id.etPassword);
        btnLogin    =  findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNumber = etMobileNumber.getText();
                String password     = etPassword.getText();

                if(mobileNumber.isEmpty()) {
                    etMobileNumber.setError("Cannot be empty");
                }

                if(password.isEmpty()) {
                    etPassword.setError("Cannot be empty");
                }
            }
        });




    }
}
