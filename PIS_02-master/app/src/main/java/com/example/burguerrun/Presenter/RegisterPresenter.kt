package com.example.burguerrun.Presenter


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.burguerrun.Model.User
import com.example.burguerrun.Singletons
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.io.File
import java.util.jar.Manifest


class RegisterPresenter():Presenter{
    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity

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

    fun register(username:String,mail:String,password:String,image:Bitmap){//register user if does not exists
        if (!exists(mail)){
            Singletons.database.addUser(username,mail,password,image,BooleanArray(4),LongArray(4))
            showMessage("Register Succesful!")
            fragact.onBackPressed()
        }
        else{
            showMessage("Already Registered!")
        }
    }

    override fun showMessage(msg:String){
        Toast.makeText(ctxt,msg, Toast.LENGTH_LONG).show()
    }

    fun setImage(realpath:String) {
        var urifromPath = Uri.fromFile(File(realpath))
        var bitmap = BitmapFactory.decodeStream(ctxt.contentResolver.openInputStream(urifromPath))
        v.registerImage.setImageBitmap(bitmap)
    }

    fun exists(mail:String):Boolean{//check if user exists
        val tmp = Singletons.database.users
        for (u:User in tmp){
            if (u.email.equals(mail)){
                return true
            }
        }
        return false
    }

    fun openGallery(){
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        fragact.startActivityForResult(intent,0)
    }

    fun getRealPath(context: Context,uri: Uri):String{
        var filepath = ""
        var wholeID = DocumentsContract.getDocumentId(uri)
        var id = wholeID.split(":")[1]
        var column = arrayOf(MediaStore.Images.Media.DATA)
        var sel = MediaStore.Images.Media._ID + "=?"
        var cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,column,sel,
            arrayOf(id),null)

        var columnIndex = cursor.getColumnIndex(column[0])

        if (cursor.moveToFirst()){
            filepath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filepath
    }

    fun checkPermissionsRead(context: Context):Boolean{
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(context,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    showDialog("External storage",context,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                else{
                    ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),123)
                }
                return false
            }
            else{
                return true
            }
        }
        else{
            return true
        }
    }


    fun showDialog(msg:String,context: Context,permission: String){
        var alertbuilder = AlertDialog.Builder(context)
        alertbuilder.setCancelable(true)
        alertbuilder.setTitle("Permission necessary")
        alertbuilder.setMessage(msg + " permission is necessary")
        alertbuilder.setPositiveButton(android.R.string.yes,DialogInterface.OnClickListener { dialog, which ->
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission),123)
        })
        var alert = alertbuilder.create()
        alert.show()
    }
}
