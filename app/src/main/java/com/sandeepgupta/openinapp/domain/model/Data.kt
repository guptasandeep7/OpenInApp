package com.sandeepgupta.openinapp.domain.model

data class Data(
    val favourite_links: List<String>,
    val overall_url_chart: Map<String,Double>,
    val recent_links: List<Link>,
    val top_links: List<Link>
)