package com.capstoneproject.aplikasiantifoodwaste.searchfood

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.aplikasiantifoodwaste.R
import com.capstoneproject.aplikasiantifoodwaste.databinding.ActivitySearchFoodListBinding
import com.capstoneproject.aplikasiantifoodwaste.databinding.ItemPenyimpananHomeBinding
import com.capstoneproject.aplikasiantifoodwaste.databinding.SearchFoodCardBinding
import com.capstoneproject.aplikasiantifoodwaste.scan.Storage


class SearchFoodAdapter(private val listSearchFood: ArrayList<SearchFood>) : RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder>() {
    private var onItemClickCallback: SearchFoodAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: SearchFoodAdapter.OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchFoodAdapter.SearchFoodViewHolder {
        val itemView = SearchFoodCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchFoodViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: SearchFoodAdapter.SearchFoodViewHolder, position: Int) {
        holder.bind(listSearchFood[position])
    }
    override fun getItemCount(): Int {
        return listSearchFood.size
    }
    inner class SearchFoodViewHolder(private val binding: SearchFoodCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(searchFood: SearchFood){

            val gambar = base64ToBitmap(searchFood.b64)

            binding.foodPhoto.setImageBitmap(gambar)
            binding.foodDesc.text = searchFood.deskripsi
            binding.foodName.text = searchFood.namaMakanan
            binding.foodStock.text = searchFood.stok

            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(searchFood)
            }
        }
    }
    interface OnItemClickCallback{
        fun onItemClicked(data: SearchFood)
    }

    private fun base64ToBitmap(b64: String?): Bitmap {
        val base64 = Base64.decode(b64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(base64, 0, base64.size)
    }
}


/*
class SearchFoodAdapter(
    private val searchFoodList: List<SearchFood>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_food_card,
            parent, false)

        return SearchFoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchFoodViewHolder, position: Int) {
        val currentItem = searchFoodList[position]

        holder.namaMakanan.text = currentItem.namaMakanan
        holder.deskripsi.text = currentItem.deskripsi
        holder.stok.text = "Stok: ${currentItem.stok}"
        holder.foto.setImageBitmap(base64ToBitmap(currentItem.b64))
    }

    override fun getItemCount() = searchFoodList.size

    inner class SearchFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val namaMakanan: TextView = itemView.findViewById(R.id.food_name)
        val deskripsi: TextView = itemView.findViewById(R.id.food_desc)
        val stok: TextView = itemView.findViewById(R.id.food_stock)
        val foto: ImageView = itemView.findViewById(R.id.food_photo)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private fun base64ToBitmap(b64: String?): Bitmap {
        val base64 = Base64.decode(b64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(base64, 0, base64.size)
    }
}
*/







/*
class SearchFoodAdapter(private val searchFoodList: ArrayList<SearchFood>): RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder>() {

    //private lateinit var mListener: onItemClickListener


    class SearchFoodViewHolder(itemView: View, /*listener: onItemClickListener*/): RecyclerView.ViewHolder(itemView) {
        val namaMakanan: TextView = itemView.findViewById(R.id.food_name)
        val deskripsi: TextView = itemView.findViewById(R.id.food_desc)
        val stok: TextView = itemView.findViewById(R.id.food_stock)
        val foto: ImageView = itemView.findViewById(R.id.food_photo)

        /*
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

         */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_food_card, parent,false)
        return SearchFoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchFoodViewHolder, position: Int) {
        val currentItem = searchFoodList[position]

        holder.namaMakanan.text = currentItem.namaMakanan
        holder.deskripsi.text = currentItem.deskripsi
        holder.stok.text = "Stok: ${currentItem.stok}"
        holder.foto.setImageBitmap(base64ToBitmap(currentItem.b64))
    }

    override fun getItemCount(): Int {
        return searchFoodList.size
    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    /*
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    */

    private fun base64ToBitmap(b64: String?): Bitmap {
        val base64 = Base64.decode(b64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(base64, 0, base64.size)
    }
}
*/