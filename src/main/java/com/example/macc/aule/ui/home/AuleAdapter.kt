package com.example.macc.aule.ui.home

import android.content.Context
import android.graphics.Color
import android.provider.Settings.System.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.macc.aule.R
import kotlinx.android.synthetic.main.item.view.*
import org.json.JSONArray
import org.json.JSONObject

class AuleAdapter (c: Context?,dest:String) : RecyclerView.Adapter<AuleAdapter.ViewHolder> ()
{

    private var data: JSONArray? =null
    private var wh = dest
    private var context :Context? = null

    init {

        this.context=c

        val queue = Volley.newRequestQueue(c)
        val url = "http://www.diag.uniroma1.it/pannello/?q=export_json"


        val stringRequest = JsonArrayRequest(

            Request.Method.GET, url, null,

            Response.Listener<JSONArray> { r ->
                data = JSONArray(r.toString())
                notifyDataSetChanged()
                },

            Response.ErrorListener { error: VolleyError? ->  Log.e("AULE", "/get request ERROR!: $error")})

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Log.d("AULE","getCreateViewHolder called...")
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.dummyitem, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //Log.d("AULE","getItemCount called...")

        if (data==null) return  0
            return data!!.length()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.who.text=JSONObject(data!![position].toString()).getString("Descrizione")
        holder.itemView.time.text=  JSONObject(data!![position].toString()).getString("ora_inizio")+
                                    JSONObject(data!![position].toString()).getString("minuti_inizio")+ " "+
                                    JSONObject(data!![position].toString()).getString("ora_fine")+
                                    JSONObject(data!![position].toString()).getString("minuti_fine")
        holder.itemView.aula.text=JSONObject(data!![position].toString()).getString("nome_aula")

//        val now = SimpleDateFormat("HH").format(Date()).toInt().toString()
        val hourE = JSONObject(data!![position].toString()).getString("ora_fine").toInt()
        val hourS = JSONObject(data!![position].toString()).getString("ora_inizio").toInt()


        holder.itemView.aula.setTextColor(Color.BLACK)
        //Outline only events in the afternoon
        if ((wh==context!!.getString(R.string.title_Afternoon)) and ( hourE >= 14)) {
            holder.itemView.aula.setTextColor(Color.RED)
         }

        if ((wh==context!!.getString(R.string.title_Morning)) and ( hourS < 14) ) {
            holder.itemView.aula.setTextColor(Color.RED)
        }

    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}