import com.jnglman.adc2022.day1.findMaxCalories
import com.jnglman.adc2022.day1.findMaxOfTopNCalories
import com.jnglman.adc2022.day2.calculateRiggedScore
import com.jnglman.adc2022.day2.calculateScore
import com.jnglman.adc2022.day3.sumPriorities
import com.jnglman.adc2022.day3.sumPrioritiesByGroupOf3
import com.jnglman.adc2022.day4.findFullOverlaps
import com.jnglman.adc2022.day4.findOverlaps
import com.jnglman.adc2022.day5.moveCargo

fun main() {
    println("Elf carrying max calories carries ${findMaxCalories()}")
    println("Top 3 Elf's calories sum ${findMaxOfTopNCalories()}")
    println()
    println("My rock-paper-scissors score is ${calculateScore()}")
    println("My rock-paper-scissors rigged score is ${calculateRiggedScore()}")
    println()
    println("Compartments priorities sum is ${sumPriorities()}")
    println("Compartments priorities sum for group of 3 is ${sumPrioritiesByGroupOf3()}")
    println()
    println("There are ${findFullOverlaps()} full overlaps in tasks")
    println("There are ${findOverlaps()} overlaps in tasks")
    println()
    println("The top cargos are: ${moveCargo()}")
}