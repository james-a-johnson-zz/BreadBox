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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import spacepirates.breadbox.model.Admin;
import spacepirates.breadbox.model.BasicUser;
import spacepirates.breadbox.model.DatabaseUser;
import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.LocationEmployee;
import spacepirates.breadbox.model.Manager;
import spacepirates.breadbox.model.Model;
import spacepirates.breadbox.model.User;
import spacepirates.breadbox.model.UserType;

/**
 * Registration activity for a new user to enter their email and password
 * The user's information and type are then stored in the user database
 */
public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private Spinner userType;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        db = instance.getReference();

        mAuth = FirebaseAuth.getInstance();
        Button register = findViewById(R.id.RegisterButton);
        userType = findViewById(R.id.UserTypeSpinner);
        emailText = findViewById(R.id.EmailText);
        passwordText = findViewById(R.id.PasswordText);
        TextView registerText = findViewById(R.id.RegisterText);

        registerText.setText("Register a User");

        userType.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, UserType.values()));
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText eText = (EditText) emailText.getText();
        EditText passText = (EditText) passwordText.getText();
        final String email = eText.toString();
        final String pass = passText.getText().toString();
        final UserType ut;
        final User newUser;
        Object item = userType.getSelectedItem();
        switch (item.toString()) {
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
            //Done: Disallow registering a guest user
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
                            //We Need to add user to a user database that includes the type
                            //Also Include name and location depending on employee type?
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseAuth instance = FirebaseAuth.getInstance();
                            FirebaseUser currentUser = instance.getCurrentUser();
                            String userID = Objects.requireNonNull(currentUser).getUid();

                            // Log.d("Uploading user", "" + userID + newUser.getUsername());
                            DatabaseReference reference = db.child("users");
                            reference.child(userID).setValue(dbUser);

                            Model modelInstance = Model.getInstance();
                            modelInstance.setCurrentUser(newUser);
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
