package com.example.newsagreggatorapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.models.Explore



/**
 * This class represent explore fragment
 *
 * @author Piotr Obara
 * 967793
 */
class exploreFragment : Fragment() {

    var loggedInActivity : LoggedInActivity = LoggedInActivity()
    val currentUser = loggedInActivity.mAuth.currentUser?.email
    val currentUserL = loggedInActivity.mAuth.currentUser
    private var db: DB = DB()
    private var listOfPreferences: ArrayList<Preference>? = null
    var contextt: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)
        val sqliteDatabase = context?.let { db.getInstance(it) }
        listOfPreferences = sqliteDatabase?.listPreferences()
        val imageModelArrayList = populateList()

        val recyclerView = view?.findViewById<View>(R.id.my_recycler_view_explore) as RecyclerView
        val layoutManager = LinearLayoutManager(view?.context)
        recyclerView.layoutManager = layoutManager
        System.out.println("jdjdjdejeiei" + imageModelArrayList.size)
        val mAdapter = AdapterExplore(imageModelArrayList, contextt!!)
        recyclerView.adapter = mAdapter


        return view

    }

    private fun populateList(): ArrayList<Explore> {
        val list = ArrayList<Explore>()
        var preferencesOfCurrentUser: ArrayList<String>? = null


        System.out.println(loggedInActivity.sqliteDatabase.toString())

        val myImageList = arrayOf(
            R.drawable.science,
            R.drawable.general,
            R.drawable.entertainment,
            R.drawable.health,
            R.drawable.business,
            R.drawable.technology,
            R.drawable.sports
        )
        val myImageNameList = arrayOf(
            "science", "general", "entertainment",
            "health", "business", "technology", "sports"
        )
        if (currentUserL == null || checkIfCurrentUserHavePreferences() == false) {
            for (i in 0..6) {
                val imageModel = Explore()
                imageModel.modelImage = myImageList[i]
                imageModel.modelName = myImageNameList[i]
                list.add(imageModel)
                System.out.println("NAme      " + myImageNameList[i])
            }
        } else {
                preferencesOfCurrentUser = getCurrentUserPreferences()
                for (i in 0..6) {
                    if (preferencesOfCurrentUser.contains(myImageNameList[i])) {
                        val imageModel = Explore()
                        imageModel.modelImage = myImageList[i]
                        imageModel.modelName = myImageNameList[i]
                        list.add(imageModel)
                        System.out.println("N    " + myImageNameList[i])
                    }
                }
            }
            return list
        }

    fun checkIfCurrentUserHavePreferences(): Boolean {
        var returningValue: Boolean = false
        for (preference in listOfPreferences!!){
            if (preference.user == currentUser)
                returningValue = true
        }
        return returningValue
    }

    fun getCurrentUserPreferences(): ArrayList<String> {
        var arrayOfPreferences: ArrayList<String> = ArrayList()
        for (preference in listOfPreferences!!){
            if (preference.user == currentUser){
                arrayOfPreferences.add(preference.name)
            }
        }
        return arrayOfPreferences
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextt = context
    }

}

