package com.example.projectt

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.text.TextUtils
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import android.content.Intent





class LoginActivity : AppCompatActivity(){


    public override fun onStart() {
        super.onStart()

        setContentView(R.layout.login_page)

        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setLogo(R.drawable.elma);
        supportActionBar?.setDisplayUseLogoEnabled(true);

        val auth: FirebaseAuth = Firebase.auth

        lateinit var email: String
        lateinit var password: String

        val login_button = findViewById<Button>(R.id.login_button)
        login_button.setOnClickListener {
            val email_text = findViewById<EditText>(R.id.email_edit_text)
            email = email_text.text.toString()
            val password_text = findViewById<EditText>(R.id.password_edit_text)
            password = password_text.text.toString()

            if(!isValidEmail(email)){
                Toast.makeText(baseContext, "Please enter valid email",
                    Toast.LENGTH_SHORT).show()
                onStart()
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(baseContext, "Access granted",
                            Toast.LENGTH_SHORT).show()
                        val switchActivityIntent = Intent(this, MainPageActivity::class.java)
                        startActivity(switchActivityIntent)
                    }else{
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Access denied.",
                            Toast.LENGTH_SHORT).show()
                        onStart()
                    }
                }
        }

        val sign_up_button = findViewById<Button>(R.id.singup_button)
        sign_up_button.setOnClickListener {
            val email_text = findViewById<EditText>(R.id.email_edit_text)
            email = email_text.text.toString()
            val password_text = findViewById<EditText>(R.id.password_edit_text)
            password = password_text.text.toString()

            if(!isValidEmail(email)){
                Toast.makeText(baseContext, "Please enter valid email",
                    Toast.LENGTH_SHORT).show()
                onStart()
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(baseContext, "You are successfully signed-up.",
                            Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "User sign-up is failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


    private fun navigateToMainPage(user: FirebaseUser?) {
        // Implement this for user
    }

}