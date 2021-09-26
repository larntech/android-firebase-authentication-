package net.larntech.firebase_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import net.larntech.firebase_auth.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding;
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root;
        setContentView(view)
        initData();
    }


    private fun initData(){
        auth = FirebaseAuth.getInstance();
        clickListener();
    }

    private fun clickListener(){
        binding.llNewUser.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            getUserData()
        }

    }

    private fun getUserData(){
        var email = binding.etEmail.text.toString();
        var password = binding.etPassword.text.toString();

        if(email.isNotEmpty() && password.isNotEmpty()){
            //auth user
            authUser(email,password)
        }else{
            Toast.makeText(this," All inputs required ...",Toast.LENGTH_LONG).show()

        }

    }

    private fun authUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                checkResult(it.isSuccessful)
            }
    }

    private fun checkResult(isSuccess: Boolean){
        if(isSuccess){
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }else{
            Toast.makeText(this," Authentication failed ...",Toast.LENGTH_LONG).show()
        }
    }
}