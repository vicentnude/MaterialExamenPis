package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.burguerrun.Model.LevelTile
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class RankingPresenter:Presenter {

    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity
    var names = ArrayList<String>()
    var points = ArrayList<ArrayList<Long>>()


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

    override fun showMessage(text:String) {
        Toast.makeText(ctxt, text, Toast.LENGTH_LONG).show()
    }

    fun getDBdata():Boolean{
        val read: Task<QuerySnapshot> = Singletons.db.collection("users")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    for (doc: QueryDocumentSnapshot in it.result!!){
                        //reading firebase users data
                        val uname = doc.get("name").toString()
                        var upoints = ArrayList<Long>(4)
                        upoints.add(doc.get("point1") as Long)
                        upoints.add(doc.get("point2") as Long)
                        upoints.add(doc.get("point3") as Long)
                        upoints.add(doc.get("point4") as Long)
                        names.add(uname)
                        points.add(upoints)
                    }
                }
            }
        return true
    }

    fun update(){
        points.clear()
        names.clear()
        getDBdata()
    }

    fun createList(lvl:Int){
        var list: ListView = v.findViewById(R.id.listview)
        var itemDataList:ArrayList<Map<String,Any>> = ArrayList<Map<String,Any>>()
        var image = BitmapFactory.decodeResource(ctxt.resources, R.drawable.burger)
        for (i in 0..names.size - 1){
            var listItemMap = HashMap<String,Any>()
            listItemMap.put("user",names[i])
            listItemMap.put("points", points[i][lvl])
            itemDataList.add(listItemMap)
        }

        itemDataList.sortBy { it.get("points") as Long}
        itemDataList.reverse()


        var adapter = object : SimpleAdapter(ctxt,itemDataList,R.layout.rankingitem,
            arrayOf("user","points","position"), intArrayOf(R.id.rankingtext1,R.id.rankingtext2,R.id.rankingpos)){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var inflater = actv.layoutInflater
                var item = inflater.inflate(R.layout.rankingitem,null)

                var text1 = item.findViewById<TextView>(R.id.rankingtext1)
                var text2 = item.findViewById<TextView>(R.id.rankingtext2)
                var image1 = item.findViewById<ImageView>(R.id.rankingpos)
                text1.text = itemDataList[position]["user"].toString()
                text2.text = itemDataList[position]["points"].toString()
                image1.setImageResource(selectId(position))

                return item
            }
        }

        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            val clickItem = (position+1).toString()+"  :  "+itemDataList[position]["user"].toString()
            Toast.makeText(ctxt,clickItem,Toast.LENGTH_LONG).show()
        }
    }
    fun selectId(position:Int):Int{
        var id = 0
        when(position){
            0-> id = R.drawable.ic_ranking1
            1-> id = R.drawable.ic_ranking2
            2-> id = R.drawable.ic_ranking3
            3-> id = R.drawable.ic_ranking4
            4-> id = R.drawable.ic_ranking5
            5-> id = R.drawable.ic_ranking6
            6-> id = R.drawable.ic_ranking7
            7-> id = R.drawable.ic_ranking8
            8-> id = R.drawable.ic_ranking9
            9-> id = R.drawable.ic_ranking10
            else-> id = R.drawable.ic_ranking11
        }
        return id
    }
}