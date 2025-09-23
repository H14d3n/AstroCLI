import java.io._
import java.nio.file.{Files, Paths}
import scala.util.Try

case class Rocket(
                   id: String,
                   name: String,
                   dryMassKg: Double,
                   fuelMassKg: Double,
                   maxSpeed: Double
                 ) {
  def totalMass: Double = dryMassKg + fuelMassKg
  def addRocket: Unit = ()
  def listRocket: Unit = ()
}

object RocketManager:
  val dataFile = "rockets.json"
