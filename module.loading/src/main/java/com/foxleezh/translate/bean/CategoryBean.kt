package com.foxleezh.translate.bean

import java.io.Serializable

/**
 * Created by foxleezh on 18-5-29.
 * desc:分类 Bean
 */
data class CategoryBean(val id: Long, val name: String, val description: String, val bgPicture: String, val bgColor: String, val headerImage: String) : Serializable
