import scala.io.StdIn.readLine

@main def consoleUI(): Unit =
  val banner =
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

  println(banner)

  println("What do you want to do?")

  println("\nAvailable Options:")
  println("1) List all rockets")
  println("2) Add a new rocket")
  println("3) Calculate flight Time")
  println("4) Save rockets to file")
  println("5) Load rockets from file")
  println("0) Exit")

  val option = readLine()

  option match {
    case 0 => listRocket()
    case 1 => addRocket()
    case 3 => calculateFlight()
    case 4 => saveRockets()
    case 5 => loadRockets()
    case _ => println("Invalid Option")
  }


