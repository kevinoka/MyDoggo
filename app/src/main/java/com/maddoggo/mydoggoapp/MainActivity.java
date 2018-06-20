package com.maddoggo.mydoggoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maddoggo.mydoggoapp.Model.User;

public class MainActivity extends AppCompatActivity {


    Button btnSignUp, btnSignIn;
    RelativeLayout rootLayout;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //automatic pop up keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //init firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        //init view
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        //event
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterDialog();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });

    }

    private void showLoginDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Log In");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signin_layout = inflater.inflate(R.layout.layout_signin,null);

        final EditText editEmail = signin_layout.findViewById(R.id.editEmail);
        final EditText editPassword = signin_layout.findViewById(R.id.editPassword);

        dialog.setView(signin_layout);

        //set button
        dialog.setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        //check validation
                        if (TextUtils.isEmpty(editEmail.getText().toString())) {
                            Snackbar.make(rootLayout, "Please enter your email address", Snackbar.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        if (TextUtils.isEmpty(editPassword.getText().toString())) {
                            Snackbar.make(rootLayout, "Please enter your password", Snackbar.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        if (editPassword.getText().toString().length() < 6) {
                            Snackbar.make(rootLayout, "Your password is too short", Snackbar.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        //login
                        auth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        startActivity(new Intent(MainActivity.this, Home.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout, "Failed"+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });


                    }
                });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });



        dialog.show();
    }

    private void showRegisterDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Sign Up");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signup_layout = inflater.inflate(R.layout.layout_signup,null);

        final EditText editFirstName = signup_layout.findViewById(R.id.editFirstName);
        final EditText editLastName = signup_layout.findViewById(R.id.editLastName);
        final EditText editEmail = signup_layout.findViewById(R.id.editEmail);
        final EditText editPassword = signup_layout.findViewById(R.id.editPassword);

        dialog.setView(signup_layout);

        //set button
        dialog.setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

                //check validation
                if(TextUtils.isEmpty(editFirstName.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Please enter your first name", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if(TextUtils.isEmpty(editLastName.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Please enter your last name", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if(TextUtils.isEmpty(editEmail.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Please enter your email address", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if(TextUtils.isEmpty(editPassword.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Please enter your password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if(editPassword.getText().toString().length() < 6)
                {
                    Snackbar.make(rootLayout, "Your password is too short", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                //register/sign up new user
                auth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //save user to db
                                User user = new User();
                                user.setFirstName(editFirstName.getText().toString());
                                user.setLastName(editLastName.getText().toString());
                                user.setEmailAddress(editEmail.getText().toString());
                                user.setPassword(editPassword.getText().toString());

                                //use email as a key
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(rootLayout, "Sign up successful", Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout, "Sign up failed "+e.getMessage(), Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });

                            }
                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout, "Your password is too short", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();


    }
}
