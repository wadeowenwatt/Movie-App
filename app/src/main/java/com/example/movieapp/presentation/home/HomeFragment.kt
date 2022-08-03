package com.example.movieapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutHomeBinding
import com.example.movieapp.domain.model.ItemMostPopular
import com.example.movieapp.domain.model.ItemUpcoming
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
        return bindingLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding view
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.home.isNotEmpty() -> {
                        val listMostPopular = mutableListOf<ItemMostPopular>()
                        val listUpcoming = mutableListOf<ItemUpcoming>()
                        for (i in 0..10) {
                            listMostPopular.add(
                                ItemMostPopular(
                                    uiState.home[0].listPopular[i].id.toString(),
                                    uiState.home[0].listPopular[i].image.toString(),
                                    uiState.home[0].listPopular[i].title.toString(),
                                    uiState.home[0].listPopular[i].imdb.toString()
                                )
                            )
                            listUpcoming.add(
                                ItemUpcoming(
                                    uiState.home[0].listUpcoming[i].poster.toString(),
                                    uiState.home[0].listUpcoming[i].id.toString()
                                )
                            )
                        }

                        bindingLayout.searchFilm.setOnClickListener {
                            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
                        }

                        /* ViewPager2: Most Popular */
                        val adapter1 = MPViewPagerAdapter(requireContext(), listMostPopular)

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
                        val adapter2 = URViewPagerAdapter(listUpcoming)
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
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }
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