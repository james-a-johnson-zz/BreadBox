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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import spacepirates.breadbox.model.Admin;
import spacepirates.breadbox.model.BasicUser;
import spacepirates.breadbox.model.DatabaseUser;
import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.LocationEmployee;
import spacepirates.breadbox.model.Manager;
import spacepirates.breadbox.model.Model;
import spacepirates.breadbox.model.User;
import spacepirates.breadbox.model.UserType;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private Button register;
    private Spinner userType;
    private EditText emailText;
    private EditText passwordText;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = FirebaseDatabase.getInstance().getReference();

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
        final String email = emailText.getText().toString();
        final String pass = passwordText.getText().toString();
        final UserType ut;
        final User newUser;
        switch (userType.getSelectedItem().toString()) {
            case "Basic":
                ut = UserType.BASIC;
                newUser = new BasicUser(email, ut);
                break;
            case "Location Employee":
                ut = UserType.LOCATION_EMPLOYEE;
                newUser = new LocationEmployee(email, ut, new Location());
                break;
            case "Manager":
                ut = UserType.MANAGER;
                newUser = new Manager(email, ut, new Location());
                break;
            case "Administrator":
                ut = UserType.ADMINISTRATOR;
                newUser = new Admin(email, ut);
                break;
            // TODO: Disallow registering a guest user
                /*
            case "Guest":
                ut = UserType.GUEST;
                newUser = new GuestUser();
                break;
                */
            default:
                ut = UserType.BASIC;
                newUser = new BasicUser(email, ut);
                break;
        }
        final DatabaseUser dbUser = new DatabaseUser(email, ut);

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // TODO: Need to add user to a user database that includes the type
                            // TODO: Include name and location depending on employee type?
                            // Sign in success, update UI with the signed-in user's information
                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            // Log.d("Uploading user", "" + userID + newUser.getUsername());
                            db.child("users").child(userID).setValue(dbUser);
                            Model.getInstance().setCurrentUser(newUser);
                            Context context = getApplicationContext();
                            Intent intent = new Intent(context, LocationsActivity.class);
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
