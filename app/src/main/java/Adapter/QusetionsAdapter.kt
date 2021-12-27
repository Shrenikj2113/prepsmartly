package Adapter

import activity.questionslist
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.prepsmartly.R
import kotlinx.android.synthetic.main.recycleritems.view.*
import models.Questions

class QusetionsAdapter(val context: Context, val question: MutableList<Questions>): RecyclerView.Adapter<QusetionsAdapter.questionviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): questionviewholder {
        val inflator=LayoutInflater.from(parent.context)
        val view=inflator.inflate(R.layout.recycleritems,parent,false)
        return questionviewholder(view)
    }

    override fun onBindViewHolder(holder: questionviewholder, position: Int) {
        holder.txt.text=question[position].number
    }

    override fun getItemCount(): Int {
       return question.size
    }

    inner class questionviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val txt=itemView.findViewById<Button>(R.id.textscrollid)

        init {
            itemView.textscrollid.setOnClickListener {
                val intent= Intent(itemView.context, questionslist::class.java)
                intent.putExtra("shrenu",txt.text)
                ContextCompat.startActivity(itemView.context, intent, Bundle.EMPTY)
            }
        }

    }
}

