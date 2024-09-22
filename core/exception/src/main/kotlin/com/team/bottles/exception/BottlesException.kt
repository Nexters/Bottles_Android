package com.team.bottles.exception

import java.io.IOException

open class BottlesException(
    val code: Int,
    override val message: String?,
) : IOException(message)