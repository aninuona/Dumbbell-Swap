# Dumbbell Swap - Android App

A Kotlin-based Android application that helps users find dumbbell-based alternatives to gym exercises when they have limited equipment.

## Project Overview

**App Name:** Dumbbell Swap  
**Target API:** API 36.1 (Android 15+)  
**Minimum API:** API 24 (Android 7.0 - Nougat)  
**Language:** Kotlin  
**Architecture:** MVC + Room Database

## Features

**Exercise Search** Auto-complete search for gym exercises  
**Dumbbell Inventory** Track which dumbbells you own  
**Smart Filtering** Results filtered by your max weight  
**Local Caching** Room Database stores exercises  
**Bottom Navigation** Easy access to Search and Inventory tabs  

## Project Structure

```
app/src/main/
├── java/com/example/dumbbellswap/
│   ├── MainActivity.kt                    # Main activity container
│   ├── database/
│   │   ├── Entities.kt                   # Exercise & InventoryItem data classes
│   │   ├── Daos.kt                       # Database access objects
│   │   └── AppDatabase.kt                # Room database configuration
│   ├── repository/
│   │   └── ExerciseRepository.kt         # Data access layer
│   ├── ui/
│   │   ├── fragment/
│   │   │   ├── SearchFragment.kt         # Search screen
│   │   │   ├── ResultsFragment.kt        # Results display
│   │   │   └── InventoryFragment.kt      # Inventory management
│   │   ├── viewmodel/
│   │   │   └── ExerciseViewModel.kt      # UI state management
│   │   └── adapter/
│   │       └── Adapters.kt               # RecyclerView adapters
├── res/
│   ├── layout/
│   │   ├── activity_main.xml             # Main layout
│   │   ├── fragment_search.xml           # Search screen layout
│   │   ├── fragment_results.xml          # Results screen layout
│   │   ├── fragment_inventory.xml        # Inventory screen layout
│   │   ├── item_result.xml               # Result card
│   │   └── item_inventory.xml            # Inventory item
│   ├── navigation/
│   │   └── nav_graph.xml                 # Navigation configuration
│   ├── menu/
│   │   └── bottom_nav_menu.xml           # Bottom navigation menu
│   ├── values/
│   │   ├── strings.xml                   # String resources
│   │   ├── colors.xml                    # Color definitions
│   │   └── themes.xml                    # Theme configuration
│   └── drawable/
│       ├── bg_search_input.xml           # Search input background
│       ├── ic_search.xml                 # Search icon
│       └── ic_settings.xml               # Settings icon
└── AndroidManifest.xml                   # App configuration

build.gradle.kts                           # Dependencies & build config
```

## Setup Instructions

### Prerequisites
- Android Studio (latest)
- SDK 36 installed
- Kotlin plugin enabled

### Installation Steps

1. **Create a new Android Project in Android Studio**
   - Choose "Empty Activity"
   - Set Minimum SDK to API 24
   - Choose Kotlin as language

2. **Copy all Kotlin files** to these paths:
   ```
   app/src/main/java/com/example/dumbbellswap/
   ├── MainActivity.kt
   ├── database/
   │   ├── Entities.kt
   │   ├── Daos.kt
   │   └── AppDatabase.kt
   ├── repository/
   │   └── ExerciseRepository.kt
   └── ui/
       ├── fragment/
       │   ├── SearchFragment.kt
       │   ├── ResultsFragment.kt
       │   └── InventoryFragment.kt
       ├── viewmodel/
       │   └── ExerciseViewModel.kt
       └── adapter/
           └── Adapters.kt
   ```

3. **Copy all XML layout files** to:
   ```
   app/src/main/res/layout/
   ├── activity_main.xml
   ├── fragment_search.xml
   ├── fragment_results.xml
   ├── fragment_inventory.xml
   ├── item_result.xml
   └── item_inventory.xml
   ```

4. **Copy navigation file** to:
   ```
   app/src/main/res/navigation/nav_graph.xml
   ```

5. **Copy menu file** to:
   ```
   app/src/main/res/menu/bottom_nav_menu.xml
   ```

6. **Copy drawable files** to:
   ```
   app/src/main/res/drawable/
   ├── bg_search_input.xml
   ├── ic_search.xml
   └── ic_settings.xml
   ```

7. **Copy values files** to:
   ```
   app/src/main/res/values/
   ├── strings.xml
   ├── colors.xml
   └── themes.xml
   ```

8. **Update AndroidManifest.xml** - Replace entire file with provided version

9. **Update build.gradle.kts (Module: app)** - Replace with provided version

10. **Sync Gradle** - Let Android Studio download dependencies

### Running the App

1. Select a virtual device (API 36 recommended)
2. Click **Run** or press `Shift + F10`
3. Wait for the app to build and install

## User Flow

1. **Search Tab** (Home)
   - User enters exercise name (e.g., "Lat Pulldown")
   - Auto-complete suggestions appear
   - Click "Find Swaps" to search

2. **Results Tab**
   - Shows filtered dumbbell alternatives
   - Displays exercise name, target muscle, required weight
   - Results filtered by user's max inventory weight

3. **Inventory Tab**
   - Toggle switches for each dumbbell weight (5-30 lbs)
   - Changes persist to local database
   - Max weight automatically updates search filters

## Sample Data

Pre-populated exercises include:
- Lat Pulldown → Dumbbell Pullover / Renegade Row
- Barbell Bench Press → Dumbbell Bench Press
- Leg Extension → Dumbbell Goblet Squat
- Shoulder Press → Dumbbell Shoulder Press
- And more...

## Technologies

- **Room Database** Local persistence for exercises & inventory
- **Kotlin Coroutines** Async operations
- **Navigation Component** Fragment-based navigation
- **ViewModel & LiveData** UI state management
- **Material Design 3** Modern UI components

## Dependencies

- AndroidX Core: 1.12.0
- Material Components: 1.11.0
- Room: 2.6.1
- Navigation: 2.7.6
- Lifecycle: 2.6.2
- Fragment: 1.6.2

## Known Limitations

- No API integration (uses pre-populated sample data)
- Simplified filtering logic (can be expanded)
- Max 6 weight options (easily extensible)

## Future Enhancements

1. Add Glide library for exercise GIF animations
2. Integrate ExerciseDB API for real exercise data
3. Add muscle group heatmap visualization
4. Implement user profiles & saved routines
5. Add weight unit conversion (lbs ↔ kg)
6. Create favorites/history system

## Troubleshooting

**App crashes on startup?**
- Check that Room is properly initialized
- Verify all database classes are in correct packages
- Ensure build.gradle.kts has KAPT plugin enabled

**Search not working?**
- Verify sample data was initialized in ViewModel
- Check that SearchFragment properly observes LiveData
- Confirm database queries match table names

## Credits
Creator: github.com/aninuona
