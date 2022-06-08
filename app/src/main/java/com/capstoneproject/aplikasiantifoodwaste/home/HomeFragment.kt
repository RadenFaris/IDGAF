package com.capstoneproject.aplikasiantifoodwaste.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.aplikasiantifoodwaste.databinding.FragmentHomeBinding
import com.capstoneproject.aplikasiantifoodwaste.scan.FoodScanActivity
import com.capstoneproject.aplikasiantifoodwaste.scan.Storage
import com.capstoneproject.aplikasiantifoodwaste.searchfood.SearchFoodListActivity
import com.capstoneproject.aplikasiantifoodwaste.share.ShareActivity
import com.capstoneproject.aplikasiantifoodwaste.storage.StorageAdapter
import com.capstoneproject.aplikasiantifoodwaste.tips.TipsActivity
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var database : DatabaseReference
    private lateinit var listPenyimpananHomeRecyclerView: RecyclerView
    private lateinit var listPenyimpananHomeArrayList: ArrayList<Storage>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivScan.setOnClickListener {
            startActivity(Intent(activity, FoodScanActivity::class.java))
        }
        binding.ivShare.setOnClickListener{
            startActivity(Intent(activity, ShareActivity::class.java))
        }
        binding.ivTips.setOnClickListener{
            startActivity(Intent(activity, TipsActivity::class.java))
        }
        binding.etSearchHome.addTextChangedListener (object : TextWatcher{
            override fun afterTextChanged(s: Editable) {
                hideOrShowSearchBtn()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                hideOrShowSearchBtn()
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                hideOrShowSearchBtn()
            }
        })
        binding.ivSearch.setOnClickListener{
            if(binding.etSearchHome.text?.trim()?.length != 0){
                startActivity(Intent(activity, SearchFoodListActivity::class.java))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun hideOrShowSearchBtn(){
        if(binding.etSearchHome.text?.trim()?.length != 0){
            binding.ivSearch.visibility = View.VISIBLE
        }
        else{
            binding.ivSearch.visibility = View.GONE
        }
    }
    private fun getStorageData(){
        database = FirebaseDatabase.getInstance().getReference("Storage")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(idSnapshot in snapshot.children){
                        val storage = idSnapshot.getValue(Storage::class.java)
                        listPenyimpananHomeArrayList.add(storage!!)
                    }
                    listPenyimpananHomeRecyclerView.adapter = StorageAdapter(listPenyimpananHomeArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
//
            }
        })
    }
}