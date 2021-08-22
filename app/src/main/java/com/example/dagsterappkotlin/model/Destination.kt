package com.example.dagsterappkotlin.model

data class Destination(
	//var id: Int = 0,
	var mail: String? = null,
	var password: String? = null,
	var name: String? = null
)

data class authDestination(
	var mail: String? = null,
	var password: String? = null,
)