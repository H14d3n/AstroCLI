package rocketbuilder

case class Planet(
                   pID: Int,
                   name: String,
                   mass: Double,            // in 10^24 kg
                   radius: Double,          // in km
                   density: Double,         // in g/cm³
                   distanceToEarth: Double  // Approx. Distance in 10^6 km
                 )

// ------------------------------------
// Planets
// ------------------------------------

object PlanetData {
  val mercury = Planet(
    pID = 1,
    name = "Mercury",
    mass = 0.330,
    radius = 2439.7,
    density = 5.43,
    distanceToEarth = 91.7
  )

  val venus = Planet(
    pID = 2,
    name = "Venus",
    mass = 4.87,
    radius = 6051.8,
    density = 5.24,
    distanceToEarth = 41.4
  )

  val mars = Planet(
    pID = 4,
    name = "Mars",
    mass = 0.642,
    radius = 3389.5,
    density = 3.93,
    distanceToEarth = 78.3
  )

  val jupiter = Planet(
    pID = 5,
    name = "Jupiter",
    mass = 1898.0,
    radius = 69911.0,
    density = 1.33,
    distanceToEarth = 628.7
  )

  val saturn = Planet(
    pID = 6,
    name = "Saturn",
    mass = 568.0,
    radius = 58232.0,
    density = 0.69,
    distanceToEarth = 1275.0
  )

  val uranus = Planet(
    pID = 7,
    name = "Uranus",
    mass = 86.8,
    radius = 25362.0,
    density = 1.27,
    distanceToEarth = 2723.0
  )

  val neptune = Planet(
    pID = 8,
    name = "Neptune",
    mass = 102.0,
    radius = 24622.0,
    density = 1.64,
    distanceToEarth = 4351.0
  )

  val allPlanets = Seq(mercury, venus, mars, jupiter, saturn, uranus, neptune)
}
