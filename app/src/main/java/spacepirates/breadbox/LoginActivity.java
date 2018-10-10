package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    //FirebaseAuth firebase = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseAPP.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameField = findViewById(R.id.UsernameField);
        final EditText passwordField = findViewById(R.id.PasswordField);
        Button loginButton = findViewById(R.id.LoginButton);
        Button registerButton = findViewById(R.id.RegisterButton);


        //Test button
        Button testButton = findViewById(R.id.testButton);

        final String username = "user";
        final String password = "pass";
        final String failedLoginMessage = getString(R.string.login_fail_message);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameField.getText().toString().equals(username)
                        && passwordField.getText().toString().equals(password)) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast failMessage = Toast.makeText(getApplicationContext(), failedLoginMessage,
                            Toast.LENGTH_SHORT);
                    failMessage.show();
                }
        }}
        );

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Register.class);
                context.startActivity(intent);
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, LocationsActivity.class);
                context.startActivity(intent);
            }
        });

    }

}
