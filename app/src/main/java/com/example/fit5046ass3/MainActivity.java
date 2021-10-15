package com.example.fit5046ass3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fit5046ass3.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        //set the email icon size
        Drawable drawable=getResources().getDrawable(R.drawable.button1);
        drawable.setBounds(0,0,70,70);
        binding.username.setCompoundDrawables(drawable,null,null,null);

        //set the password icon size
        Drawable drawable1=getResources().getDrawable(R.drawable.button2);
        drawable1.setBounds(0,0,70,70);
        binding.password.setCompoundDrawables(drawable1,null,null,null);

        //hide and show password
        binding.hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.hide.getText().toString().equals("Hide")){
                    binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.hide.setText("Show");
                }
                else if(binding.hide.getText().toString().equals("Show"))
                {
                    binding.hide.setText("Hide");
                    binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        //turn to register page
        binding.signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivityForResult(intent,1);
            }
        });

        //click the signin button and go to home page
        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.password.getText().toString().trim();
                String email = binding.username.getText().toString().trim();

                //check username null?
                if (email.isEmpty()) {
                    binding.username.setError("Email should not be null");
                    binding.username.setText("");
                    return ;
                }

                //check valid username?
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.username.setError("Invalid email");
                    binding.username.requestFocus();
                    return;
                }

                //check password null?
                if (password.isEmpty()) {
                    binding.password.setError("Password should not be null");
                    binding.password.requestFocus();
                    return;
                }
                //check password length >7, include at least 1 uppercase letter,1 lowercase letter and 1 digit
                boolean upperFlag = false;
                boolean lowerFlag = false;
                boolean numberFlag = false;

                if (!password.isEmpty() && password.length() < 8) {
                    binding.password.setError("The length of email should be greater than 7");
                    binding.password.setText("");
                    return ;
                } else {
                    for (int i = 0; i < password.length(); i++)
                    {
                        char ch = password.charAt(i);
                        if (!password.isEmpty() && Character.isUpperCase(ch))
                            upperFlag = true;
                        else if (!password.isEmpty() && Character.isLowerCase(ch))
                            lowerFlag = true;
                        else if (!password.isEmpty() && Character.isDigit(ch))
                            numberFlag = true;
                    }
                    if (!(upperFlag && lowerFlag && numberFlag)) {
                        binding.password.setError("Password should include at least 1 uppercase letter,1 lower case letter and 1 digit.");
                        binding.password.setText("");
                        return ;
                    }
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(a -> {
                   if(a.isSuccessful()){
                       Toast.makeText(MainActivity.this,"Login successfully",Toast.LENGTH_LONG).show();
                       Intent intent =new Intent(MainActivity.this, Home.class);
                        intent.putExtra("email",email);
                       startActivity(intent);
                   } else{
                       Toast.makeText(MainActivity.this,"Login fail",Toast.LENGTH_LONG).show();
                   }
                });
            }
        });


    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String email=data.getStringExtra("email");
                String password=data.getStringExtra("password");
                binding.username.setText(email);
                binding.password.setText(password);
            }
        }
    }

}