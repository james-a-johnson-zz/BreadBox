package spacepirates.breadbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import spacepirates.breadbox.model.Admin;
import spacepirates.breadbox.model.BasicUser;
import spacepirates.breadbox.model.DatabaseUser;
import spacepirates.breadbox.model.Location;
import spacepirates.breadbox.model.LocationEmployee;
import spacepirates.breadbox.model.Manager;
import spacepirates.breadbox.model.Model;
import spacepirates.breadbox.model.User;
import spacepirates.breadbox.model.UserType;

public class LoginActivity extends AppCompatActivity {
    final String tag = "LoginActivity";
    FirebaseAuth firebaseAuth;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseAPP.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Model.getInstance();
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
                                    Log.d(tag, "User logged in");
                                    String userID = firebaseAuth.getCurrentUser().getUid();
                                    userLoggedIn(userID);
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
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

    }

    private void userLoggedIn(String UID) {
        Log.d(tag, "Starting database fetch");
        db.child("users").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(tag, "Got response from database");
                if (dataSnapshot.exists()) {
                    Log.d(tag, "Fetch successful");
                    DatabaseUser currentUser = dataSnapshot.getValue(DatabaseUser.class);
                    Model m = Model.getInstance();
                    String username = currentUser.getUsername();
                    UserType ut = currentUser.getType();
                    User current;
                    switch (ut) {
                        case ADMINISTRATOR:
                            current = new Admin(username, ut);
                            m.setCurrentUser(current);
                            break;
                        case MANAGER:
                            current = new Manager(username, ut, new Location());
                            m.setCurrentUser(current);
                            break;
                        case BASIC:
                            current = new BasicUser(username, ut);
                            m.setCurrentUser(current);
                            break;
                        case LOCATION_EMPLOYEE:
                            current = new LocationEmployee(username, ut, new Location());
                            m.setCurrentUser(current);
                            break;
                    }
                    Context c = getApplicationContext();
                    Intent i = new Intent(c, LocationsActivity.class);
                    c.startActivity(i);
                } else {
                    Log.d(tag, "Fetch unsuccessful");
                    failedLogIn();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                failedLogIn();
            }
        });
    }

    private void failedLogIn() {
        firebaseAuth.signOut();
        final String failedLoginMessage = "User Login failed";
        Toast.makeText(getApplicationContext(), failedLoginMessage, Toast.LENGTH_SHORT).show();
        Context context = getApplicationContext();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}