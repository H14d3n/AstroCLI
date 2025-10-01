package rocketbuilder

import java.io._
import java.nio.file.{Files, Paths}
import scala.util.Try

// ------------------------------------
// Rocket Parts & Categories
// ------------------------------------

enum PartCategory:
  case Payload, Control, Structure, Propulsion, Tanks

case class RocketPart(
                       name: String,
                       massKg: Double,
                       category: PartCategory
                     )

// ------------------------------------
// Rocket definition
// ------------------------------------

case class Rocket(
                   rID: Int,
                   name: String,
                   parts: Seq[RocketPart],
                   fuelMassKg: Double,
                   maxSpeed: Double
                 ):
  def dryMassKg: Double = parts.map(_.massKg).sum
  def totalMass: Double = dryMassKg + fuelMassKg

// ------------------------------------
// Rocket Manager
// ------------------------------------

object RocketManager:

  val dataFile = "rockets.json"

  // Storage of rockets
  private var rockets: Seq[Rocket] = Seq.empty

  // Available parts (grouped by category)
  val availableParts: Map[PartCategory, Seq[RocketPart]] = Map(
    PartCategory.Payload -> Seq(
      RocketPart("Small Payload Fairing", 800, PartCategory.Payload),
      RocketPart("Medium Payload Fairing", 1500, PartCategory.Payload),
      RocketPart("Large Payload Fairing", 2500, PartCategory.Payload),
      RocketPart("Satellite Payload", 1200, PartCategory.Payload),
      RocketPart("Crew Capsule", 3000, PartCategory.Payload)
    ),

    PartCategory.Control -> Seq(
      RocketPart("Basic Avionics", 500, PartCategory.Control),
      RocketPart("Advanced Avionics", 900, PartCategory.Control),
      RocketPart("Grid Fins", 800, PartCategory.Control),
      RocketPart("Small Reaction Control Thrusters", 600, PartCategory.Control),
      RocketPart("Large Reaction Control Thrusters", 1200, PartCategory.Control)
    ),

    PartCategory.Structure -> Seq(
      RocketPart("Lightweight Airframe", 8000, PartCategory.Structure),
      RocketPart("Reinforced Airframe", 12000, PartCategory.Structure),
      RocketPart("Heavy-Duty Airframe", 16000, PartCategory.Structure),
      RocketPart("Landing Legs (Light)", 1200, PartCategory.Structure),
      RocketPart("Landing Legs (Heavy)", 2500, PartCategory.Structure),
      RocketPart("Heat Shield", 1500, PartCategory.Structure)
    ),

    PartCategory.Propulsion -> Seq(
      RocketPart("Engine - Merlin", 4700, PartCategory.Propulsion),
      RocketPart("Engine - Raptor", 6500, PartCategory.Propulsion),
      RocketPart("Engine - Ion Drive", 1500, PartCategory.Propulsion),
      RocketPart("Engine - Solid Booster", 3000, PartCategory.Propulsion),
      RocketPart("Engine - Nuclear Thermal", 8000, PartCategory.Propulsion)
    ),

    PartCategory.Tanks -> Seq(
      RocketPart("Small Fuel Tank (empty)", 2000, PartCategory.Tanks),
      RocketPart("Medium Fuel Tank (empty)", 3000, PartCategory.Tanks),
      RocketPart("Large Fuel Tank (empty)", 4000, PartCategory.Tanks),
      RocketPart("Extra-Large Fuel Tank (empty)", 6000, PartCategory.Tanks),
      RocketPart("Cryogenic Tank (empty)", 5000, PartCategory.Tanks)
    )
  )


  // --------------------
  // Core methods
  // --------------------

  def addRocket(rocket: Rocket): Seq[Rocket] =
    rockets = rockets :+ rocket
    rockets

  def listRockets(): Seq[String] =
    if rockets.isEmpty then
      Seq.empty
    else
      rockets.map { r =>
        s"ID: ${r.rID}, Name: ${r.name}, Dry Mass: ${r.dryMassKg} kg, " +
          s"Fuel: ${r.fuelMassKg} kg, Total: ${r.totalMass} kg"
      }
