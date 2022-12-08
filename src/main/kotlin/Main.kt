import com.jnglman.adc2022.day1.findMaxCalories
import com.jnglman.adc2022.day1.findMaxOfTopNCalories
import com.jnglman.adc2022.day2.calculateRiggedScore
import com.jnglman.adc2022.day2.calculateScore
import com.jnglman.adc2022.day3.sumPriorities

fun main() {
    println("Elf carrying max calories carries ${findMaxCalories()}")
    println("Top 3 Elf's calories sum ${findMaxOfTopNCalories()}")
    println("My rock-paper-scissors score is ${calculateScore()}")
    println("My rock-paper-scissors rigged score is ${calculateRiggedScore()}")
    println("Compartments priorities sum is ${sumPriorities()}")
}