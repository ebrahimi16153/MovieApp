package com.github.ebrahimi16153.movieapp.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.setVisibility(isShow:Boolean){

    if (isShow){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.INVISIBLE
    }

}

fun RecyclerView.initRecycler(layoutManager:RecyclerView.LayoutManager,adapter: RecyclerView.Adapter<*>){
    this.layoutManager = layoutManager
    this.adapter = adapter

}