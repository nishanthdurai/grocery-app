package com.grocery.app.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.grocery.app.R;
import com.grocery.app.model.Admin;

import java.util.regex.Pattern;

public class AdminLoginActivity extends AppCompatActivity {

    EditText et_email, et_password;
    Button btn_login;
    TextView tv_registerBtn;

    TextView tv_forgotPassword;

    DatabaseReference dbInstance;

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);

        setTitle("Admin");

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_registerBtn = findViewById(R.id.tv_registerButton);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);

        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(TutorSignIn.this, TutorResetPasswordActivity.class));
            }
        });

        progressDialog = new ProgressDialog(this);

        dbInstance = FirebaseDatabase.getInstance().getReference();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        tv_registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginActivity.this, AdminSignUpActivity.class));
            }
        });
    }

    private void performLogin() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty()) {
            et_email.setError("Please Enter Email");
            return;
        } else if (!pat.matcher(email).matches()) {
            et_email.setError("Please Enter a valid Email");
            return;
        } else if (password.isEmpty()) {
            et_password.setError("Please input Password");
            return;
        } else if (password.length() < 6) {
            et_password.setError("Password too short");
            return;
        } else {
            progressDialog.setMessage("Validating credentials....");
            progressDialog.setTitle("Loading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            // validate credentials with email and password
            Query emailQuery = dbInstance.child("admin").orderByChild("email").equalTo(email);
            emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot tutorSnapshot : dataSnapshot.getChildren()) {
                            Admin admin = tutorSnapshot.getValue(Admin.class);
                            Log.d("Grocery logs", "onDataChange: " + admin.getPassword()+password );
                            if (admin != null && password.equals(admin.getPassword())) {
                                // check if password is valid
                                Toast.makeText(AdminLoginActivity.this, "SignIn success.",
                                        Toast.LENGTH_SHORT).show();
                                sendUserToMainActivity(admin.getId(), admin.getEmail(), admin.getPassword());
                                return; // Exit once a match is found
                            }
                        }

                        Toast.makeText(AdminLoginActivity.this, "Invalid password..!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Email is not registered, " + "try signing up..!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminLoginActivity.this, AdminSignUpActivity.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Log.e("Firebase", "Database error", databaseError.toException());
                    Toast.makeText(AdminLoginActivity.this, "Error validating credentials, " + "try again later..!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToMainActivity(String tutorId, String email, String password) {
//        Intent intent = new Intent(TutorSignIn.this, TutorRealDashboard.class);
//        intent.putExtra("email", email);
//        intent.putExtra("tutorId", tutorId);
//        intent.putExtra("password", password);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button action
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}