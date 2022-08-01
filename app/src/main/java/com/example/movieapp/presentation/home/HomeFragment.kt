package com.example.movieapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.HorizontalMarginItemDecoration
import com.example.movieapp.R
import com.example.movieapp.adapter.MPViewPagerAdapter
import com.example.movieapp.adapter.URViewPagerAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.databinding.LayoutHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _bindingLayout : LayoutHomeBinding? = null
    private val bindingLayout get() = _bindingLayout!!

    private var nextItemVisibleDimens: Float? = null
    private var currentItemHorizontalMarginDimens: Float? = null

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingLayout = LayoutHomeBinding.inflate(inflater)

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {

            }
        }

        return bindingLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(
            R.drawable.bob,
            R.drawable.bob,
            R.drawable.bob
        )

        /* ViewPager2: Most Popular */
        val adapter1 = MPViewPagerAdapter(imageList)
        bindingLayout.viewpager2MostPopular.adapter = adapter1

        bindingLayout.viewpager2MostPopular.offscreenPageLimit = 1

        nextItemVisibleDimens = resources.getDimension(R.dimen.viewpager_next_item_visible_1)
        currentItemHorizontalMarginDimens =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin_1)

        bindingLayout.viewpager2MostPopular.setPageTransformer(
            transformerItem(
                nextItemVisibleDimens,
                currentItemHorizontalMarginDimens,
                1
            )
        )

        bindingLayout.viewpager2MostPopular.addItemDecoration(
            HorizontalMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_current_item_horizontal_margin_1
            )
        )

        bindingLayout.dotTab1.attachTo(bindingLayout.viewpager2MostPopular)

        /* ViewPager2: Upcoming releases */
        val adapter2 = URViewPagerAdapter(imageList)
        bindingLayout.viewpager2Upcoming.adapter = adapter2

        bindingLayout.viewpager2Upcoming.offscreenPageLimit = 1

        nextItemVisibleDimens = resources.getDimension(R.dimen.viewpager_next_item_visible_2)
        currentItemHorizontalMarginDimens =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin_2)

        bindingLayout.viewpager2Upcoming.setPageTransformer(
            transformerItem(
                nextItemVisibleDimens,
                currentItemHorizontalMarginDimens,
                2
            )
        )

        bindingLayout.viewpager2Upcoming.addItemDecoration(
            HorizontalMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_current_item_horizontal_margin_2
            )
        )

        bindingLayout.dotTab2.attachTo(bindingLayout.viewpager2Upcoming)
    }

    // set transformer for item of ViewPager2
    private fun transformerItem(dimens1: Float?, dimens2: Float?, type: Int): ViewPager2.PageTransformer {
        val pageTranslationX = dimens1!! + dimens2!!
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position

            if (type == 1) {
                // Scaling the item's height.
                page.scaleY = 1 - (0.25f * abs(position))
                page.alpha = 0.25f + (1 - abs(position))
            } else
                // Fading effect
                page.alpha = 0.8f + (1 - abs(position))
        }
        return pageTransformer
    }

}