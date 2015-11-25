package com.quizapp.sinan.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quizapp.sinan.quizapplication.Model.Person;

public class RegistrationActivity extends AppCompatActivity {

    EditText username, password, name, surname, birthday;
    Button register;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHandler = new DbHandler(this, null, null, 1);

        username = (EditText) findViewById(R.id.reg_username_edtTxt);
        password = (EditText) findViewById(R.id.reg_pass_edtTxt);
        name = (EditText) findViewById(R.id.reg_name_edtTxt);
        surname = (EditText) findViewById(R.id.reg_surname_edtTxt);
        birthday = (EditText) findViewById(R.id.reg_birthday_edtTxt);
        register = (Button) findViewById(R.id.regMe_btn);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if( name.getText().toString().equals("") || surname.getText().toString().equals("") || username.getText().toString().equals("") ||
                        password.getText().toString().equals("") || birthday.getText().toString().equals("")
                        )
                {
                    Toast.makeText(RegistrationActivity.this, "Please fill out all blanks", Toast.LENGTH_LONG ).show();
                }
                else {
                    Person temp_person = new Person(name.getText().toString(), surname.getText().toString(),
                            username.getText().toString(), password.getText().toString(), birthday.getText().toString());
                    dbHandler.insertPerson(temp_person);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                }
            }

        });
    }
}
 /*


  */