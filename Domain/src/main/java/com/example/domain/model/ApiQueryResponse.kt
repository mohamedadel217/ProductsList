package com.example.domain.model


data class ApiQueryResponse(

 	val metadata: Metadata ,

 	val success: Boolean
)

data class Metadata(

 	val sort: String ,

 	val title: String ,

 	val totalProducts: Int ,

 	val results: List<ResultsItem>
)


