# 🚀 AstroCLI — Beyond Horizons

**AstroCLI** is a Scala-based console simulation game that lets you **design, manage, and launch rockets** to distant planets in our solar system.  
As a mission commander, you’ll assemble rockets from modular parts, manage resources, and simulate expeditions to explore the stars.

#### Made by Tizian & David
---

## 🪐 Features

- **Interactive Terminal Interface**  
  Classic command-line UI with color-coded menus and an ASCII-art banner for an authentic control-center feel.  

- **Rocket Fleet Management**  
  - Create, list, edit, and manage your rocket fleet.  
  - Customize rockets with unique parts across multiple categories.  
  - Save and load your fleet to/from a JSON file for persistence.  

- **Part System**  
  Choose components from these key categories:
  - Payload  
  - Control  
  - Structure  
  - Propulsion  
  - Tanks  

- **Planetary Expeditions**  
  Launch your rockets toward planets such as **Mars, Venus, Jupiter**, and others — each with unique mass, distance, and travel difficulty.

- **Simulation Engine**  
  Calculates:
  - Rocket speed and performance bonuses  
  - Fuel efficiency and usage  
  - Travel time and mission outcome (success/failure)  

---

## 🧩 Project Structure
```
AstroCLI/
├── main.scala # Initialises application
├── consoleUI.scala # Main user interface (AstroCLI)
├── rockets.scala # Handles rocket data and persistence
├── planets.scala # Contains planet definitions and data
├── calculation.scala # Simulation logic for expeditions
└── build.sbt # Scala build configuration (if using SBT)
```
---
### 💾 Use the in-terminal menu to interact with the system:
```
[1] List Rocket Fleet
[2] Register New Rocket
[3] Edit Rocket
[4] Start Expedition
[5] Save Fleet to File
[6] Load Fleet from File
[0] Shutdown Terminal
```

### 🧠 Notes

* Each rocket must include one part from every category before launch.
* Missing categories will result in expedition abort.
* The simulation is for fun and educational use only, not real-world physics.
