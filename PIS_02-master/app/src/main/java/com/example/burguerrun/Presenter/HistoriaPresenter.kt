package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.burguerrun.Model.IngredientCreator
import com.example.burguerrun.Model.Level
import com.example.burguerrun.Model.Player
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream

class HistoriaPresenter() : Presenter{
    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity
    //We use de creator and level for generate level before playing it
    var creator = IngredientCreator()
    var lvl = Level()
    lateinit  var plyr: Player;//=Singletons.plyr
    init{

    }


    override fun setView(view: View){
        this.v = view;
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

    //choose the right image_historia
    fun selectImage(nivell:String , image_historia:ImageView): ImageView {

        when (nivell) {
            "Guanyat" -> image_historia.setImageResource(R.drawable.historia_nivell4_final)

            "Nivell 2" -> image_historia.setImageResource(R.drawable.historia_nivell2)

            "Nivell 3" -> image_historia.setImageResource(R.drawable.historia_nivell3)

            "Nivell 4" -> image_historia.setImageResource(R.drawable.historia_nivell4)
        }

        return image_historia
    }

    //We create level reading .txt and create their enemies
    fun createLevel(nivell:String){
        var iS: InputStream? = null
        //reading level file
        when(nivell){
            "Nivell 1"->{
                iS = v.resources.assets.open("lvl1.txt")
            }
            "Nivell 2"->{
                iS = v.resources.assets.open("lvl2.txt")
            }
            "Nivell 3"->{
                iS = v.resources.assets.open("lvl3.txt")
            }
            "Nivell 4"->{
                iS = v.resources.assets.open("lvl4.txt")
            }

        }
        //generating level data based on read
        var file:File = File(""+v.context.filesDir+"\\tmp.txt")
        file.createNewFile()
        FileUtils.copyInputStreamToFile(iS,file)
        lvl.readLvlfromFile(file,8,15)
        file.delete()
        Singletons.level = lvl
        plyr=com.example.burguerrun.Model.Player(lvl.startTile.down!!, 24)
        Singletons.plyr=plyr
        //creating decoration level and enemies
        when(nivell){
            "Nivell 1"->{
                lvl.stage = 1
                lvl.num = 16
                lvl.ingredients.add(creator.createIngredient("cheese", 2, 2, 3))
                lvl.ingredients.add(creator.createIngredient("cheese", 17, 2, 4))
                lvl.ingredients.add(creator.createIngredient("cheese", 7, 8, 5))
                lvl.ingredients.add(creator.createIngredient("cheese", 16, 18, 7))
                lvl.ingredients.add(creator.createIngredient("cheese", 5, 21, 10))
                lvl.muro = R.drawable.muro_1
                lvl.suelo = R.drawable.terra_1
                lvl.num1 = R.drawable.num_1
                lvl.num2 = R.drawable.num_6

                plyr.skinSelector(1)


            }
            "Nivell 2"->{
                lvl.stage = 2
                lvl.num = 21
                lvl.ingredients.add(creator.createIngredient("lettuce", 1, 3, 2))
                lvl.ingredients.add(creator.createIngredient("lettuce", 16, 2, 6))
                lvl.ingredients.add(creator.createIngredient("lettuce", 6, 11, 7))
                lvl.ingredients.add(creator.createIngredient("lettuce", 1, 24, 11))
                lvl.ingredients.add(creator.createIngredient("lettuce", 16, 24, 15))
                lvl.muro = R.drawable.muro_2
                lvl.suelo = R.drawable.terra_2
                lvl.num1 = R.drawable.num_2
                lvl.num2 = R.drawable.num_1
                plyr.skinSelector(2)
            }
            "Nivell 3"->{
                lvl.stage = 3
                lvl.num = 18
                lvl.ingredients.add(creator.createIngredient("tomato", 2, 3, 3))
                lvl.ingredients.add(creator.createIngredient("tomato", 16, 2, 4))
                lvl.ingredients.add(creator.createIngredient("tomato", 9, 11, 7))
                lvl.ingredients.add(creator.createIngredient("tomato", 1, 24, 10))
                lvl.ingredients.add(creator.createIngredient("tomato", 16, 24, 12))
                lvl.muro = R.drawable.muro_3
                lvl.suelo = R.drawable.terra_3
                lvl.num1 = R.drawable.num_1
                lvl.num2 = R.drawable.num_8
                plyr.skinSelector(3)
            }
            "Nivell 4"->{
                lvl.stage = 4
                lvl.num = 24
                lvl.ingredients.add(creator.createIngredient("cheese", 1, 2, 5))
                lvl.ingredients.add(creator.createIngredient("lettuce", 16, 2, 6))
                lvl.ingredients.add(creator.createIngredient("lettuce", 11, 12, 11))
                lvl.ingredients.add(creator.createIngredient("tomato", 2, 24, 10))
                lvl.ingredients.add(creator.createIngredient("tomato", 16, 24, 10))
                lvl.muro = R.drawable.muro_4
                lvl.suelo = R.drawable.terra_4
                lvl.num1 = R.drawable.num_2
                lvl.num2 = R.drawable.num_4
                plyr.skinSelector(4)
            }

        }
    }

    override fun showMessage(msg:String){//shortcut for toasts
        Toast.makeText(ctxt,msg, Toast.LENGTH_LONG).show()
    }

}
