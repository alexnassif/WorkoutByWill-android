package com.alexnassif.mobile.workoutbywill.Model

import com.squareup.moshi.Json

data class LoginEntity(@field:Json(name = "username") val username: String,
                       @field:Json(name = "password") val password: String)