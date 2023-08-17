package com.example.shopbook.data.repository.banner

import com.example.shopbook.data.model.BannerList
import retrofit2.Response

interface BannerRepository {
    suspend fun getBanner() : Response<BannerList>?
}