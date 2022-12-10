package com.example.myresumetemplates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class skills3 extends AppCompatActivity {


    Button save;
    TextInputLayout skill_1,skill_2,skill_3,skill_4,skill_5;
    String skill1s,skill2s,skill3s,skill4s,skill5s;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private String userID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills3);

        save=findViewById(R.id.save);
        skill_1=findViewById(R.id.skill1);
        skill_2=findViewById(R.id.skill2);
        skill_3=findViewById(R.id.skill3);
        skill_4=findViewById(R.id.skill4);
        skill_5=findViewById(R.id.skill5);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = firebaseUser.getUid();



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserSkillHelper user = new UserSkillHelper(skill_1.getEditText().getText().toString(),skill_2.getEditText().getText().toString(),skill_3.getEditText().getText().toString(),skill_4.getEditText().getText().toString(),skill_5.getEditText().getText().toString());
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Skill1")
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(skills3.this,"Saved Successful",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(skills3.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(skills3.this,"Something went wrong",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        reference.child(userID).child("Skill1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserSkillHelper userPersonalHelper = snapshot.getValue(UserSkillHelper.class);

                if (userPersonalHelper != null){
                    //fname1,email1,add1,phn1,sum1
                    //String name1,mail1,add1,summ1,phn1;

                    skill1s = userPersonalHelper.skill1;
                    skill2s = userPersonalHelper.skill2;
                    skill3s = userPersonalHelper.skill3;
                    skill4s = userPersonalHelper.skill4;
                    skill5s = userPersonalHelper.skill5;


                    skill_1.getEditText().setText(skill1s);
                    skill_2.getEditText().setText(skill2s);
                    skill_3.getEditText().setText(skill3s);
                    skill_4.getEditText().setText(skill4s);
                    skill_5.getEditText().setText(skill5s);

                }
                else {
                    skill_1.getEditText().setText("");
                    skill_2.getEditText().setText("");
                    skill_3.getEditText().setText("");
                    skill_4.getEditText().setText("");
                    skill_5.getEditText().setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(skills3.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });




    }


}