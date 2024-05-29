package com.example.dataapi.model

import com.google.gson.annotations.SerializedName

class ResultStaff(
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("staff")
    val staff: java.util.List<DataItem>? = null,

    @field:SerializedName("status")
    val status: String? = null
)