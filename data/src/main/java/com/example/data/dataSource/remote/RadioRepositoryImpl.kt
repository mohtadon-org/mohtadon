package com.example.data.dataSource.remote

import com.example.data.dataSource.remote.mapper.radio_station.RadioMapper
 import com.example.data.dataSource.remote.service.RadioService
import com.example.data.utils.wrapApiResponse
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.repo.RadioRepository
import javax.inject.Inject

class RadioRepositoryImpl @Inject constructor (private val radioService: RadioService) :RadioRepository{
    override suspend fun getAllRadioStation(): List<RadioEntity> {
        val radioResponse = wrapApiResponse{radioService.getAllRadioStation()}
        return  RadioMapper.mapToRadioEntityList(radioResponse.radios)
    }


}