package com.example.myresumetemplates;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class tamplate1 extends AppCompatActivity {

    private TextView fnamet1;
    private TextView addresst1;
    private TextView phonet1;
    private TextView emailt1;
    private TextView summt1;
    private TextView cnamet1;
    private TextView ccityt1;
    private TextView cpost1;
    private TextView cjoint1;
    private TextView cleavet1;
    private TextView instt1;
    private TextView educ_cityt1;
    private TextView educ_majort1;
    private TextView educ_gyrt1;
    private TextView educ_summt1;
    private TextView inst2t1;
    private TextView educ_city2t1;
    private TextView educ_major2t1;
    private TextView educ_gyr2t1;
    private TextView educ_summ2t1;
    private TextView skill1t1;
    private TextView skill2t1;
    private TextView skill3t1;
    private TextView skill4t1;

    private Button btn;
    private RelativeLayout linear;
    private Bitmap bitmap;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamplate1);

        fnamet1 = findViewById(R.id.fname);
        addresst1 = findViewById(R.id.address);
        phonet1 = findViewById(R.id.phone);
        emailt1 = findViewById(R.id.email);
        summt1 = findViewById(R.id.summ);
        cnamet1 = findViewById(R.id.cname);
        ccityt1 = findViewById(R.id.ccity);
        cpost1 = findViewById(R.id.cpos);
        cjoint1 = findViewById(R.id.cjoin);
        cleavet1 = findViewById(R.id.cleave);
        instt1 = findViewById(R.id.inst);
        educ_cityt1 = findViewById(R.id.educ_city);
        educ_majort1 = findViewById(R.id.educ_major);
        educ_gyrt1 = findViewById(R.id.educ_gyr);
        educ_summt1 = findViewById(R.id.educ_summ);
        inst2t1 = findViewById(R.id.inst2);
        educ_city2t1 = findViewById(R.id.educ_city2);
        educ_major2t1 = findViewById(R.id.educ_major2);
        educ_gyr2t1 = findViewById(R.id.educ_gyr2);
        educ_summ2t1 = findViewById(R.id.educ_summ2);
        skill1t1 = findViewById(R.id.skill1);
        skill2t1 = findViewById(R.id.skill2);
        skill3t1 = findViewById(R.id.skill3);
       // skill4t1 = findViewById(R.id.skill4);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = firebaseUser.getUid();



        //PERSONAL DATA



        reference.child(userID).child("personaldata1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserPersonalHelper userPersonalHelper = snapshot.getValue(UserPersonalHelper.class);

                if (userPersonalHelper != null){
                    //fname1,email1,add1,phn1,sum1
                    //String name1,mail1,add1,summ1,phn1;

                    String fname1 = userPersonalHelper.fname1;
                    String email1 = userPersonalHelper.email1;
                    String add1 = userPersonalHelper.add1;
                    String summ1 = userPersonalHelper.sum1;
                    String phn1 = userPersonalHelper.phn1;


                    fnamet1.setText(fname1);
                    emailt1.setText(email1);
                    addresst1.setText(add1);
                    summt1.setText(summ1);
                    phonet1.setText(phn1);

                }
                else {
                    fnamet1.setText("");
                    emailt1.setText("");
                    addresst1.setText("");
                    summt1.setText("");
                    phonet1.setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate1.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });



        //EDUCATION DATA




        reference.child(userID).child("Education1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserEduHelper userPersonalHelper = snapshot.getValue(UserEduHelper.class);

                if (userPersonalHelper != null){
                    //In_name3,add3,summ3,yr3,major3,In_name1,add1,summ1,yr1,major1,In_name2,add2,summ2,yr2,major2;
                    //String In_name,add,summ,yr,major,In_nam,ad,sum,y,majo,In_na,a,su,yrr,maj;

                    String In_name= userPersonalHelper.In_name1;
                    String add=userPersonalHelper.add1;
                    String summ=userPersonalHelper.summ1;
                    String yr=userPersonalHelper.yr1;
                    String major=userPersonalHelper.major1;

                    String ad=userPersonalHelper.add2;
                    String sum=userPersonalHelper.summ2;
                    String y=userPersonalHelper.yr2;
                    String majo=userPersonalHelper.major2;
                    String In_nam=userPersonalHelper.In_name2;

                    instt1.setText(In_name);
                    educ_cityt1.setText(add);
                    educ_summt1.setText(summ);
                    educ_gyrt1.setText(yr);
                    educ_majort1.setText(major);

                    inst2t1.setText(In_nam);
                    educ_city2t1.setText(ad);
                    educ_summ2t1.setText(sum);
                    educ_gyr2t1.setText(y);
                    educ_major2t1.setText(majo);

                }
                else {

                    instt1.setText("");
                    educ_cityt1.setText("");
                    educ_summt1.setText("");
                    educ_gyrt1.setText("");
                    educ_majort1.setText("");

                    inst2t1.setText("");
                    educ_city2t1.setText("");
                    educ_summ2t1.setText("");
                    educ_gyr2t1.setText("");
                    educ_major2t1.setText("");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate1.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });


        //EXperience Data:


        reference.child(userID).child("Experience1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserExpHelper userPersonalHelper = snapshot.getValue(UserExpHelper.class);

                if (userPersonalHelper != null){
                    //In_name3,add3,summ3,yr3,major3,In_name1,add1,summ1,yr1,major1,In_name2,add2,summ2,yr2,major2;
                    //String In_name,add,summ,yr,major,In_nam,ad,sum,y,majo,In_na,a,su,yrr,maj;

                    String In_name= userPersonalHelper.C_name1;
                    String add=userPersonalHelper.C_add1;
                    String summ=userPersonalHelper.C_pos1;
                    String yr=userPersonalHelper.yr1;
                    String major=userPersonalHelper.C_leve1;

                    cnamet1.setText(In_name);
                    ccityt1.setText(add);
                    cpost1.setText(summ);
                    cjoint1.setText(yr);
                    cleavet1.setText(major);

                }
                else {

                    cnamet1.setText("");
                    ccityt1.setText("");
                    cpost1.setText("");
                    cjoint1.setText("");
                    cleavet1.setText("");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate1.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });


        //SKILLS:



        reference.child(userID).child("Skill1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserSkillHelper userPersonalHelper = snapshot.getValue(UserSkillHelper .class);

                if (userPersonalHelper != null){
                    //In_name3,add3,summ3,yr3,major3,In_name1,add1,summ1,yr1,major1,In_name2,add2,summ2,yr2,major2;
                    //String In_name,add,summ,yr,major,In_nam,ad,sum,y,majo,In_na,a,su,yrr,maj;

                    String In_name= userPersonalHelper.skill1;
                    String add=userPersonalHelper.skill2;
                    String summ=userPersonalHelper.skill3;

                    skill1t1.setText(In_name);
                    skill2t1.setText(add);
                    skill3t1.setText(summ);

                }
                else {

                    skill1t1.setText("");
                    skill2t1.setText("");
                    skill3t1.setText("");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate1.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });


        //PDF

        linear=findViewById(R.id.lineard);
        btn=findViewById(R.id.btnd);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.d("size",""+linear.getWidth()+" "+linear.getWidth());
                bitmap = LoadBitmap(linear,linear.getWidth(),linear.getHeight());
                createPdf();
            }
        });
    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap= Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {
        WindowManager windowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width=displayMetrics.widthPixels;
        float height=displayMetrics.heightPixels;
        int convertWidth=(int)width,convertHeight=(int)height;

        PdfDocument document=new PdfDocument();
        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(convertWidth,convertHeight,1).create();
        PdfDocument.Page page=document.startPage(pageInfo);
        Canvas canvas= page.getCanvas();

        Paint paint= new Paint();
        canvas.drawPaint(paint);
        bitmap= Bitmap.createScaledBitmap(bitmap,convertWidth,convertHeight,true);

        canvas.drawBitmap(bitmap,0,0,null);
        document.finishPage(page);


        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/template1.pdf");

        try{
            document.writeTo(new FileOutputStream(file));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this,"something wrong try again"+e.toString(),Toast.LENGTH_SHORT).show();

            //after close the document
            document.close();
            Toast.makeText(this,"Successfully Downloaded",Toast.LENGTH_SHORT).show();

            openPdf();//this method is used to open pdf file that is downloaded

        }

    }

    private void openPdf() {
        File file=new File(Environment.getExternalStorageDirectory(),"/template1.pdf");
        if(file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri= Uri.fromFile(file);
            intent.setDataAndType(uri,"application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            }catch (ActivityNotFoundException e){
                Toast.makeText(this,"No Application for pdf view ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}