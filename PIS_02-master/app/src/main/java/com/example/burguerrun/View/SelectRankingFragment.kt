package com.example.burguerrun.View


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import kotlinx.android.synthetic.main.fragment_selectranking.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RankingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RankingFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SelectRankingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val selectRankingPresenter = Singletons.selectRankingPresenter
    val rankingPresenter = Singletons.rankingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rankingPresenter.update()
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_selectranking, container, false)
        selectRankingPresenter.setView(view)
        selectRankingPresenter.setFragmentActivity(activity!!)
        selectRankingPresenter.setContext(context!!)

        view.ranking1.setOnClickListener {
            selectRankingPresenter.openRanking("0")
        }

        view.ranking2.setOnClickListener {
            selectRankingPresenter.openRanking("1")
        }

        view.ranking3.setOnClickListener {
            selectRankingPresenter.openRanking("2")
        }

        view.ranking4.setOnClickListener {
            selectRankingPresenter.openRanking("3")
        }
        return view
    }




}
