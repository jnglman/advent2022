import com.jnglman.adc2022.day1.findMaxCalories
import com.jnglman.adc2022.day1.findMaxOfTopNCalories
import com.jnglman.adc2022.day2.calculateRiggedScore
import com.jnglman.adc2022.day2.calculateScore
import com.jnglman.adc2022.day3.sumPriorities
import com.jnglman.adc2022.day3.sumPrioritiesByGroupOf3
import com.jnglman.adc2022.day4.findFullOverlaps
import com.jnglman.adc2022.day4.findOverlaps
import com.jnglman.adc2022.day5.Constants
import com.jnglman.adc2022.day5.moveCargo
import com.jnglman.adc2022.day6.scanStartMessage
import com.jnglman.adc2022.day6.scanStartPacket
import com.jnglman.adc2022.day7.freeSpace
import com.jnglman.adc2022.day7.sumSizeOfLessThan100000

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
    println("The top cargos are: ${moveCargo(Constants.v9000)}")
    println("The top cargos with new crane version are: ${moveCargo(Constants.v9001)}")
    println()
    println("Signal start indicated by ${scanStartPacket()} symbol")
    println("Message start indicated by ${scanStartMessage()} symbol")
    println()
    println("Summary size of directories with size < 100000 =  ${sumSizeOfLessThan100000()}")
    println("Directory to delete ${sumSizeOfLessThan100000()}")
    println("We need to free ${freeSpace()} to apply update")
}