package com.quizapp.sinan.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quizapp.sinan.quizapplication.Model.Person;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login,register;
    DbHandler dbHandler;
    public static Person current_user = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHandler = new DbHandler(this,null,null,1);


        username = (EditText) findViewById(R.id.username_edttxt);
        password = (EditText) findViewById(R.id.pass_edttxt);

        login = (Button) findViewById(R.id.login_btn);
        register = (Button) findViewById(R.id.reg_btn);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dbHandler.checkCredits(username.getText().toString(), password.getText().toString()) != null){
                    current_user = dbHandler.checkCredits(username.getText().toString(), password.getText().toString());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this,"Wrong Credits",Toast.LENGTH_SHORT).show();
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

    }
}
