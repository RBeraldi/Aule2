package com.example.macc.aule.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.macc.aule.R
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.recyclerview.widget.DividerItemDecoration
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //import kotlinx.android.synthetic.main.fragment_home.*
        //avoid use findViewById...

        //Specify adapter and LayoutManager
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val dest = activity!!.findNavController(R.id.nav_host_fragment).currentDestination!!.label
        list.addItemDecoration(itemDecoration)
        list.layoutManager=LinearLayoutManager(context)
        list.adapter=AuleAdapter(context,dest.toString())


//        Log.i("ROR",currentDate)

    }


}