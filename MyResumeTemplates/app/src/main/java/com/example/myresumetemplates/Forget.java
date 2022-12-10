package com.example.myresumetemplates;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.android.material.textfield.TextInputLayout;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.FirebaseDatabase;

public class Forget extends AppCompatActivity {

    Button succ;
    TextInputLayout mail;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    private static int TIME_OUT = 7000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mail=findViewById(R.id.email);
        succ= findViewById(R.id.succes);
        progressBar=findViewById(R.id.load);

        firebaseAuth = FirebaseAuth.getInstance();

        succ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !validateEmail()) {
                    return;
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.sendPasswordResetEmail(mail.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Toast.makeText(Forget.this, "Resest Password link is send to your email", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(Forget.this,Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }, TIME_OUT);


                            }else
                            {
                                Toast.makeText(Forget.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

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


}