package com.example.burguerrun.Presenter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_layout.view.*
import java.net.URL

class LoginPresenter():Presenter{

    companion object {
        private val PERMISSION_CODE = 9999
    }

    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity
    //Listener for failing connection
    lateinit var onConnectionFailedListener: GoogleApiClient.OnConnectionFailedListener
    //LoginGoogle
    var mGoogleApiClient:GoogleApiClient? = null
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var alertDialog: AlertDialog? = null
    //logged user
    lateinit var logged_mail : String
    lateinit var logged_user : String
    lateinit var logged_password : String
    lateinit var logged_image : Bitmap
    var options:GoogleSignInOptions? = null
    lateinit var cont: ViewGroup


    override fun setView(v: View){
        this.v = v;
    }

    override fun setActivity(activity: Activity) {
        this.actv = activity
    }

    override fun setFragmentActivity(fragmentActivity: FragmentActivity) {
        this.fragact = fragmentActivity
    }

    override fun setContext(context: Context) {
        this.ctxt = context
    }

    fun setFailedListener(listener:GoogleApiClient.OnConnectionFailedListener){
        onConnectionFailedListener = listener
    }

    fun setConta(con:ViewGroup){//container
        cont = con
    }



    fun setOptions(){//request google gmail
        if (options == null) {
            options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(fragact.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        }
    }

    fun configureGoogle(){//configure api
        if (mGoogleApiClient == null){
            mGoogleApiClient = GoogleApiClient.Builder(ctxt!!)
                .enableAutoManage(fragact,onConnectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options!!)
                .build()
            mGoogleApiClient!!.connect()
        }

    }

    fun setAlertDialog(){//wait dialog
        if (alertDialog == null) {
            alertDialog = SpotsDialog.Builder()
                .setContext(ctxt)
                .setMessage("Please wait...")
                .setCancelable(false)
                .build()
        }
    }

    fun validateSignInGoogle(requestCode: Int, resultCode: Int, data: Intent?){//validate users gmail with google api
        if(requestCode == PERMISSION_CODE){
            val result: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            val a = result.status.statusCode
            if (result.isSuccess){//sigin success
                val account: GoogleSignInAccount? = result.signInAccount
                val idToken = account!!.idToken
                val credential = GoogleAuthProvider.getCredential(idToken,null)
                firebaseAuthWithGoogle(credential)//getting user data
                showMessage("Login Successful, please wait!")
            }
            else{
                showMessage("Login Unsuccessful")
                Log.d("EDMT_ERROR","Login Unsuccessfull")
            }
        }
    }

    fun firebaseAuthWithGoogle(credential: AuthCredential?){//using database
        firebaseAuth!!.signInWithCredential(credential!!)
            .addOnSuccessListener {
                Singletons.connection = true
                //getting user info
                logged_mail = it.user.email!!
                logged_user = it.user.displayName!!
                logged_password = ""
                val url = URL(it.user.photoUrl.toString())
                logged_image = BitmapFactory.decodeStream(url.openConnection() .getInputStream())
                //creating user at DataBase
                Singletons.database.addUser(logged_user,logged_mail,logged_password,logged_image, BooleanArray(4),LongArray(4))
                Singletons.database.setUserGoogle(logged_mail)
                //Loading header
                changeHeader()
                //Disconnecting
                mGoogleApiClient!!.stopAutoManage(fragact)
                mGoogleApiClient!!.disconnect()
                mGoogleApiClient = null
                options = null
                alertDialog = null
                fragact.onBackPressed()
            }
            .addOnFailureListener {
                showMessage(""+it.message)
            }
    }

    override fun showMessage(msg:String){//shortcut for toasts
        Toast.makeText(ctxt,msg, Toast.LENGTH_LONG).show()
    }

    fun changeHeader(){//updating users header
        val hv = fragact.navigationView.inflateHeaderView(R.layout.header_layout)
        hv.mailheader.text = logged_mail
        hv.userheader.text = logged_user
        hv.picheader.setImageBitmap(logged_image)
        fragact.navigationView.removeHeaderView(fragact.navigationView.getHeaderView(0))
    }

    fun localSignin(mail:String,password:String){//local signin without google
            Singletons.database.setUserLocal(mail,password)
        if (Singletons.database.currentUser != null){
            showMessage("Login Successful, please wait!")
            Singletons.connection = true
            logged_mail = Singletons.database.currentUser!!.email
            logged_user = Singletons.database.currentUser!!.username
            logged_image = Singletons.database.currentUser!!.avatar
            changeHeader()
            fragact.onBackPressed()
        }
        else{
            showMessage("Loggin Unsuccesful!")
        }
    }


}
