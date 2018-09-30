package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import spacepirates.breadbox.model.UserType;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button register;
    private Spinner userType;
    private EditText emailText;
    private EditText passwordText;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.RegisterButton);
        userType = findViewById(R.id.UserTypeSpinner);
        emailText = findViewById(R.id.EmailText);
        passwordText = findViewById(R.id.PasswordText);
        registerText = findViewById(R.id.RegisterText);

        registerText.setText("Register a User");

        userType.setAdapter(new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, UserType.values()));
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email = emailText.getText().toString();
        String pass = passwordText.getText().toString();
        UserType ut;
        switch (userType.getSelectedItem().toString()) {
            case "Guest":
                ut = UserType.GUEST;
                break;
            case "Basic":
                ut = UserType.BASIC;
                break;
            case "Location Employee":
                ut = UserType.LOCATION_EMPLOYEE;
                break;
            case "Manager":
                ut = UserType.MANAGER;
                break;
            case "Administrator":
                ut = UserType.ADMINISTRATOR;
                break;
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // TODO: Need to add user to a user database that includes the type
                            // TODO: Include name and location depending on employee type?
                            // Sign in success, update UI with the signed-in user's information
                            Context context = getApplicationContext();
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Registering user failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
