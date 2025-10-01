package rocketbuilder

import scala.io.StdIn.readLine
import rocketbuilder._

// ------------------------------------
// AstroCLI - Beyond Horizons
// ------------------------------------

class ConsoleUI:

  private var running: Boolean = true

  // Terminal-Style
  object Term:
    val RESET   = "\u001B[0m"
    val BOLD    = "\u001B[1m"
    val GREEN   = "\u001B[32m"
    val INVERT  = "\u001B[7m"
    val CLEAR   = "\u001B[2J"
    val HOME    = "\u001B[H"

  import Term._

  private val banner: String =
    s"""|
        |${GREEN}
        |   ░███                  ░██                          ░██████  ░██         ░██████
        |  ░██░██                 ░██                         ░██   ░██ ░██           ░██
        | ░██  ░██   ░███████  ░████████ ░██░████  ░███████  ░██        ░██           ░██
        |░█████████ ░██           ░██    ░███     ░██    ░██ ░██        ░██           ░██
        |░██    ░██  ░███████     ░██    ░██      ░██    ░██ ░██        ░██           ░██
        |░██    ░██        ░██    ░██    ░██      ░██    ░██  ░██   ░██ ░██           ░██
        |░██    ░██  ░███████      ░████ ░██       ░███████    ░██████  ░██████████ ░██████
        |
        |${RESET}<―――――――――――――――――――――――――――――――――――――――――――――――――――>${RESET}
        |""".stripMargin

  def start(): Unit =
    println(banner)

    while running do
      println(s"${GREEN}\n${INVERT} SYSTEM MENU ${RESET}")
      println(s"${GREEN} [1] LIST ROCKET FLEET")
      println(s" [2] REGISTER NEW ROCKET")
      println(s" [3] CALCULATE FLIGHT TIME")
      println(s" [4] SAVE FLEET TO FILE")
      println(s" [5] LOAD FLEET FROM FILE")
      println(s" [0] SHUTDOWN TERMINAL${RESET}")

      val option = readLine(s"\n${GREEN}COMMAND > ${RESET}").toIntOption.getOrElse(-1)

      option match
        case 0 =>
          println(s"${GREEN}SYSTEM SHUTDOWN INITIATED... TERMINAL OFFLINE${RESET}")
          running = false
        case 1 => listRocketsInit()
        case 2 => addRocketInit()
        case 3 => calculateFlightInit()
        case 4 => saveRocketsInit()
        case 5 => loadRocketsInit()
        case _ => println(s"${GREEN}INVALID COMMAND. RETRY.${RESET}")


  def listRocketsInit(): Unit =
    val rockets = RocketManager.listRockets()
    println(s"\n${GREEN}${INVERT} ROCKET DATABASE ${RESET}")
    if rockets.isEmpty then
      println(s"${GREEN}NO RECORDS FOUND.${RESET}")
    else
      rockets.foreach(r => println(s"${GREEN}> $r${RESET}"))


  def addRocketInit(): Unit =
    println(s"\n${GREEN}${INVERT} ROCKET REGISTRATION ${RESET}")
    val id = scala.util.Random.nextInt(10000)
    val name = readLine(s"${GREEN}ENTER ROCKET NAME > ${RESET}")
    val fuel = readLine(s"${GREEN}ENTER FUEL MASS (KG) > ${RESET}")
      .toDoubleOption.getOrElse(0.0)

    val rocket = Rocket(id, name, Seq.empty, fuelMassKg = fuel, maxSpeed = 0)

    RocketManager.addRocket(rocket)
    println(s"${GREEN}\nROCKET '${rocket.name}' REGISTERED SUCCESSFULLY.\n${RESET}")


  def calculateFlightInit(): Unit =
    println(s"\n${GREEN}${INVERT} FLIGHT SIMULATION MODULE ${RESET}")
    println(s"${GREEN}MODULE NOT YET OPERATIONAL.${RESET}")

  def saveRocketsInit(): Unit =
    println(s"\n${GREEN}${INVERT} DATA STORAGE ${RESET}")
    println(s"${GREEN}SAVE FUNCTIONALITY NOT IMPLEMENTED.${RESET}")

  def loadRocketsInit(): Unit =
    println(s"\n${GREEN}${INVERT} DATA RETRIEVAL ${RESET}")
    println(s"${GREEN}LOAD FUNCTIONALITY NOT IMPLEMENTED.${RESET}")
