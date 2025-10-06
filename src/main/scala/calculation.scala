package rocketbuilder

import scala.util.Random

object ExpeditionCalculator:

  // ----------------------------------------------------
  // Fun Simulation Constants (not real physics)
  // ----------------------------------------------------
  private val baseFuelUsageRate = 0.8      // Base fuel multiplier
  private val baseSpeed = 12000.0          // Base km/h
  private val randomVariance = 0.15        // Random result variation

  // ----------------------------------------------------
  // Speed Bonus depends on Parts installed
  // ----------------------------------------------------
  private def speedBonus(parts: Seq[RocketPart]): Double =
    var speed = baseSpeed

    // Engines
    if parts.exists(_.name.contains("Merlin")) then speed += 4000
    if parts.exists(_.name.contains("Raptor")) then speed += 7000
    if parts.exists(_.name.contains("Ion")) then speed += 12000
    if parts.exists(_.name.contains("Nuclear")) then speed += 8000

    // Weight factors
    if parts.exists(_.name.contains("Lightweight")) then speed += 1500
    if parts.exists(_.name.contains("Heavy-Duty")) then speed -= 1500

    // Control systems efficiency
    if parts.exists(_.name.contains("Advanced Avionics")) then speed += 1000

    speed

  private def fuelEfficiency(parts: Seq[RocketPart]): Double =
    var efficiency = baseFuelUsageRate

    if parts.exists(_.name.contains("Cryogenic")) then efficiency *= 0.85
    if parts.exists(_.name.contains("Large")) then efficiency *= 1.1
    if parts.exists(_.name.contains("Extra-Large")) then efficiency *= 1.2
    if parts.exists(_.name.contains("Lightweight")) then efficiency *= 0.95

    efficiency

  // ----------------------------------------------------
  // Core Simulation Functions
  // ----------------------------------------------------
  def simulateExpedition(rocket: Rocket, planet: Planet): ExpeditionResult =
    val distance = planet.distanceToEarth * 1_000_000 // km
    val speed = speedBonus(rocket.parts)
    val timeHours = distance / speed

    // Random variance for realism
    val randomFactor = 1.0 + (Random.between(-randomVariance, randomVariance))
    val adjustedTime = timeHours * randomFactor

    // Fuel needed (arbitrary simulation rule)
    val fuelNeeded = (distance / 5000) * fuelEfficiency(rocket.parts)
    val success =
      rocket.fuelMassKg >= fuelNeeded * 0.9 &&
        rocket.parts.nonEmpty &&
        rocket.parts.exists(_.category == PartCategory.Propulsion)

    val remainingFuel = math.max(0, rocket.fuelMassKg - fuelNeeded)

    ExpeditionResult(
      rocket = rocket,
      planet = planet,
      success = success,
      travelHours = adjustedTime,
      fuelUsed = math.min(fuelNeeded, rocket.fuelMassKg),
      remainingFuel = remainingFuel
    )

  // ----------------------------------------------------
  // Data container
  // ----------------------------------------------------
  case class ExpeditionResult(
                               rocket: Rocket,
                               planet: Planet,
                               success: Boolean,
                               travelHours: Double,
                               fuelUsed: Double,
                               remainingFuel: Double
                             ):
    def report: String =
      val days = travelHours / 24
      val status = if success then "SUCCESSFUL" else "FAILED"
      f"""
         |🚀 Expedition Report
         |----------------------------
         |Rocket: ${rocket.name}
         |Destination: ${planet.name}
         |Distance: ${planet.distanceToEarth} million km
         |Speed: ${speedBonus(rocket.parts)} km/h
         |Fuel Used: ${fuelUsed}%.1f kg
         |Fuel Remaining: ${remainingFuel}%.1f kg
         |Travel Time: ${days}%.2f days
         |Status: $status
         |----------------------------
         |""".stripMargin
