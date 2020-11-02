package com.example.sharedtransitions.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sharedtransitions.R
import java.util.concurrent.TimeUnit


class DetailFragment : Fragment(){

    //private lateinit var sportsArgs : Sports
    private val args : DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val banner: ImageView = view.findViewById(R.id.detail_image_view)
        val title: TextView = view.findViewById(R.id.title_detail_text_view)
        val about: TextView = view.findViewById(R.id.about_detail_text_view)

        banner.setImageResource(args.sportArgs.banner)
        title.text = args.sportArgs.title
        about.text = args.sportArgs.about

        //because we gave that names on the bind fun in listAdapter
        banner.transitionName = args.sportArgs.banner.toString()
        title.transitionName = args.sportArgs.title
    }


}