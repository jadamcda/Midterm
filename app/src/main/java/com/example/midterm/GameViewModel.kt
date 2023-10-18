package com.example.midterm
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates
import kotlin.random.Random

public class GameViewModel : ViewModel() {
    var secretNum = 0
    var numOfTries = 0

    fun generateSecretNum () {
        secretNum = Random.nextInt(1, 100)
    }

    fun makeGuess (guess: Int) : String {
        numOfTries++

        if(guess > secretNum){
            return ">"
        } else if(guess < secretNum){
            return "<"
        } else{
            return "="
        }
    }
}