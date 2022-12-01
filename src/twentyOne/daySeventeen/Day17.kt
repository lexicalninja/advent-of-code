package twentyOne.daySeventeen

import twentyOne.dayTwo.Position
import java.awt.Point

fun main(args: Array<String>) {


}

data class Probe(val v: Velocity, val target: TargetArea, var position: Point){

  fun inRange() = position.x in target.xRange && position.y in target.yRange
  fun pastRange() = position.y < target.yRange.last

}

data class Velocity(var x: Int, var y: Int)

data class TargetArea(val xRange: IntRange, val yRange: IntRange)