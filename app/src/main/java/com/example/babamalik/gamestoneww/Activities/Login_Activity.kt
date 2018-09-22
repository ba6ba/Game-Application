package com.example.babamalik.gamestoneww.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.example.babamalik.gamestoneww.Activities.MainActivity
import com.example.babamalik.gamestoneww.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_login_.*
import kotlinx.android.synthetic.main.signup_fragment.*

class Login_Activity : AppCompatActivity() {

    private var auth : FirebaseAuth?=null
    private var listener : FirebaseAuth.AuthStateListener?=null
    private var user : FirebaseUser?=null

    //for Signup
    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var firebaseStorage: StorageReference? = null

    private var choice : String?=null
    private var progressDialog : ProgressDialog?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        auth = FirebaseAuth.getInstance()
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener { auth ->
            user = auth.currentUser
            if (user != null) {
                Toast.makeText(this@Login_Activity, "Signed in", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@Login_Activity, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this@Login_Activity, "Not Signed in", Toast.LENGTH_LONG).show()
            }
        }

        setOnClickListeners()
    }


    private fun setOnClickListeners() {

        login_button.setOnClickListener {
            if ((!TextUtils.isEmpty(mailAddress_Login.getText().toString()))
                    && (!TextUtils.isEmpty(mailAddress_Password.getText().toString()))) {
                val emailfield = mailAddress_Login.getText().toString()
                val passwordfield = mailAddress_Password.getText().toString()
                DoLogin(emailfield, passwordfield)
            }
            else
            {

            }
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        signup_button.setOnClickListener {
            Signup()
        }

    }

    private fun Signup() {
        setContentView(R.layout.signup_fragment)
        setupFirebase()
        Signup_OK.setOnClickListener {
            setupFields()
        }
    }

    private fun setupFields() {
        val fullName = userNameSignupText.getText().toString().trim({ it <= ' ' })
        val Email = EmailSignupText.getText().toString().trim({ it <= ' ' })
        val paswrod = password_edit_text_signup.getText().toString().trim({ it <= ' ' })

        if(choice_xbox.isSelected){
            choice = "XBOX"
        }
        else if(choice_pc.isSelected){
            choice = "PC"
        }
        else {
            choice = "PS"
        }

        if ((!TextUtils.isEmpty(fullName)) && (!TextUtils.isEmpty(Email)) && (!TextUtils.isEmpty(paswrod))) {

            progressDialog?.setMessage("Creating Account")
            progressDialog?.show()

            auth?.createUserWithEmailAndPassword(Email, paswrod)?.addOnSuccessListener { authResult ->
                if (authResult != null) {

                    val userId = auth?.getCurrentUser()?.uid
                    val current_user_db = databaseReference?.child(userId as String)
                    current_user_db?.child("Full Name")?.setValue(fullName)
                    current_user_db?.child("Choice")?.setValue(choice)
                    current_user_db?.child("Email")?.setValue(Email)
                    current_user_db?.child("Password")?.setValue(paswrod)

                    progressDialog?.dismiss()

                    //send users to postlist
                    Toast.makeText(this@Login_Activity, "AAKHRI", Toast.LENGTH_LONG).show()


                }
            }
        }
    }

    private fun setupFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference()?.child("Users")
        //firebaseAuth = FirebaseAuth.getInstance()
        //firebaseStorage = FirebaseStorage.getInstance().reference.child("User-storage")
    }

    private fun DoLogin(emailfield: String, passwordfield: String) {
        auth?.signInWithEmailAndPassword(emailfield, passwordfield)?.addOnCompleteListener(this)
        { task ->
            if (task.isSuccessful) {
                //we in
                Toast.makeText(this@Login_Activity, "isSuccessful", Toast.LENGTH_LONG).show()


            } else {
                Toast.makeText(this@Login_Activity, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
            startActivity(Intent(this@Login_Activity, MainActivity::class.java))
        }

    }



    override fun onStart() {
        super.onStart()
        auth?.addAuthStateListener { listener }
    }

    override fun onStop() {
        super.onStop()
        if(listener!=null){
            auth?.removeAuthStateListener { listener }
        }
    }


}


