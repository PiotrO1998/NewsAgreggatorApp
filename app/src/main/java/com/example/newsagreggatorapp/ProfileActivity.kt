package com.example.newsagreggatorapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


/**
 * This class represent profile activity, thorugh this activity user can log in or signed in
 *
 * @author Piotr Obara
 * 967793
 */
class ProfileActivity : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)

        val email = findViewById<EditText>(R.id.input_email)
        val password = findViewById<EditText>(R.id.input_password)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener { view ->
            mAuth.signInWithEmailAndPassword(
                email.text.toString(),
                password.text.toString()
            )
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        System.out.println(email.text.toString())
                        System.out.println(password.text.toString())
                        val user = mAuth.currentUser
                        val intent = Intent(this, LoggedInActivity::class.java)
                        intent.putExtra("id", user?.email)
                        startActivity(intent)
                    } else {
                        System.out.println(mAuth.app)
                        System.out.println(email.text.toString())
                        System.out.println(password.text.toString())
                        closeKeyBoard()
                        message(
                            view,
                            "Error when signing in"
                        )
                    }
                }
        }

        val buttonSigin = findViewById<Button>(R.id.buttonSignin)
        buttonSigin.setOnClickListener { view ->
            mAuth.createUserWithEmailAndPassword(
                email.text.toString(),
                password.text.toString()
            )
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        System.out.println(email.text.toString())
                        System.out.println(password.text.toString())
                        val user = mAuth.currentUser
                        val intent = Intent(this, LoggedInActivity::class.java)
                        intent.putExtra("id", user?.email)
                        startActivity(intent)
                    } else {
                        System.out.println(mAuth.app)
                        System.out.println(email.text.toString())
                        System.out.println(password.text.toString())
                        closeKeyBoard()
                        message(
                            view,
                            "Error when signing in"
                        )
                    }
                }
        }
    }

    private fun message(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val i = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            i.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    public fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }

}

