package com.example.mohtdon.ui.hadith.chapterHadithList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mohtdon.R
import com.example.mohtdon.databinding.FragmentBookChapterHadithListBinding
import com.example.mohtdon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookChapterHadithList : BaseFragment<FragmentBookChapterHadithListBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_book_chapter_hadith_list
    override val viewModel: BookChapterHadithListViewModel by viewModels()
    private val args: BookChapterHadithListArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

//        setAdapter()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.chapterHadaithList.collect {
                viewModel.collectSpecificChapterHadith(args.bookName, args.chapterNumber)
//                Log.i("ahmed", it.toString())

                val ChaptersHadithRvAdapter = BookChapterHadithListRvAdapter(
                    it
                )
                binding.chapterHadithListRv.adapter = ChaptersHadithRvAdapter
            }

        }

    }
}