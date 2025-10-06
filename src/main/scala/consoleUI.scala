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
    val YELLOW  = "\u001B[33m"
    val INVERT  = "\u001B[7m"
    val CLEAR   = "\u001B[2J"
    val HOME    = "\u001B[H"

  import Term._

  private val banner: String =
    s"""|
        |${YELLOW}
        |   ░███                  ░██                          ░██████  ░██         ░██████
        |  ░██░██                 ░██                         ░██   ░██ ░██           ░██
        | ░██  ░██   ░███████  ░████████ ░██░████  ░███████  ░██        ░██           ░██
        |░█████████ ░██           ░██    ░███     ░██    ░██ ░██        ░██           ░██
        |░██    ░██  ░███████     ░██    ░██      ░██    ░██ ░██        ░██           ░██
        |░██    ░██        ░██    ░██    ░██      ░██    ░██  ░██   ░██ ░██           ░██
        |░██    ░██  ░███████      ░████ ░██       ░███████    ░██████  ░██████████ ░██████
        |
        |${YELLOW}<―――――――――――――――――――――――――――――――――――――――――――――――――――>${RESET}
        |""".stripMargin

  def start(): Unit =
    println(banner)

    while running do
      println(s"${GREEN}\n${INVERT} SYSTEM MENU ${RESET}")
      println(s"${GREEN} [1] LIST ROCKET FLEET")
      println(s" [2] REGISTER NEW ROCKET")
      println(s" [3] EDIT ROCKET")
      println(s" [4] START EXPEDITION")
      println(s" [5] SAVE FLEET TO FILE")
      println(s" [6] LOAD FLEET FROM FILE")
      println(s" [0] SHUTDOWN TERMINAL${RESET}")

      val option = readLine(s"\n${GREEN}COMMAND > ${RESET}").toIntOption.getOrElse(-1)

      option match
        case 0 =>
          println(s"${GREEN}SYSTEM SHUTDOWN INITIATED... TERMINAL OFFLINE${RESET}")
          running = false
        case 1 => listRocketsInit()
        case 2 => addRocketInit()
        case 3 => editRocketInit()
        case 4 => startExpeditionInit()
        case 5 => saveRocketsInit()
        case 6 => loadRocketsInit()
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

    // ---- Select Parts ----
    val chosenParts = selectParts()

    // ---- Fuel Input ----
    val fuel = readLine(s"${GREEN}ENTER FUEL MASS (KG) > ${RESET}")
      .toDoubleOption.getOrElse(0.0)

    val rocket = Rocket(id, name, chosenParts, fuelMassKg = fuel, maxSpeed = 0)

    RocketManager.addRocket(rocket)
    println(s"${GREEN}\nROCKET '${rocket.name}' REGISTERED SUCCESSFULLY.\n${RESET}")


  def editRocketInit(): Unit =
    println(s"\n${GREEN}${INVERT} ROCKET EDITOR ${RESET}")
    val rockets = RocketManager.getAllRockets()
    if rockets.isEmpty then
      println(s"${GREEN}NO ROCKETS AVAILABLE TO EDIT.${RESET}")
    else
      rockets.foreach(r => println(s"${GREEN}ID: ${r.rID}, Name: ${r.name}${RESET}"))

      val id = readLine(s"${GREEN}ENTER ROCKET ID TO EDIT > ${RESET}")
        .toIntOption.getOrElse(-1)

      RocketManager.findRocket(id) match
        case Some(rocket) =>
          println(s"${GREEN}Editing '${rocket.name}'${RESET}")
          println(s"[1] Change Name")
          println(s"[2] Change Fuel")
          println(s"[3] Change Parts")
          println(s"[0] Cancel")

          val choice = readLine(s"${GREEN}SELECT OPTION > ${RESET}")
            .toIntOption.getOrElse(0)

          choice match
            case 1 =>
              val newName = readLine(s"${GREEN}NEW NAME > ${RESET}")
              RocketManager.updateRocket(rocket.copy(name = newName))
              println(s"${GREEN}Name updated.${RESET}")

            case 2 =>
              val newFuel = readLine(s"${GREEN}NEW FUEL MASS (KG) > ${RESET}")
                .toDoubleOption.getOrElse(rocket.fuelMassKg)
              RocketManager.updateRocket(rocket.copy(fuelMassKg = newFuel))
              println(s"${GREEN}Fuel updated.${RESET}")

            case 3 =>
              val newParts = selectParts()
              RocketManager.updateRocket(rocket.copy(parts = newParts))
              println(s"${GREEN}Parts updated.${RESET}")

            case _ => println(s"${GREEN}EDIT CANCELLED.${RESET}")

        case None =>
          println(s"${GREEN}ROCKET WITH ID $id NOT FOUND.${RESET}")


  def startExpeditionInit(): Unit =
    println(s"\n${GREEN}${INVERT} EXPEDITION LAUNCH MODULE ${RESET}")
      val rockets = RocketManager.getAllRockets()
      if rockets.isEmpty then
        println(s"${GREEN}NO ROCKETS AVAILABLE.${RESET}")
      else
        // List available rockets
        rockets.foreach(r => println(s"${GREEN}ID: ${r.rID}, Name: ${r.name}${RESET}"))

        val id = readLine(s"${GREEN}ENTER ROCKET ID TO LAUNCH > ${RESET}")
          .toIntOption.getOrElse(-1)

        RocketManager.findRocket(id) match
          case Some(rocket) =>
            val hasAllCategories = PartCategory.values.forall(cat =>
              rocket.parts.exists(_.category == cat)
            )

            if !hasAllCategories then
              println(
                s"""
                  |${YELLOW}${INVERT} EXPEDITION ABORTED ${RESET}
                  |Rocket '${rocket.name}' is missing one or more required component categories.
                  |
                  |Required categories:
                  | - ${PartCategory.values.mkString(", ")}
                  |
                  | Ensure your rocket has exactly one part from each before launching.
                  |""".stripMargin
              )
            else
              // Proceed to planet selection
              println(s"\n${GREEN}${INVERT} DESTINATION SELECTION ${RESET}")
              PlanetData.allPlanets.foreach(p =>
                println(s"${GREEN}[${p.pID}] ${p.name} — Distance: ${p.distanceToEarth} million km${RESET}")
              )
              val pChoice = readLine(s"${GREEN}ENTER PLANET ID > ${RESET}")
                .toIntOption.getOrElse(-1)

              PlanetData.allPlanets.find(_.pID == pChoice) match
                case Some(planet) =>
                  println(s"${GREEN}Launching expedition simulation...${RESET}")
                  Thread.sleep(700)

                  val result = ExpeditionCalculator.simulateExpedition(rocket, planet)
                  println(s"${GREEN}${result.report}${RESET}")

                case None =>
                  println(s"${GREEN}INVALID PLANET SELECTION.${RESET}")

          case None =>
            println(s"${GREEN}ROCKET WITH ID $id NOT FOUND.${RESET}")

  def saveRocketsInit(): Unit =
    println(s"\n${GREEN}${INVERT} DATA STORAGE ${RESET}")
    if RocketManager.saveRockets() then
      println(s"${GREEN}Rocket fleet saved successfully to 'rockets.json'.${RESET}")
    else
      println(s"${GREEN}Error: Failed to save rocket data.${RESET}")

  def loadRocketsInit(): Unit =
    println(s"\n${GREEN}${INVERT} DATA RETRIEVAL ${RESET}")
    if RocketManager.loadRockets() then
      println(s"${GREEN}Rocket fleet loaded from 'rockets.json'.${RESET}")
    else
      println(s"${GREEN}No data found or failed to load file.${RESET}")


  def selectParts(): Seq[RocketPart] =
    var selectedParts: Seq[RocketPart] = Seq.empty

    println(s"\n${GREEN}${INVERT} PARTS SELECTION MODULE ${RESET}")

    for (category <- PartCategory.values) {
      println(s"\n${GREEN}--- ${category} ---${RESET}")
      val parts = RocketManager.availableParts(category)
      parts.zipWithIndex.foreach { case (p, idx) =>
        println(s"${GREEN}[${idx + 1}] ${p.name} (${p.massKg} kg)")
      }
      println(s"[0] SKIP ${category}")

      val choice = readLine(s"${GREEN}SELECT PART NUMBER > ${RESET}")
        .toIntOption.getOrElse(0)

      if choice > 0 && choice <= parts.size then
        selectedParts :+= parts(choice - 1)
        println(s"${GREEN}ADDED: ${parts(choice - 1).name}${RESET}")
      else
        println(s"${GREEN}NO PART SELECTED FOR ${category}.${RESET}")
    }

    selectedParts