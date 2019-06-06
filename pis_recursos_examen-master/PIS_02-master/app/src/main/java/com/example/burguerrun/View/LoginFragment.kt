package com.example.burguerrun.View



import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.fragment_login.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment(),GoogleApiClient.OnConnectionFailedListener{
    companion object {
        private val PERMISSION_CODE = 9999
    }

    val loginPresenter = Singletons.loginPresenter

    override fun onConnectionFailed(p0:ConnectionResult){
        loginPresenter.showMessage(""+p0.errorMessage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        //init Presenter
        loginPresenter.setView(view)
        loginPresenter.setFragmentActivity(activity!!)
        loginPresenter.setContext(context!!)
        loginPresenter.setFailedListener(this)
        loginPresenter.setConta(container!!)

        view.loginButton.setOnClickListener(View.OnClickListener {
            loginPresenter.localSignin(view.usernameText.text.toString(),view.passwordText.text.toString())
        })

        //sigin options
        loginPresenter.setOptions()
        //configure google
        loginPresenter.configureGoogle()

        //signIn
        view.google_button.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(loginPresenter.mGoogleApiClient)
            activity!!.startActivityForResult(intent, PERMISSION_CODE)

        }
        //prepare dialog
        loginPresenter.setAlertDialog()

        return view
    }




}



