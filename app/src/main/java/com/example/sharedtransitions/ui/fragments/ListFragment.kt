package com.example.sharedtransitions.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedtransitions.R
import com.example.sharedtransitions.data.Sports
import com.example.sharedtransitions.utils.adapters.SportsAdapter

class ListFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Decides how will the transition will be seen when back button pressed

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.sports_recycler_view)
        val list = Sports.sportsList(requireContext())
        val adapter = SportsAdapter(list, sportsItemListener)
        recyclerView.adapter = adapter

        // When user hits back button transition takes backward
        //needed for the animations to be visible
        postponeEnterTransition()

        recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    //Implementation of the lambda
    // the extra val is for the sharedTransition
    private val sportsItemListener = SportsAdapter.OnClickListener { sports, imageView, textView ->
        val direction: NavDirections = ListFragmentDirections.actionListFragmentToDetailFragment(sports)

        val extras = FragmentNavigatorExtras(
            imageView to sports.banner.toString(),
            textView to sports.title
        )

        findNavController().navigate(direction, extras)

    }
}