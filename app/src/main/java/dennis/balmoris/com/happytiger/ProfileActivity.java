package dennis.balmoris.com.happytiger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private DrawerLayout dl;
    private ActionBarDrawerToggle adbt;

    TextView textView;
    TextView showName;
    TextView showEmail;
    TextView showEmailVerified;
    ImageView imageView;
    EditText editText;



    ProgressBar progressBar;
    Uri uriProfileImage;
    String profileImageUrl;

    FirebaseAuth mAuth;

    private static final int CHOOSE_IMAGE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        mAuth = FirebaseAuth.getInstance();

        editText = (EditText) findViewById(R.id.editTextDisplayName);
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        showEmailVerified = (TextView) findViewById(R.id.showEmailVerified);
        showName = (TextView)findViewById(R.id.showName);
        showEmail = (TextView)findViewById(R.id.showEmail);
        editText.setVisibility(View.GONE);
        findViewById(R.id.btnProceed).setVisibility(View.GONE);
        editText.setVisibility(View.GONE);
        showName.setVisibility(View.VISIBLE);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        loadUserInformation();

        findViewById(R.id.btnProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();

                findViewById(R.id.btnProceed).setVisibility(View.GONE);
                findViewById(R.id.btnUpdate).setVisibility(View.VISIBLE);
                editText.setVisibility(View.GONE);
                showName.setVisibility(View.VISIBLE);


            }
        });

        //Drawer Layout
        dl = (DrawerLayout)findViewById(R.id.dl);
        adbt = new ActionBarDrawerToggle(this, dl, R.string.Open,R.string.Close);
        adbt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(adbt);
        adbt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final FirebaseUser user = mAuth.getCurrentUser();
                int id = item.getItemId();

                if(id == R.id.homePage)
                {
                    Toast.makeText(ProfileActivity.this, "You are in this page", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.foodStores)
                {
                    finish();
                    startActivity(new Intent(ProfileActivity.this, FoodStores.class));
                    Toast.makeText(ProfileActivity.this, "Food Stores", Toast.LENGTH_SHORT).show();
                }



                else if(id == R.id.messages) {
                    if(user != null){
                        if(user.isEmailVerified()){
                            finish();
                            startActivity(new Intent(ProfileActivity.this, MessageActivity.class));
                            Toast.makeText(ProfileActivity.this, "Discuss Now", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ProfileActivity.this, "Verify Email First", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
                else if (id == R.id.signout)

                {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    Toast.makeText(ProfileActivity.this, "You have signed out successfully", Toast.LENGTH_SHORT).show();

                }


                return true;
            }
        });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                findViewById(R.id.btnProceed).setVisibility(View.VISIBLE);
                findViewById(R.id.btnUpdate).setVisibility(View.GONE);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getPhotoUrl() != null) {

                GlideApp.with(this).load(user.getPhotoUrl().toString()).into(imageView);
            }


            if (user.getDisplayName() != null) {

                editText.setVisibility(View.GONE);
                editText.setText(user.getDisplayName());
                showEmail.setText(user.getEmail());
                showName.setText(user.getDisplayName());



            }
            if (user.isEmailVerified()) {
                showEmailVerified.setText("Email Verified");
                findViewById(R.id.btnVerify).setVisibility(View.GONE);


            } else {
                showEmailVerified.setText("Email Not Verifed (Click to Verify)");
                findViewById(R.id.btnVerify).setVisibility(View.VISIBLE);
                findViewById(R.id.btnVerify).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ProfileActivity.this, "Verification Email Sent", Toast.LENGTH_LONG).show();
                            }
                        });
                    }


                });
            }
        }
    }



    private void saveUserInformation() {


        String displayName = editText.getText().toString();

        if (displayName.isEmpty()) {
            editText.setError("Name required");
            editText.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Display Name Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        if (user != null && profileImageUrl != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        final StorageReference profileImageRef = storage.child("Profile Pictures/" + uriProfileImage.getLastPathSegment());

        if (uriProfileImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriProfileImage).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return profileImageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        profileImageUrl = downloadUri.toString();

                    } else {
                        Toast.makeText(ProfileActivity.this, "Upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return adbt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }
}
