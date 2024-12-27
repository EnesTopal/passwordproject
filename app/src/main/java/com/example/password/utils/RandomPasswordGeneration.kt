package com.example.password.utils


fun RandomPasswordGenerator(): String{
    val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val lowercase = "abcdefghijklmnopqrstuvwxyz"
    val symbols = "0123456789@#$%"
    val allChars = uppercase + lowercase + symbols

    val mandatoryChars = listOf(
        uppercase.random(),
        lowercase.random(),
        symbols.random()
    )

    val remainingLength = (8..12).random() - mandatoryChars.size
    val remainingChars = (1..remainingLength).map { allChars.random() }

    return (mandatoryChars + remainingChars).shuffled().joinToString("")
}