package com.example.domain.usecases

import com.example.domain.repo.HadithRepository
import javax.inject.Inject

class GetSpecificHadithBookChaptersUseCase @Inject constructor(
    private val hadithRepository: HadithRepository
) {
    suspend  fun invoke(bookName: String) =
        hadithRepository.getSpecificHadithBookChapters(bookName)

}