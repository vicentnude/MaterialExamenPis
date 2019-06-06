package com.example.burguerrun.View



import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import kotlinx.android.synthetic.main.fragment_register.view.*
import android.content.Intent





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterFragment : Fragment() {
    companion object {
        val GALLERY = 1
        val CAMERA = 2
    }

    val registerPresenter = Singletons.registerPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        registerPresenter.setView(view)
        registerPresenter.setFragmentActivity(activity!!)
        registerPresenter.setContext(context!!)

        view.registerButton.setOnClickListener(View.OnClickListener {
            if (view.userRegister.text.isEmpty() || view.mailRegister.text.isEmpty() || view.passwordRegister.text.isEmpty()){
                registerPresenter.showMessage("Omple tots els camps!")
            }
            else{
                if (view.passwordRegister.length() < 6){
                    registerPresenter.showMessage("La contrasenya ha de tenir minim 6 caracters")
                }
                else{
                    registerPresenter.register(view.userRegister.text.toString(),view.mailRegister.text.toString(),view.passwordRegister.text.toString(),(view.registerImage.drawable as BitmapDrawable).bitmap)
                    activity!!.onBackPressed()
                }
            }
        })

        view.registerImage.setOnClickListener(View.OnClickListener {
            if (registerPresenter.checkPermissionsRead(context!!)){
                registerPresenter.openGallery()
            }

        })

        return view
    }







}
