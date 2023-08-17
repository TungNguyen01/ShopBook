package com.example.shopbook.data.repository.banner

import com.example.shopbook.data.model.BannerList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class BannerRepositoryImp(private val iDataSource: IDataSource) : BannerRepository {
    override suspend fun getBanner(): Response<BannerList>? {
        return iDataSource.getBanner()
    }
}