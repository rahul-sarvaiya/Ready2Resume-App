
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
        import com.google.firebase.database.FirebaseDatabase;


        import java.util.HashMap;
        import java.util.Map;
        import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {


    Button callSignIn,succ;
    TextInputLayout name,mail,pass,phone;
    String name1,mail1,pass1,phone1;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        callSignIn= findViewById(R.id.signin_jum);
        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        mail = findViewById(R.id.email);
        name1 = name.getEditText().getText().toString();
        pass1 = pass.getEditText().getText().toString();
        mail1 = mail.getEditText().getText().toString();
        progressBar = findViewById(R.id.load);

        callSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        succ= findViewById(R.id.succ);
        succ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePassword() | !validateUsername() | !validateEmail()){
                    return;
                }
                else {
                    firebaseAuth = FirebaseAuth.getInstance();
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(mail.getEditText().getText().toString(),pass.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        UserHelperClass user = new UserHelperClass(name.getEditText().getText().toString(),mail.getEditText().getText().toString());
                                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(Signup.this,"Registered Successful",Toast.LENGTH_LONG).show();
                                                    progressBar.setVisibility(View.GONE);
                                                    openActivity();
                                                }
                                                else{
                                                    Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    }
                                    else{
                                        Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                }
            }
        });

    }
    private Boolean validateUsername() {
        String val = name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            name.setError("Username is to long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            name.setError("White Spaces are not allowed");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
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

    private Boolean validatePassword() {
        String val = pass.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{5,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            pass.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            pass.setError("Password is too weak");
            return false;
        } else {
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
