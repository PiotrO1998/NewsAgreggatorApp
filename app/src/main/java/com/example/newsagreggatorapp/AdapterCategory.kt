package com.example.newsagreggatorapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.models.Source
import com.squareup.picasso.Picasso

/**
 * This class represent adapter for Category Article Activity
 *
 * @author Piotr Obara
 * 967793
 */
class AdapterCategory (private var savedArticles: MutableList<Source>, context: Context) : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    var savedArticle: MutableList<Source> = savedArticles
    var context: Context? = context
    var s: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.category_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = savedArticles[position]

        holder.source.text = info.name
        holder.descryption.text = info.description
        holder.category.text = info.category
        holder.url = info.url



        holder.itemView.setOnClickListener(View.OnClickListener() {

            val newIntent = Intent(context, ArticleActivity::class.java)
            newIntent.putExtra("source", info.name)
            newIntent.putExtra("title", info.description)
            newIntent.putExtra("publishedAt", info.category)
            newIntent.putExtra("url", info.url)
            newIntent.putExtra("description", s)
            context?.startActivity(newIntent)

            //TODO otworzyc ArtcielActivity

        })
    }

    override fun getItemCount(): Int {
        return savedArticles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var source = itemView.findViewById<View>(R.id.sourceCategory) as TextView
        var descryption = itemView.findViewById<View>(R.id.descryptionCategory) as TextView
        var category = itemView.findViewById<View>(R.id.categoryCategory) as TextView
        var url: String? = null
        var language: String? = null
        var country: String? = null

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