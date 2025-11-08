package com.victorkirui.ui

fun getDrawable(animalName: String): Int{
    return when(animalName){
        "Cow" ->{
            com.victorkirui.ui.R.drawable.cow
        }

        "Goat" ->{
            com.victorkirui.ui.R.drawable.goat
        }

        "Bees" ->{
            com.victorkirui.ui.R.drawable.bees
        }

        "Chicken" ->{
            com.victorkirui.ui.R.drawable.chicken
        }

        "Donkey" ->{
            com.victorkirui.ui.R.drawable.donkey
        }

        "Goose" ->{
            com.victorkirui.ui.R.drawable.goose
        }

        "Horse" ->{
            com.victorkirui.ui.R.drawable.horse
        }

        "Pig" ->{
            com.victorkirui.ui.R.drawable.pig
        }

        "Quail" ->{
            com.victorkirui.ui.R.drawable.quail
        }

        "Rabbits" ->{
            com.victorkirui.ui.R.drawable.rabbits
        }

        "Sheep" ->{
            com.victorkirui.ui.R.drawable.sheep
        }

        "Turkey" ->{
            com.victorkirui.ui.R.drawable.turkey
        }

        else -> {
            com.victorkirui.ui.R.drawable.cow
        }
    }
}