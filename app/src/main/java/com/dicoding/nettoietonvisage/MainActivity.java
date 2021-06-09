package com.dicoding.nettoietonvisage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dicoding.nettoietonvisage.retrofit.IUploadAPI;
import com.dicoding.nettoietonvisage.retrofit.RetrofitClient;
import com.dicoding.nettoietonvisage.utils.Common;
import com.dicoding.nettoietonvisage.utils.IUploadCallbacks;
import com.dicoding.nettoietonvisage.utils.ProgressRequestBody;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URISyntaxException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IUploadCallbacks {

    private static final int PICK_FILE_REQUEST = 1000;
    IUploadAPI mService;
    Button btnUpload;
    ImageView imageView;
    Uri selectedFileUri;

    ProgressDialog dialog;

    private IUploadAPI getAPIUpload(){
        return RetrofitClient.getClient().create(IUploadAPI.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selector
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.treatment:
                        startActivity(new Intent(getApplicationContext()
                                ,Treatment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.howtouse:
                        startActivity(new Intent(getApplicationContext()
                                ,HowToUse.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.aboutus:
                        startActivity(new Intent(getApplicationContext()
                                ,AboutUs.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Toast.makeText(MainActivity.this, "Permission Accept", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this, "You Should Accept Permission", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        //Create mService
        mService = getAPIUpload();

        //InitView
        btnUpload = (Button) findViewById(R.id.btn_upload);
        imageView = (ImageView) findViewById(R.id.Photo);

        //Event
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == PICK_FILE_REQUEST){
                if (data!= null){
                    selectedFileUri = data.getData();
                    if (selectedFileUri != null && !selectedFileUri.getPath().isEmpty())
                        imageView.setImageURI(selectedFileUri);
                    else
                        Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void chooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_FILE_REQUEST);
    }

    private void uploadFile() {
        if (selectedFileUri != null){
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMessage("Uploading....");
            dialog.setIndeterminate(false);
            dialog.setMax(100);
            dialog.setCancelable(false);
            dialog.show();

            File file = null;
            try {
                file = new File (Common.getFilePath(this,selectedFileUri));
            } catch (URISyntaxException e){
                e.printStackTrace();
            }
            if (file!=null){
                final ProgressRequestBody requestBody = new ProgressRequestBody(file,this);

                final MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(),requestBody);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mService.UploadFile(body)
                                .enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        dialog.dismiss();
                                        String image_processed_link = new StringBuilder("http://34.67.93.220:80/" +
                                                response.body().replace("\"", "")).toString();
                                        Picasso.get().load(image_processed_link)
                                                .into(imageView);
                                        Toast.makeText(MainActivity.this, "Detected!!!", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        dialog.dismiss();
                                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).start();
            }
            else{
                Toast.makeText(this, "Cannot Upload This File!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onProgressUpdate(int percent) {

    }
}