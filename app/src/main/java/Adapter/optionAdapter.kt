package Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prepsmartly.R
import models.Questions

class optionAdapter(val context: Context, val ques: List<Questions>): RecyclerView.Adapter<optionAdapter.optionviewholder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionviewholder {
        val inflator= LayoutInflater.from(parent.context)
        val view=inflator.inflate(R.layout.optionitem,parent,false)
        return optionviewholder(view)
    }

    override fun onBindViewHolder(holder: optionviewholder, position: Int) {
        val user=ques[position]
     // holder.optiondes.text=ques[position].questions
        holder.optiontxt.text = user.op1
        holder.optiontxt.text = user.op2
        holder.optiontxt.text = user.op3
        holder.optiontxt.text = user.op4
    }

    override fun getItemCount(): Int {
       return ques.size
    }


    inner class optionviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
      var optiontxt=itemView.findViewById<TextView>(R.id.optiontxt)
        var optiondes=itemView.findViewById<TextView>(R.id.descriptionid)
    }

}

