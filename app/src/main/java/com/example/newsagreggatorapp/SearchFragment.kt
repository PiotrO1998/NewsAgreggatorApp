package com.example.newsagreggatorapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.api.Retrofit
import com.example.newsagreggatorapp.models.Article
import com.example.newsagreggatorapp.models.News
import com.example.project.MyAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.concurrent.thread

/**
 * This class represnt search fragment
 *
 * @author Piotr Obara
 * 967793
 */
class SearchFragment : Fragment() {

    val retrofit: Retrofit = Retrofit()
    var articlesArrayList: ArrayList<Article> = ArrayList()
    lateinit var adapter : MyAdapter
    var contextt: Context? = null
    var call: Call<News> = retrofit.api().getSearchNews()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)
        var recyclerView = view.findViewById<View>(R.id.my_recycler_view_search) as RecyclerView

        var search = view.findViewById<EditText>(R.id.search)
        var text = search.text.toString()

        var job: Job? = null
        search.addTextChangedListener { text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text?.let {
                    if (text.isNotEmpty()) {
                        System.out.println(text)
                        call = retrofit.api().getSearchNews(text.toString())
                        call.enqueue(object: Callback<News> {
                            override fun onFailure(call: Call<News>, t: Throwable) {}

                            override fun onResponse(call: Call<News>, response: Response<News>) {
                                if (response.isSuccessful && response.body()?.articles != null)

                                articlesArrayList = response.body()?.articles!!
                                adapter = MyAdapter(articlesArrayList, contextt!!)
                                recyclerView.adapter = adapter
                                val layoutManager = LinearLayoutManager(view.context)
                                recyclerView.layoutManager = layoutManager

                                if(articlesArrayList.size > 0) {

                                }
                            }
                        })
                        }
                    }
                }
            }

            return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextt = context
    }

}