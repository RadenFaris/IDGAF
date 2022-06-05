package com.capstoneproject.aplikasiantifoodwaste.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstoneproject.aplikasiantifoodwaste.databinding.FragmentProfileBinding
import com.capstoneproject.aplikasiantifoodwaste.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.FragmentManager
import com.capstoneproject.aplikasiantifoodwaste.R
import com.capstoneproject.aplikasiantifoodwaste.searchfood.SearchFoodDetailActivity

class ProfileFragment : Fragment(){

    private lateinit var auth : FirebaseAuth
    private lateinit var binding : FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.btnProfileLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(activity, WelcomeActivity::class.java))
        }

        binding.btnProfileChange.setOnClickListener{
            startActivity(Intent(activity, ChangeProfileActivity::class.java))
        }

        binding.btnMyAddress.setOnClickListener{
            startActivity(Intent(activity, AddressActivity::class.java))
        }

        binding.btnDetailFood.setOnClickListener{
            startActivity(Intent(activity, SearchFoodDetailActivity::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}