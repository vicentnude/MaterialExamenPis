package org.manumatnez.recursosexamenpis

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.gson.Gson
import org.manumatnez.recursosexamenpis.db.MAIL
import org.manumatnez.recursosexamenpis.db.NAME
import org.manumatnez.recursosexamenpis.db.User
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var userListener: ListenerRegistration

    private lateinit var user : User

    private lateinit var logoutButton : Button
    private lateinit var savefbButton : Button

    private lateinit var unametf : EditText
    private lateinit var umailtf : EditText

    private lateinit var savespButton : Button
    private lateinit var loadspButton : Button

    private lateinit var playSnakeButton : Button

    private lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)

        mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        supportActionBar!!.title = mAuth.currentUser!!.displayName

        unametf = findViewById(R.id.nametext)
        umailtf = findViewById(R.id.mailtext)

        userListener =
            db.collection(USERS).document(mAuth.currentUser!!.uid).addSnapshotListener { docSnapshot, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed.", exception)
                }
                if (docSnapshot != null && docSnapshot.exists()) {
                    user = docSnapshot.toObject(User::class.java)!!
                    unametf.setText(user.name)
                    umailtf.setText(user.mail)
                    supportActionBar!!.title = user.name
                }
            }

        logoutButton = findViewById(R.id.logoutBtn)
        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            // LOGOUT
            mAuth.signOut()
            mGoogleSignInClient.signOut()
            startActivity(intent)
        }

        savefbButton = findViewById(R.id.savefbBtn)
        savefbButton.setOnClickListener {
            db.collection(USERS).document(mAuth.currentUser!!.uid).update(NAME, unametf.text.toString())
            db.collection(USERS).document(mAuth.currentUser!!.uid).update(MAIL, umailtf.text.toString())
            Toast.makeText(this, getString(R.string.user_mod_fb),Toast.LENGTH_SHORT).show()
        }

        savespButton = findViewById(R.id.savespBtn)
        savespButton.setOnClickListener {
            val editor = sp.edit()
            val gson = Gson()
            val strUser = gson.toJson(User(unametf.text.toString(), umailtf.text.toString()))
            editor.putString("USER", strUser)
            editor.apply()
            Toast.makeText(this, getString(R.string.user_save_sp),Toast.LENGTH_SHORT).show()
        }

        loadspButton = findViewById(R.id.loadspBtn)
        loadspButton.setOnClickListener {
            val jsonUser = sp.getString("USER", "")
            val userSp = Gson().fromJson<User>(jsonUser, User::class.java)
            try {
                unametf.setText(userSp.name)
                umailtf.setText(userSp.mail)
                Toast.makeText(this, getString(R.string.user_loaded),Toast.LENGTH_SHORT).show()
            } catch (e : Exception) {
                Toast.makeText(this, getString(R.string.no_saved_values),Toast.LENGTH_SHORT).show()
            }
        }

        val intent = Intent(this, SnakeActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)

        playSnakeButton = findViewById(R.id.playSnakeBtn)
        playSnakeButton.setOnClickListener {
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        userListener.remove()
    }
}
