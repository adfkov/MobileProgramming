package com.chobo.real

import java.io.File

// 데이터 클래스 정의
data class Word(val word: String, val meaning : String)

fun main() {
    val words = mutableListOf<Word>()
    val filePath = "C:/web/words2.txt"

    // 파일 읽기
    try {
        File(filePath).bufferedReader().use { reader ->
            while(true) {
                val word = reader.readLine() ?: break
                val meaning = reader.readLine() ?: break

                words.add(Word(word, meaning))
            }

        }

    } catch (e: Exception) {
        println("파일 위치를 확인하세요.")
    }

    println("학번: 201811294 이름: 정재우")
    while(true) { // 사용자 입력 받기
        println()
        println("1) 영어단어 검색 2) 부분 영단어 검색(입력값으로 시작하는) 3) 뜻 검색 4) 종료")
        println("메뉴를 선택하세요 ")

        val choice = readLine()
        when(choice) {
            "1" -> {
                print("영어 단어를 입력하세요: ")
                val query = readLine()?.trim() ?: ""
                words.find { it.word == query}
                    ?.let {println("${it.word}: ${it.meaning}")}
                    ?:println("찾는 단어가 존재하지 않습니다.")
            }
            "2"-> {
                print("부분 영단어를 입력하세요: ")
                val query = readLine()?.trim() ?:""
                words.filter {it.word.startsWith(query)}
                    .takeIf { it.isNotEmpty() }
                    ?.forEach { println("${it.word} : ${it.meaning}")}
                    ?: println("찾는 단어가 존재하지 않습니다.")
            }
           "3" ->{
                print("뜻을 입력하세요: ")
                val query = readLine()?.trim() ?:""
                words.filter{it.meaning == query}
                    .takeIf { it.isNotEmpty() }
                    ?.forEach { println("${it.word}:${it.meaning}") }
                    ?: println("찾는 단어가 존재하지 않습니다.")
            }
            "4"-> {
                println("프로그램을 종료합니다.")
                break
            }
            else -> println("잘못된 입력입니다. 다른 값을 입력해주세요.")
        }
    }
}