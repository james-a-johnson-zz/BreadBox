package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import spacepirates.breadbox.model.Model;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseAPP.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        final EditText usernameField = findViewById(R.id.UsernameField);
        final EditText passwordField = findViewById(R.id.PasswordField);
        Button loginButton = findViewById(R.id.LoginButton);
        Button registerButton = findViewById(R.id.RegisterButton);


        //Test button
        Button testButton = findViewById(R.id.testButton);

        //TODO move this string.
        //These sorts of app level final strings should be in the resource String xml, and then referenced,
        final String failedLoginMessage = getString(R.string.login_fail_message);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(usernameField.getText().toString(),
                        passwordField.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Context context = getApplicationContext();
                                    Intent intent = new Intent(context, LocationsActivity.class);
                                    context.startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), failedLoginMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

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
                Model model = Model.getInstance();
                Context context = view.getContext();
                Intent intent = new Intent(context, LocationsActivity.class);
                context.startActivity(intent);
            }
        });

    }
}