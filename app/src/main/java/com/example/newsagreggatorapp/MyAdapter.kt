package com.example.project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.*
import com.example.newsagreggatorapp.models.Article
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

/**
 * This class represent adapter for favorite fragment
 *
 * @author Piotr Obara
 * 967793
 */
class  MyAdapter(private val imageModelArrayList: MutableList<Article>, context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var context: Context = context
    var loggedInActivity = LoggedInActivity()
    var currentUser = loggedInActivity.mAuth.currentUser?.email
    var savedArticlesActivity = SavedArticlesActivity()

    var titleA: String = ""
    var sourceA: String = ""
    var publishedAtA: String = ""
    var urlToImageA: String = ""
    var urlA: String = ""
    var descriptionA: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view : View  = inflater.inflate(R.layout.favorite_layout, parent, false)



        //val v = inflater.inflate(R.layout.cardview_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = imageModelArrayList[position]

        Picasso.get().load(info.urlToImage).fit().into(holder.imgView)
        //holder.imgView.setImageResource()
        holder.title.text = info.title
        holder.source.text = info.source.name
        holder.date.text = info.publishedAt
        holder.url = info.url

        titleA = info.title
        sourceA = info.title
        publishedAtA = info.publishedAt
        urlA = info.url
        urlToImageA = info.urlToImage
        descriptionA = info.description


        holder.itemView.setOnClickListener(View.OnClickListener() {



    val newIntent = Intent(context, ArticleActivity::class.java)
    newIntent.putExtra("title", info.title)
    newIntent.putExtra("source", info.source.name)
    newIntent.putExtra("publishedAt", info.publishedAt)
    newIntent.putExtra("url", info.url)
    newIntent.putExtra("urlToImage", info.urlToImage)
    newIntent.putExtra("description", info.description)
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
        var imgView = itemView.findViewById<View>(R.id.articleImage) as ImageView
        var title = itemView.findViewById<View>(R.id.title) as TextView
        var source = itemView.findViewById<View>(R.id.source) as TextView
        var date = itemView.findViewById<View>(R.id.date) as TextView
        var url: String? = null




        override fun onClick(v: View){

            //val newIntent = Intent(context, ArticleActivity::class.java)
              //newIntent.putExtra("title", title.text)
             // newIntent.putExtra("source", source.text)
             // newIntent.putExtra("publishedAt", date.text)
              //newIntent.putExtra("url", url.tex)
              //newIntent.putExtra("urlToImage", imgView.toString())
              //newIntent.putExtra("descrition", descriptionA)
              //context?.startActivity(newIntent)
            //TODO otworzyc ArtcielActivity

            //System.out.println("tutaaaaajjjj")
            //var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            //context?.startActivity(i)


            val msg = title.text
            val snackbar = Snackbar.make(v, "$msg are the best!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }





    }

}