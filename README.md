# AstroCLI - Beyond Horizons 🚀

A terminal-based rocket builder and space expedition simulator written in Scala 3. Design custom rockets, manage your fleet, and launch expeditions to explore the solar system!

## Overview

AstroCLI is an interactive command-line application that allows you to:
- **Design custom rockets** by selecting parts from various categories
- **Manage a rocket fleet** with save/load functionality
- **Simulate space expeditions** to different planets in our solar system
- **Track expedition performance** including fuel consumption, travel time, and mission success

The simulation uses simplified physics calculations based on rocket components, fuel efficiency, and distance to create an engaging space exploration experience.

## Features

### 🔧 Rocket Building
- Build rockets by assembling parts from 5 categories:
  - **Payload**: Fairings, satellites, crew capsules
  - **Control**: Avionics systems, grid fins, reaction control thrusters
  - **Structure**: Airframes, landing legs, heat shields
  - **Propulsion**: Various engine types (Merlin, Raptor, Ion Drive, Nuclear Thermal, Solid Booster)
  - **Tanks**: Fuel storage systems of different sizes and types

- Each part has:
  - Mass (kg) that affects total rocket weight
  - Category for proper classification
  - Unique properties that influence performance

### 🚀 Fleet Management
- Register new rockets with custom names and configurations
- View all rockets in your fleet with detailed specifications
- Edit existing rockets (name, fuel, parts)
- Persistent storage via JSON files

### 🌍 Space Expeditions
- Launch expeditions to 7 planets:
  - Mercury, Venus, Mars, Jupiter, Saturn, Uranus, Neptune
- Real-time simulation calculates:
  - Travel time based on distance and rocket speed
  - Fuel consumption based on efficiency and distance
  - Mission success probability
  - Random variance for realistic outcomes

### 💾 Data Persistence
- Save your entire rocket fleet to `rockets.json`
- Load previously saved fleets
- JSON format for easy inspection and modification

## Project Structure

The codebase is organized into 5 main Scala files:

### `main.scala`
Entry point of the application. Initializes and starts the console UI.

### `rockets.scala`
Core data models and fleet management:
- **PartCategory**: Enum defining 5 rocket component categories
- **RocketPart**: Case class representing individual components (name, mass, category)
- **Rocket**: Case class for complete rockets with parts, fuel, and calculated properties
- **RocketManager**: Singleton managing the rocket fleet with methods for:
  - CRUD operations on rockets
  - JSON serialization/deserialization using upickle
  - A catalog of 25+ available parts to choose from

### `consoleUI.scala`
Terminal user interface with a retro-futuristic aesthetic:
- **ConsoleUI**: Main UI class with color-coded terminal output
- Implements 6 main menu options:
  1. List rocket fleet
  2. Register new rocket
  3. Edit existing rocket
  4. Start expedition
  5. Save fleet to file
  6. Load fleet from file
- Interactive part selection system
- Input validation and error handling

### `calculation.scala`
Expedition simulation engine:
- **ExpeditionCalculator**: Object containing simulation logic
- **speedBonus()**: Calculates rocket speed based on installed parts
- **fuelEfficiency()**: Determines fuel consumption rate
- **simulateExpedition()**: Core simulation function that:
  - Calculates travel distance and time
  - Applies random variance for realism
  - Determines mission success based on fuel, parts, and requirements
- **ExpeditionResult**: Data container for simulation outcomes with formatted reports

### `planets.scala`
Planetary data and definitions:
- **Planet**: Case class with physical properties (mass, radius, density, distance from Earth)
- **PlanetData**: Object containing data for 7 planets in the solar system
- Distances in millions of km for expedition planning

## How It Works

### Rocket Design
1. Each rocket must have at least one part from each category for a complete design
2. Part selection affects performance:
   - **Engines** provide speed bonuses (Ion Drive: +12,000 km/h, Raptor: +7,000 km/h)
   - **Lightweight** components increase speed by 1,500 km/h
   - **Heavy-Duty** components decrease speed by 1,500 km/h
   - **Advanced Avionics** provide +1,000 km/h speed boost
   - **Cryogenic tanks** improve fuel efficiency by 15%

### Expedition Simulation
1. **Speed Calculation**: Base speed (12,000 km/h) + bonuses from parts
2. **Travel Time**: Distance to planet / effective speed
3. **Fuel Requirements**: (Distance / 5000) × fuel efficiency factor
4. **Success Criteria**:
   - Must have sufficient fuel (≥90% of required)
   - Must have parts installed
   - Must have at least one propulsion component
5. **Random Variance**: ±15% variation for realistic unpredictability

## Building and Running

### Prerequisites
- Scala 3.3.6
- sbt (Scala Build Tool)

### Dependencies
- **circe**: JSON parsing (though upickle is actually used)
- **upickle**: JSON serialization/deserialization

### Build Commands
```bash
# Compile the project
sbt compile

# Run the application
sbt run

# Create a package
sbt package
```

### Running the Application
```bash
sbt run
```

The application will display the AstroCLI banner and present a menu with 6 options.

## Usage Example

```
 SYSTEM MENU 
 [1] LIST ROCKET FLEET
 [2] REGISTER NEW ROCKET
 [3] EDIT ROCKET
 [4] START EXPEDITION
 [5] SAVE FLEET TO FILE
 [6] LOAD FLEET FROM FILE
 [0] SHUTDOWN TERMINAL

COMMAND > 2
```

1. **Register a New Rocket**: Select option 2, name your rocket, choose parts from each category, and specify fuel amount
2. **Launch an Expedition**: Select option 4, choose your rocket, select destination planet
3. **View Results**: See detailed expedition report with fuel usage, travel time, and mission status
4. **Save Your Fleet**: Select option 5 to persist your rockets to `rockets.json`

## Technical Details

### Design Patterns
- **Singleton Pattern**: RocketManager manages global rocket fleet state
- **Case Classes**: Immutable data structures for Rocket, RocketPart, Planet
- **Enum**: Type-safe PartCategory with 5 values
- **Functional Programming**: Extensive use of Scala collections (map, filter, find, exists)

### Data Storage
- JSON format using upickle library
- Custom serializers for enum types
- Automatic marshalling/unmarshalling of nested case classes

### Terminal Styling
- ANSI escape codes for colors and formatting
- Green terminal aesthetic for retro-futuristic feel
- Inverted colors for section headers
- Clear visual separation between menu sections

## License

This project is open source. See LICENSE file for details.