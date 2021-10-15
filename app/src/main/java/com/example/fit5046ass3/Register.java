package com.example.fit5046ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.fit5046ass3.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        //set the email icon size
        Drawable drawable=getResources().getDrawable(R.drawable.button1);
        drawable.setBounds(0,0,70,70);
        binding.username1.setCompoundDrawables(drawable,null,null,null);

        //set the password icon size
        Drawable drawable1=getResources().getDrawable(R.drawable.button2);
        drawable1.setBounds(0,0,70,70);
        binding.password.setCompoundDrawables(drawable1,null,null,null);
        binding.confirmpassword.setCompoundDrawables(drawable1,null,null,null);

        binding.signup1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String password = binding.password.getText().toString().trim();
                String email = binding.username1.getText().toString().trim();
                String confirmpw=binding.confirmpassword.getText().toString().trim();
                //check username null?
                if (email.isEmpty()) {
                    binding.username1.setError("Email should not be null");
                    binding.username1.setText("");
                    return ;
                }

                //check valid username?
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.username1.setError("Invalid email");
                    binding.username1.requestFocus();
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

                //check confirmpassword null?
                if (confirmpw.isEmpty()) {
                    binding.confirmpassword.setError("Password should not be null");
                    binding.confirmpassword.setText("");
                    return;
                }

                //confirmpw is equal to pw
                if(!confirmpw.equals(password)){
                    binding.confirmpassword.setError("Passwords are not the same!");
                    binding.confirmpassword.setText("");
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       Toast.makeText(Register.this,"Register successfully",Toast.LENGTH_LONG).show();
                       //turn back to home page
                        Intent reIntent =getIntent();
                        reIntent.putExtra("email",email);
                        reIntent.putExtra("password",password);
                        setResult(RESULT_OK,reIntent);
                        finish();
                       //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                   } else{
                       Toast.makeText(Register.this,"Registration failed",Toast.LENGTH_LONG).show();
                   }
                });
            }
        });
    }
}