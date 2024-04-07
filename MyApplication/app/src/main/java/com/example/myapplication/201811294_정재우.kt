package com.example.myapplication

fun main() {
    val names = arrayOf("김길동", "오길동", "이길동", "차길동", "차승원", "차은우", "홍길동")

    do {
        println("1) 오름차순 2) 내림차순 3) 이름 찾기 4) 종료 : ")
        val input = readLine() // 사용자 입력 받기

        when (input) {
            "1" -> {
                val sortedAsc = names.sortedArray()
                println(sortedAsc.joinToString("\n"))
            }
            "2" -> {
                val sortedDesc = names.sortedArrayDescending()
                println(sortedDesc.joinToString("\n"))
            }
            "3" -> {
                println("찾고 싶은 이름의 첫 글자를 입력하세요: ")
                val search = readLine() ?: "" // 널이면 빈 문자열로 처리
                val foundNames = names.filter { it.startsWith(search) }
                if (foundNames.isNotEmpty()) {
                    println(foundNames.joinToString("\n"))
                } else {
                    println("$search (으)로 시작하는 이름이 없습니다.")
                }
            }
            "4" -> println("프로그램을 종료합니다.")
            else -> println("올바른 선택이 아닙니다.")
        }
    } while (input != "4") // 사용자가 4를 입력할 때까지 반복
}
