package com.example.myresumetemplates;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class personaldata2 extends AppCompatActivity {
    Button save;
    TextInputLayout name, mail, add, summ, phn;
    String name1, mail1, add1, summ1, phn1;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldata2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        save = findViewById(R.id.save);
        name = findViewById(R.id.fullname);
        mail = findViewById(R.id.email);
        add = findViewById(R.id.address);
        summ = findViewById(R.id.summary);
        phn = findViewById(R.id.contact);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = firebaseUser.getUid();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFullName() | !validateAddress() | !validateContact() | !validateEmail() | !validateSummary()) {
                    return;
                } else {

                    reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            UserPersonalHelper2 user = new UserPersonalHelper2(name.getEditText().getText().toString(), mail.getEditText().getText().toString(), add.getEditText().getText().toString(), phn.getEditText().getText().toString(), summ.getEditText().getText().toString());
                            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(personaldata2.this, "Saved Successful", Toast.LENGTH_LONG).show();

                                        openActivity();
                                    } else {
                                        Toast.makeText(personaldata2.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(personaldata2.this, "Something went wrong", Toast.LENGTH_LONG).show();

                        }
                    });


                }

            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPersonalHelper2 userPersonalHelper = snapshot.getValue(UserPersonalHelper2.class);

                if (userPersonalHelper != null) {
                    //fname1,email1,add1,phn1,sum1
                    //String name1,mail1,add1,summ1,phn1;

                    name1 = userPersonalHelper.fname2;
                    mail1 = userPersonalHelper.email2;
                    add1 = userPersonalHelper.add2;
                    summ1 = userPersonalHelper.sum2;
                    phn1 = userPersonalHelper.phn2;


                    name.getEditText().setText(name1);
                    mail.getEditText().setText(mail1);
                    add.getEditText().setText(add1);
                    summ.getEditText().setText(summ1);
                    phn.getEditText().setText(phn1);

                } else {
                    name.getEditText().setText("");
                    mail.getEditText().setText("");
                    add.getEditText().setText("");
                    summ.getEditText().setText("");
                    phn.getEditText().setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(personaldata2.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });

    }

    private Boolean validateFullName() {
        String val = name.getEditText().getText().toString();

        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 40) {
            name.setError("Full Name is to long");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateAddress() {
        String val = add.getEditText().getText().toString();


        if (val.isEmpty()) {
            add.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 500) {
            add.setError("Address is to long");
            return false;
        } else {
            add.setError(null);
            add.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateContact() {
        String val = phn.getEditText().getText().toString();


        if (val.isEmpty()) {
            phn.setError("Field cannot be empty");
            return false;
        } else if (val.length() > 10) {
            phn.setError("Invalid Contact Number");
            return false;
        } else {
            phn.setError(null);
            phn.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = mail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            mail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            mail.setError("Invalid email address");
            return false;
        } else {
            mail.setError(null);
            mail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateSummary() {
        String val = summ.getEditText().getText().toString();

        if (val.isEmpty()) {
            summ.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 400) {
            summ.setError("Summary is to long");
            return false;
        } else {
            summ.setError(null);
            summ.setErrorEnabled(false);
            return true;
        }
    }


    public void openActivity() {
        Intent intent = new Intent(this, createprofile1.class);
        startActivity(intent);
        finish();
    }

}
