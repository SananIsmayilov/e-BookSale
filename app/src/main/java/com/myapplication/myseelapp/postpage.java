package com.myapplication.myseelapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myapplication.myseelapp.databinding.ActivityPostpageBinding;

import java.util.HashMap;
import java.util.UUID;

public class postpage extends AppCompatActivity {
    public FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
ActivityResultLauncher<Intent> acintent;
ActivityResultLauncher<String> acstr;
ActivityPostpageBinding bindin;
FirebaseStorage firebaseStorage;
StorageReference storageReference;
SQLiteDatabase db;

Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindin =ActivityPostpageBinding.inflate(getLayoutInflater());
        View view=bindin.getRoot();
        setContentView(view);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseStorage =FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        registerlanc();
        db=this.openOrCreateDatabase("Book",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(bname VARCHAR,aname VARCHAR,bsale VARCHAR)");
    }
    public void galerayaget(View v){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                Snackbar.make(v,"ICAZEYE EHTIYYAC VAR",Snackbar.LENGTH_INDEFINITE).setAction("Icaze lazimdir", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        acstr.launch(Manifest.permission.CAMERA);

                    }
                } ).show();

            }else{
                acstr.launch(Manifest.permission.CAMERA);
            }

        }else{
            Intent intenttogalery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            acintent.launch(intenttogalery);
        }
    }
    public void registerlanc(){

        acintent=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
               if(result.getResultCode()==RESULT_OK){
                   Intent intentfromresult=result.getData();
                   if(intentfromresult!=null){
                        imageUri=intentfromresult.getData();
                       bindin.imageview.setImageURI(imageUri);
                   }
               }
            }
        });


        acstr= registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
             if(result){
                 Intent intenttogalery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             }else{
                 Toast.makeText(postpage.this, "Icazəyə ehtiyyac var", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
    public void melumatgonder(View v){

        if (imageUri != null) {

            //universal unique id
            UUID uuid = UUID.randomUUID();
            final String imageName = "images/" + uuid + ".jpg";

            storageReference.child(imageName).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //Download URL

                    StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                    newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String downloadUrl = uri.toString();

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userEmail = firebaseUser.getEmail();

                            String ad = bindin.author.getText().toString();
                            String author = bindin.phonenum2.getText().toString();
                            String sales= bindin.qiymet.getText().toString();
                            String phonenum3=bindin.phonenum3.getText().toString();
                            String k="INSERT INTO book (bname,aname,bsale)  VALUES(?,?,?)";

                            SQLiteStatement sqLiteStatement=db.compileStatement(k);
                            sqLiteStatement.bindString(1,ad);
                            sqLiteStatement.bindString(2,author);
                            sqLiteStatement.bindString(3,sales);
                            sqLiteStatement.execute();

                            HashMap<String, Object> postData = new HashMap<>();
                            postData.put("useremail",userEmail);
                            postData.put("downloadurl",downloadUrl);
                            postData.put("Kitabın adı",ad);
                            postData.put("date", FieldValue.serverTimestamp());
                            postData.put("Kitab müəllifi",author);
                            postData.put("qiyməti",sales);
                            postData.put("Əlaqə nömrəsi",phonenum3);


                            firebaseFirestore.collection("Posts").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                    Intent intent = new Intent(postpage.this, alissatis.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    Toast.makeText(postpage.this, "Kitabınız əlavə edildi...", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(postpage.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(postpage.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });

        };
    }}