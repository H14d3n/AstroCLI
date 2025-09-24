package rocketbuilder

import scala.io.StdIn.readLine
import rocketbuilder._

// ------------------------------------
// AstroCLI - Beyond Horizons
// ------------------------------------

class ConsoleUI:

  private var running: Boolean = true

  private val banner: String =
    """|
       |   ░███                  ░██                          ░██████  ░██         ░██████
       |  ░██░██                 ░██                         ░██   ░██ ░██           ░██
       | ░██  ░██   ░███████  ░████████ ░██░████  ░███████  ░██        ░██           ░██
       |░█████████ ░██           ░██    ░███     ░██    ░██ ░██        ░██           ░██
       |░██    ░██  ░███████     ░██    ░██      ░██    ░██ ░██        ░██           ░██
       |░██    ░██        ░██    ░██    ░██      ░██    ░██  ░██   ░██ ░██           ░██
       |░██    ░██  ░███████      ░████ ░██       ░███████    ░██████  ░██████████ ░██████
       |
       |<―――――――――――――――――――――――――――――――――――――――――――――――――――>
       |""".stripMargin

  def start(): Unit =
    println(banner)

    // Do in mainloop
    while running do
      println("\nAvailable Options:")
      println("1) List all rockets")
      println("2) Add a new rocket")
      println("3) Calculate flight time")
      println("4) Save rockets to file")
      println("5) Load rockets from file")
      println("0) Exit")

      val option = readLine("Choose an option: ").toIntOption.getOrElse(-1)

      option match
        case 0 =>
          println("Exiting…")
          running = false
        case 1 => RocketManager.listRockets()
        // case 2 => RocketManager.addRocket(Rocket)
        // case 3 => RocketManager.calculateFlight()
        // case 4 => RocketManager.saveRockets()
        // case 5 => RocketManager.loadRockets()
        case _ => println("Invalid option")