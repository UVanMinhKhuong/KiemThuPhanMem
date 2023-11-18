package com.example.myapplication.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.UserModel;
import com.example.myapplication.service.UserService;
import com.example.myapplication.utility.BCryptUtil;

public class RegisterActivity extends AppCompatActivity {
    private UserService userService;
    private EditText edtUsername, edtPassword, edtEmail, edtFirstName, edtLastName;
    private TextView txtLogin;

    private TextView errorEmail, errorUsername, errorPassword;
    private Button btnRegister;

    public RegisterActivity() {
        this.userService = new UserService(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_activity);

        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);

        edtUsername = findViewById(R.id.editTextName);
        edtPassword = findViewById(R.id.editTextPassword);
        edtEmail = findViewById(R.id.editTextEmail);
        edtFirstName = findViewById(R.id.editTextFirstName);
        edtLastName = findViewById(R.id.editTextLastName);

        errorEmail = findViewById(R.id.errorEmail);
        errorUsername = findViewById(R.id.errorUsername);
        errorPassword = findViewById(R.id.errorPassword);

        btnRegister.setOnClickListener(view -> {
            String password = edtPassword.getText().toString();


            boolean validation = validation();
            if (!validation)
                return;

            UserModel user = new UserModel(edtUsername.getText().toString(),
                    password,
                    edtEmail.getText().toString(),
                    edtFirstName.getText().toString(),
                    edtLastName.getText().toString()
            );
            user.setPassword(password);
            userService.insert(user);
            Toast.makeText(RegisterActivity.this, String.format("Chúc mừng bạn đã đăng ký thành công."), Toast.LENGTH_LONG).show();

            startToLoginActivity();
        });


        txtLogin.setOnClickListener(view -> {
            startToLoginActivity();
        });

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    errorUsername.setVisibility(View.VISIBLE);
                    errorUsername.setText("Tên tài khoản không được rỗng.");
                } else {
                    errorUsername.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 0) {
                    errorEmail.setVisibility(View.VISIBLE);
                    errorEmail.setText("Email không được rỗng.");
                } else {
                    errorEmail.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    void startToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public boolean validation() {
        boolean isCorrect = true;

        if (edtUsername.getText().toString().length() == 0) {
            errorUsername.setVisibility(View.VISIBLE);
            errorUsername.setText("Tên đăng nhập không được rỗng.");
            isCorrect = false;
        } else {

            boolean isExistsUsername = userService.isExistsUsername(edtUsername.getText().toString());
            if(isExistsUsername == true ){
                errorUsername.setVisibility(View.VISIBLE);
                errorUsername.setText("Username đã tồn tại trong hệ thống. Vui lòng sử dụng username khác!");
                isCorrect = !isExistsUsername ;
            }
        }

        if (!edtEmail.getText().toString().contains("@")) {
            errorEmail.setVisibility(View.VISIBLE);
            errorEmail.setText("Email không hợp lệ.");
            isCorrect = false;
        }

        if (edtPassword.getText().toString().length() == 0) {
            errorPassword.setVisibility(View.VISIBLE);
            errorPassword.setText("Mật khẩu không được rỗng.");
            isCorrect = false;
        } else {
            if (edtPassword.getText().toString().length() < 6) {
                errorPassword.setVisibility(View.VISIBLE);
                errorPassword.setText("Mật khẩu phải ít nhất 6 ký tự.");
                isCorrect = false;
            }
        }
        return isCorrect;
    }
}
