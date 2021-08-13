package com.example.pkl_admin.ui.dashboard.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pkl_admin.R
import com.example.pkl_admin.data.viewmodel.DashboardViewModel
import com.example.pkl_admin.databinding.FragmentDashboardBinding
import com.example.pkl_admin.databinding.FragmentDetailBinding
import com.example.pkl_admin.model.Score
import com.example.pkl_admin.ui.dashboard.DashboardAdapter
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var scoreList = ArrayList<Score>()
    private val mDashboardViewModel by lazy {
        ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChart2()
        getData()
        arguments?.let {
            binding.IDDetailPasienBtnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            binding.IDDetailNamaPasien.text = it.getString(DashboardAdapter.keyNamaPasien)
        }
    }

    fun setProgressBar(value:String) {
        binding.IDDetailProgressbar.apply {
            progress = value.toFloat()
            progressMax =100f
            // Set ProgressBar Color
            progressBarColor = Color.BLACK
            // or with gradient
            progressBarColorStart = Color.GREEN
            progressBarColorEnd = Color.RED
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
        }
    }

    fun getData() {
        val date = SimpleDateFormat("ddMyyyy")
        val currentDateNow = date.format(Date())
        mDashboardViewModel.getQuestionValue(arguments?.get(DashboardAdapter.keyIdPasien) as String)
            .observe(viewLifecycleOwner, Observer {

                val filter = it?.filter { data ->
                    data.tanggalNilai == currentDateNow
                }
                if (filter!!.isNotEmpty()){
                    val nilai = filter.first().nilai?.toString()
                    binding.IDDetailQuestion.text = nilai
                    setProgressBar(nilai)
                }
            })
    }

    fun setChart2() {
        scoreList = getScoreList()
        val barChart = binding.chart1
        initBarChart()
        //now draw bar chart with dynamic data
        val entries: ArrayList<BarEntry> = ArrayList()

        //you can replace this data object with  your custom object
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(BarEntry(i.toFloat(), score.score.toFloat()))
        }

        val barDataSet = BarDataSet(entries, "Jam")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data

        barChart.invalidate()
    }

    private fun initBarChart() {
        val barChart = binding.chart1


//        hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove right y-axis
        barChart.axisRight.isEnabled = false

        //remove legend
        barChart.legend.isEnabled = false


        //remove description label
        barChart.description.isEnabled = false


        //add animation
        barChart.animateY(3000)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

    }


    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            Log.d("PKL", "getAxisLabel: index $index")
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                ""
            }
        }
    }

    // simulate api call
    // we are initialising it directly
    private fun getScoreList(): ArrayList<Score> {
        scoreList.add(Score("Duduk", 5))
        scoreList.add(Score("Berdiri", 10))
        scoreList.add(Score("Berbaring", 5))
        scoreList.add(Score("Berjalan", 4))

        return scoreList
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}