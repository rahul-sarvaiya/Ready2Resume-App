package com.example.myresumetemplates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class education1 extends AppCompatActivity {


    Button save;
    TextInputEditText In_name3,add3,summ3,yr3,major3,In_name1,add1,summ1,yr1,major1,In_name2,add2,summ2,yr2,major2;
    String In_name,add,summ,yr,major,In_nam,ad,sum,y,majo,In_na,a,su,yrr,maj;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_education1);

        save=findViewById(R.id.Save);

        In_name3=findViewById(R.id.institution3);
        add3=findViewById(R.id.city3);
        summ3=findViewById(R.id.info3);
        yr3=findViewById(R.id.gradyr3);
        major3=findViewById(R.id.major3);

        In_name1=findViewById(R.id.institution);;
        add1=findViewById(R.id.city);
        summ1=findViewById(R.id.info);
        yr1=findViewById(R.id.gradyr);
        major1=findViewById(R.id.major);

        In_name2=findViewById(R.id.institution2);
        add2=findViewById(R.id.city2);
        summ2=findViewById(R.id.info2);
        yr2=findViewById(R.id.gradyr2);
        major2=findViewById(R.id.major2);

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
                            UserEduHelper user = new UserEduHelper(In_name3.getText().toString(),add3.getText().toString(),summ3.getText().toString(),yr3.getText().toString(),major3.getText().toString(),In_name1.getText().toString(),add1.getText().toString(),summ1.getText().toString(),yr1.getText().toString(),major1.getText().toString(),In_name2.getText().toString(),add2.getText().toString(),summ2.getText().toString(),yr2.getText().toString(),major2.getText().toString());
                            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Education1")
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(education1.this,"Saved Successful",Toast.LENGTH_LONG).show();
                                        openActivity();
                                    }
                                    else{
                                        Toast.makeText(education1.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(education1.this,"Something went wrong",Toast.LENGTH_LONG).show();

                        }
                    });





                }


        });

        reference.child(userID).child("Education1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserEduHelper userPersonalHelper = snapshot.getValue(UserEduHelper.class);

                if (userPersonalHelper != null){
                    //In_name3,add3,summ3,yr3,major3,In_name1,add1,summ1,yr1,major1,In_name2,add2,summ2,yr2,major2;
                    //String In_name,add,summ,yr,major,In_nam,ad,sum,y,majo,In_na,a,su,yrr,maj;

                    In_name= userPersonalHelper.In_name1;
                    add=userPersonalHelper.add1;
                    summ=userPersonalHelper.summ1;
                    yr=userPersonalHelper.yr1;
                    major=userPersonalHelper.major1;

                    In_nam=userPersonalHelper.In_name2;
                    ad=userPersonalHelper.add2;
                    sum=userPersonalHelper.summ2;
                    y=userPersonalHelper.yr2;
                    majo=userPersonalHelper.major2;

                    In_na=userPersonalHelper.In_name3;
                    a=userPersonalHelper.add3;
                    su=userPersonalHelper.summ3;
                    yrr=userPersonalHelper.yr3;
                    maj=userPersonalHelper.major3;


                        In_name1.setText(In_name);


                        add1.setText(add);


                        summ1.setText(summ);


                        yr1.setText(yr);


                        major1.setText(major);



                        In_name2.setText(In_nam);


                        add2.setText(ad);


                        summ2.setText(sum);


                        yr2.setText(y);


                        major2.setText(majo);



                        In_name3.setText(In_na);


                        add3.setText(a);


                        summ3.setText(su);


                        yr3.setText(yrr);


                        major3.setText(maj);

                }
                else {

                    In_name1.setText("");
                    add1.setText("");
                    summ1.setText("");
                    yr1.setText("");
                    major1.setText("");

                    In_name2.setText("");
                    add2.setText("");
                    summ2.setText("");
                    yr2.setText("");
                    major2.setText("");

                    In_name3.setText("");
                    add3.setText("");
                    summ3.setText("");
                    yr3.setText("");
                    major3.setText("");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(education1.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });

    }

    public void openActivity(){
        Intent intent = new Intent(education1.this, createprofile1.class);
        startActivity(intent);
        finish();
    }

}