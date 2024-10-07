
# Firefight - A Turn-Based Firefighting Game
![image](https://github.com/user-attachments/assets/0af3e0a7-c803-4688-aa65-ba64674da961)

## Overview
Firefight is a turn-based strategy game built with Java and JavaFX for GUI. The game leverages object-oriented programming principles to create a dynamic and engaging firefighting simulation. Players assume the role of a firefighter tasked with extinguishing fires, managing resources, and utilizing vehicles to prevent the spread of fires across various terrains.

## Game Features
- **Turn-Based Gameplay:** Make strategic moves to extinguish fires while planning ahead to prevent further outbreaks.
- **Dynamic Fire Spread:** As you progress through the levels, the fire spreads more aggressively, challenging your firefighting skills.
- **Multiple Levels and Terrain Types:** Navigate through different terrains and face increasing difficulty as you advance.
- **Vehicles and Tools:** Utilize a variety of tools and vehicles like bulldozers, fire trucks, and fireman bots to control and extinguish fires.
- **Water Plane Support:** Call a water plane with the press of the `P` key to quickly put out large areas of fire.
- **Scoring System:** Earn points for extinguishing fires and strategically using resources. Save your scores and compare with others.

## Installation
To run the game locally, you'll need to have Java and Scala installed on your system.

1. Clone the repository:
   ```bash
   git clone https://github.com/silvasalavdor/firefighter.git
   ```
2. Navigate to the project directory:
   ```bash
   cd firefighter
   ```
3. Compile and run the game using your preferred IDE or build tool (e.g., sbt or IntelliJ IDEA).

## How to Play
1. **Start the Game:** Launch the game and enter your username.
2. **Control the Firefighter:**
   - Move the firefighter using the arrow keys.
   - Step on fires to extinguish them.
3. **Use Vehicles:**
   - Deploy bulldozers to clear terrain and create firebreaks.
   - Use fire trucks to extinguish multiple fires at once.
4. **Call the Water Plane:** Press `P` to call a water plane that drops water on the fire.
5. **Level Up:** Complete the level by extinguishing all fires. New challenges and vehicles become available as you progress.

## Game Mechanics
- **Fire Propagation:** Fires spread to neighboring tiles at the end of each turn. The speed and intensity of the spread increase with each level.
- **Vehicles and Tools:**
  - **Bulldozer:** Clears vegetation, creating firebreaks to prevent fire spread.
  - **Fire Truck:** Extinguishes fires in multiple adjacent tiles.
  - **Fireman Bot:** Automatically searches and extinguishes fires.
- **Exploding Barrels:** On higher levels, barrels can catch fire and explode, causing additional fires.

## Technologies Used
- **Java:** Core game logic and object-oriented design.
- **JavaFX:** User interface and game rendering.
- **Object-Oriented Design Patterns:** Implemented for game entities, interactions, and event handling.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
