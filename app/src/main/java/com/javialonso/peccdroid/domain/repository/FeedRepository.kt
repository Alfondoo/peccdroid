package com.javialonso.peccdroid.domain.repository

import com.javialonso.peccdroid.data.entity.ProfileEntity
import io.reactivex.Maybe

interface FeedRepository {
    fun profile(): Maybe<ProfileEntity>
}