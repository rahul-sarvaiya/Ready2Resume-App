package com.example.myresumetemplates;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class tamplate3 extends AppCompatActivity {


    private TextView fnamet3;
    private TextView addresst3;
    private TextView phonet3;
    private TextView emailt3;
    private TextView summt3;
    private TextView cnamet3;
    private TextView ccityt3;
    private TextView cpost3;
    private TextView cjoint3;
    private TextView cleavet3;
    private TextView cname2t3;
    private TextView ccity2t3;
    private TextView cpos2t3;
    private TextView cjoin2t3;
    private TextView cleave2t3;
    private TextView instt3;
    private TextView educ_cityt3;
    private TextView educ_majort3;
    private TextView educ_gyrt3;
    private TextView educ_summt3;
    private TextView skill1t3;
    private TextView skill2t3;
    private TextView skill3t3;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE=1;
    Uri imageUri;


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
        setContentView(R.layout.activity_tamplate3);


        fnamet3 = findViewById(R.id.fname);
        addresst3 = findViewById(R.id.address);
        phonet3 = findViewById(R.id.phone);
        emailt3 = findViewById(R.id.email);
        summt3 = findViewById(R.id.summ);
        cnamet3 = findViewById(R.id.cname);
        ccityt3 = findViewById(R.id.ccity);
        cpost3 = findViewById(R.id.cpos);
        cjoint3 = findViewById(R.id.cjoin);
        cleavet3 = findViewById(R.id.cleave);
        cname2t3 = findViewById(R.id.cname2);
        ccity2t3 = findViewById(R.id.ccity2);
        cpos2t3 = findViewById(R.id.cpos2);
        cjoin2t3 = findViewById(R.id.cjoin2);
        cleave2t3 = findViewById(R.id.cleave2);
        instt3 = findViewById(R.id.inst);
        educ_cityt3 = findViewById(R.id.educ_city);
        educ_majort3 = findViewById(R.id.educ_major);
        educ_gyrt3 = findViewById(R.id.educ_gyr);
        educ_summt3 = findViewById(R.id.educ_summ);
        skill1t3 = findViewById(R.id.skill1);
        skill2t3 = findViewById(R.id.skill2);
        skill3t3 = findViewById(R.id.skill3);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = firebaseUser.getUid();


        //IMAGE


        ProfileImage = (CircleImageView) findViewById(R.id.profile_img);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });



        //PERSONAL DATA 3



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


                    fnamet3.setText(fname1);
                    emailt3.setText(email1);
                    addresst3.setText(add1);
                    summt3.setText(summ1);
                    phonet3.setText(phn1);

                }
                else {
                    fnamet3.setText("");
                    emailt3.setText("");
                    addresst3.setText("");
                    summt3.setText("");
                    phonet3.setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate3.this,"Something went wrong",Toast.LENGTH_LONG).show();

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


                    instt3.setText(In_name);
                    educ_cityt3.setText(add);
                    educ_summt3.setText(summ);
                    educ_gyrt3.setText(yr);
                    educ_majort3.setText(major);


                }
                else {

                    instt3.setText("");
                    educ_cityt3.setText("");
                    educ_summt3.setText("");
                    educ_gyrt3.setText("");
                    educ_majort3.setText("");


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate3.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });


        //EXPERIENCE


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


                    String In_name2= userPersonalHelper.C_name2;
                    String add2=userPersonalHelper.C_add2;
                    String summ2=userPersonalHelper.C_pos2;
                    String yr2=userPersonalHelper.yr2;
                    String major2=userPersonalHelper.C_leve2;

                    cnamet3.setText(In_name);
                    ccityt3.setText(add);
                    cpost3.setText(summ);
                    cjoint3.setText(yr);
                    cleavet3.setText(major);


                    cname2t3.setText(In_name2);
                    ccity2t3.setText(add2);
                    cpos2t3.setText(summ2);
                    cjoin2t3.setText(yr2);
                    cleave2t3.setText(major2);



                }
                else {

                    cnamet3.setText("");
                    ccityt3.setText("");
                    cpost3.setText("");
                    cjoint3.setText("");
                    cleavet3.setText("");


                    cname2t3.setText("");
                    ccity2t3.setText("");
                    cpos2t3.setText("");
                    cjoin2t3.setText("");
                    cleave2t3.setText("");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate3.this,"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });


        //SKILLS

        reference.child(userID).child("Skill1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserSkillHelper userPersonalHelper = snapshot.getValue(UserSkillHelper.class);

                if (userPersonalHelper != null){
                    //In_name3,add3,summ3,yr3,major3,In_name1,add1,summ1,yr1,major1,In_name2,add2,summ2,yr2,major2;
                    //String In_name,add,summ,yr,major,In_nam,ad,sum,y,majo,In_na,a,su,yrr,maj;

                    String In_name= userPersonalHelper.skill1;
                    String add=userPersonalHelper.skill2;
                    String summ=userPersonalHelper.skill3;

                    skill1t3.setText(In_name);
                    skill2t3.setText(add);
                    skill3t3.setText(summ);

                }
                else {

                    skill1t3.setText("");
                    skill2t3.setText("");
                    skill3t3.setText("");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tamplate3.this,"Something went wrong",Toast.LENGTH_LONG).show();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                ProfileImage.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
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


        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/template3.pdf");

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
        File file=new File(Environment.getExternalStorageDirectory(),"/template3.pdf");
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