package com.example.Bean

data class ServeBean(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
)