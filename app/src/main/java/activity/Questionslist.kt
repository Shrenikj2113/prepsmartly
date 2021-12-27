package activity

import Adapter.QusetionsAdapter
import Adapter.optionAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prepsmartly.databinding.ActivityQuestionslistBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_questionslist.*
import models.Questions
import models.numbering

class questionslist : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var binding: ActivityQuestionslistBinding
    lateinit var adapter: optionAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionslistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberlist: MutableList<numbering> = ArrayList()
        firestore = FirebaseFirestore.getInstance()
        for (i in 1..100) {
            numberlist.add(numbering("$i"))
        }
       // }
        recyclerview.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
       // recyclerview.adapter = QusetionsAdapter(this, numberlist)
        setup()
        getup()
    }

    private fun getup() {
        firestore.collection("question").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val user = documents.toObjects(Questions::class.java)
                    binding.recyclerview.adapter = QusetionsAdapter(this, user)
                }
            }
    }

    @SuppressLint("WrongConstant")
    private fun setup() {
        val joker=intent.getStringExtra("shrenu")
        firestore.collection("question").document("1").get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    val simpletext = documents.getString("questions")
                    binding.descriptionid.text = simpletext
                    val button1 = documents.getString("op1")
                    binding.btn1.text = button1
                    val button2 = documents.getString("op2")
                    binding.btn2.text = button2
                    val button3 = documents.getString("op3")
                    binding.btn3.text = button3
                    val button4 = documents.getString("op4")
                    binding.btn4.text = button4

                }



            }
            .addOnFailureListener {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }

    }
}




