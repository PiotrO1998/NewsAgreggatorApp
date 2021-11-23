package com.example.newsagreggatorapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.models.Explore

/**
 * This class represent adapter for explore fragment
 *
 * @author Piotr Obara
 * 967793
 */
class AdapterExplore (private val imageModelArrayList: MutableList<Explore>, context: Context) : RecyclerView.Adapter<AdapterExplore.ViewHolder>() {

    var context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.explore_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = imageModelArrayList[position]

        holder.imgView.setImageResource(info.getImage())

        holder.itemView.setOnClickListener(View.OnClickListener() {



            val newIntent = Intent(context, CategoryArticlesActivity::class.java)
            newIntent.putExtra("category", info.modelName)
            context.startActivity(newIntent)
            //TODO otworzyc ArtcielActivity

            //System.out.println("tutaaaaajjjj")
            //var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            //context?.startActivity(i)


            //val msg = title.text
            //val snackbar = Snackbar.make(v, "$msg are the best!", Snackbar.LENGTH_LONG)
            //snackbar.show()
        })

    }

    override fun getItemCount(): Int {
        return imageModelArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var imgView = itemView.findViewById<View>(R.id.placeImageExplore) as ImageView

        init{
            itemView.setOnClickListener(this)
        }

        override  fun onClick(v: View){
            System.out.println("Explore tab")
            //val msg = txtMsg.text
            //val snackbar = Snackbar.make(v, "$msg are the best!", Snackbar.LENGTH_LONG)
            //snackbar.show()
        }
    }

}