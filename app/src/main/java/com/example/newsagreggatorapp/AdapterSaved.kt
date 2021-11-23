package com.example.newsagreggatorapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.models.Explore
import com.squareup.picasso.Picasso

/**
 * This class represent adapter for saved articles activity
 *
 * @author Piotr Obara
 * 967793
 */

class AdapterSaved (private var savedArticles: MutableList<ArticleToSave>, context: Context) : RecyclerView.Adapter<AdapterSaved.ViewHolder>() {

    var savedArticle: MutableList<ArticleToSave> = savedArticles
    var context: Context? = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.saved_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = savedArticles[position]

        Picasso.get().load(info.urlToImage).into(holder.imgView)
        holder.title.text = info.title
        holder.source.text = info.source
        holder.date.text = info.publishedAt
        holder.url = info.url


        holder.itemView.setOnClickListener(View.OnClickListener() {



            val newIntent = Intent(context, ArticleActivity::class.java)
            newIntent.putExtra("title", info.title)
            newIntent.putExtra("source", info.source)
            newIntent.putExtra("publishedAt", info.publishedAt)
            newIntent.putExtra("url", info.url)
            newIntent.putExtra("urlToImage", info.urlToImage)
            newIntent.putExtra("description", info.description)
            context?.startActivity(newIntent)
            //TODO otworzyc ArtcielActivity
        })

    }

    override fun getItemCount(): Int {
        return savedArticles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var imgView = itemView.findViewById<View>(R.id.placeImageSave) as ImageView
        var title = itemView.findViewById<View>(R.id.titleSave) as TextView
        var source = itemView.findViewById<View>(R.id.sourceSave) as TextView
        var date = itemView.findViewById<View>(R.id.dateSave) as TextView
        var url: String? = null

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

    fun setChanges(savedArticles: ArrayList<ArticleToSave>){
        savedArticle.clear()

        savedArticle.addAll(savedArticles)
        this.notifyDataSetChanged()
    }

}