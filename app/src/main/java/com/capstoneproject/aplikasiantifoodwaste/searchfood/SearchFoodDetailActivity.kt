package com.capstoneproject.aplikasiantifoodwaste.searchfood

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.capstoneproject.aplikasiantifoodwaste.databinding.ActivitySearchFoodDetailBinding
import com.capstoneproject.aplikasiantifoodwaste.home.HomeActivity
import com.capstoneproject.aplikasiantifoodwaste.profile.Users
import com.capstoneproject.aplikasiantifoodwaste.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SearchFoodDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchFoodDetailBinding
    private lateinit var userRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val b64image = intent.getStringExtra(Extra_Image)
        val foodName = intent.getStringExtra(Extra_FoodName)
        val stock = intent.getStringExtra(Extra_Stock)
        val desc = intent.getStringExtra(Extra_Description)
        val idPembagi = intent.getStringExtra(Extra_Id)!!
        val idMakanan = intent.getStringExtra(Extra_Id_Makanan)

        userRef = FirebaseDatabase.getInstance().getReference("Users").child(idPembagi)
        userRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("User", snapshot.getValue(Users::class.java).toString())

                var users: Users? = snapshot.getValue(Users::class.java)
                binding.tvGiverName.text = users?.name
                binding.ivGiver.setImageBitmap(base64ToBitmap(users?.photo))
                binding.tvGiverTelp.text = users?.telp
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        binding.apply {
            ivMakanan.setImageBitmap(base64ToBitmap(b64image))
            tvNamaMakanan.text = foodName
            tvStok.text = stock
            tvDeskripsi.text = desc
        }

        if (stock != null) {
            if(stock.toInt()==0){
                binding.btnAmbilMakananAvailable.visibility = View.GONE
                binding.btnAmbilMakananUnavailable.visibility = View.VISIBLE
            }
        }

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(idPembagi)){
            binding.btnAmbilMakananAvailable.text = "Hapus Makanan"
            binding.btnAmbilMakananUnavailable.text = "Hapus Makanan"
        }

        binding.btnAmbilMakananAvailable.setOnClickListener{

            if(FirebaseAuth.getInstance().currentUser?.uid.equals(idPembagi)){
                val mPostReference =
                    idMakanan?.let { it1 ->
                        FirebaseDatabase.getInstance().getReference("Share").child(
                            it1
                        )
                    }
                if (mPostReference != null) {
                    mPostReference.removeValue()
                }
                Toast.makeText(this, "Makanan berhasil dihapus", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Intent(this@SearchFoodDetailActivity, TakeFoodActivity::class.java).also{
                    it.putExtra("EXTRA_ID_MAKANAN", idMakanan )
                    it.putExtra("EXTRA_ID_PEMBAGI", idPembagi)
                    startActivity(it)
                }
            }
        }
        binding.btnAmbilMakananUnavailable.setOnClickListener {
            if(FirebaseAuth.getInstance().currentUser?.uid.equals(idPembagi)){
                val mPostReference =
                    idMakanan?.let { it1 ->
                        FirebaseDatabase.getInstance().getReference("Share").child(
                            it1
                        )
                    }
                if (mPostReference != null) {
                    mPostReference.removeValue()
                }
                Toast.makeText(this, "Makanan berhasil dihapus", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    private fun base64ToBitmap(b64: String?): Bitmap {
        val base64 = Base64.decode(b64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(base64, 0, base64.size)
    }

    companion object {
        const val Extra_Image = "extra_image"
        const val Extra_FoodName = "extra_foodname"
        const val Extra_Stock = "extra_stock"
        const val Extra_Description = "extra_description"
        const val Extra_Id = "extra_id"
        const val Extra_Id_Makanan = "extra_id_makanan"
    }
}