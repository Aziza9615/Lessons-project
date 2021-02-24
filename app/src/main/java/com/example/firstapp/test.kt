package com.example.firstapp

//Дан массив возрастающих целых чисел. Нужно написать функцию, которая будет сжимать подмассивы,
//состоящие из последовательно идущих чисел, в интервалы, представленные строками.
//
//Например на вход поступает массив [1, 2, 3, 5, 6] → ["1-3", "5-6"]


//fun main() {
//    println(trimLists(listOf(1, 2, 3, 5, 6)))
//}
//
//fun trimLists(items: List<Int>): List<String> {
//    val list = mutableListOf<String>()
//    var previewStep: Int? = null
//    var range = ""
//
//    for (i in 0..items.count()) {
//
//        if (previewStep == null) {
//            previewStep = i
//            range = setupStartRange(i)
//        }
//
//        if (!isNextNumber(previewStep, i)) {
//            range += " - $previewStep"
//            list.add(range)
//            range = setupStartRange(i)
//        }
//
//        previewStep = i
//    }
//    return list
//}
//
//fun setupStartRange(step: Int) = "$step"
//
//fun isNextNumber(number: Int?, step: Int): Boolean {
//    return number == step - 1
//}