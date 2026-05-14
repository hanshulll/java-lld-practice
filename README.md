# Java LLD & Machine Coding Practice

This repository contains Java implementations for LLD and machine coding practice.

## Structure
- case-studies/ → problem-wise solutions
- patterns/ → standalone design pattern examples
- shared/ → reusable helpers and models
- templates/ → problem/design/checklist templates

## How to use
1. Pick a case study
2. Start with v1
3. Refactor into v2/final
4. Add notes and test cases

## Topics covered
- OOP
- SOLID
- Design patterns
- Low-level design
- Machine coding

## Build Automation Tool
Used a common root **Gradle** build for the repo, and keep per-module build.gradle files only if needed.

### Root-level Gradle
Have:
<br>**settings.gradle** at root
<br>**build.gradle** at root

### How it works

#### Root `settings.gradle`
This defines all modules:

```text
rootProject.name = "java-lld-practice"

include("shared")
include("parking-lot")
include("splitwise")
include("elevator")
include("chess")
```

#### Root `build.gradle`
Put common things here:
- Java version
- repositories
- common dependencies
- test setup

#### Example:
```text
plugins {
    id 'java'
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply plugin: 'java'

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    dependencies {
        testImplementation 'org.junit.jupiter:junit-jupiter:5.10.2'
    }

    test {
        useJUnitPlatform()
    }
}
```

Now the issue with shared configuration is that how will it know which file is the main file in your case study folder and execute it to start the program. To solve this issue a `Better approach: module-specific application config` where a small config file is needed to be created inside your case study implementation folder.

For example, in `parking-lot/build.gradle`:
```text
plugins {
    id 'application'
}

application {
    mainClass = 'com.yourname.parkinglot.Main'
}

dependencies {
    implementation project(':shared')
}
```

Then run:
```text
./gradlew :parking-lot:run
```
Do this only for modules you actively want to run.


### When to add module-specific `build.gradle`

Only add one in a module if that module needs:

- extra dependencies
- different plugins
- special test setup

Example:

<br>`parking-lot/build.gradle`
<br>`splitwise/build.gradle`</br>

But if all modules are similar, you can avoid per-module build files entirely.

### Example module build.gradle

Only add this if a module needs extra dependencies.

`parking-lot/build.gradle`

```text
 dependencies {
    implementation project(':shared')
}
```

If a module does not need shared, you can leave it without a build.gradle.

## How to run

1. On mac first install gradle by running the following commands on terminal :

- brew update
- brew install gradle

2. Verify gradle installation and version

- gradle -v

3. Create all the config files manually or through some automation (tbh I did it manually)

   - build.gradle
   - gradle.properties
   - settings.gradle

4. After creating the files from step 3 use `gradle wrapper` command to generates the necessary files to include the Gradle Wrapper in a project. This creates a self-contained environment that allows anyone to build the project without having Gradle installed manually.

5. After steps 1 to 4 are executed successfully Use Gradle from the root:
```bash
./gradlew test
./gradlew :parking-lot:run
```


## Repository Structure Sample Tree
```text
java-lld-practice/
├── README.md
├── docs/
│   ├── approach-template.md
│   ├── design-template.md
│   └── checklist.md
├── shared/
│   ├── utils/
│   ├── exceptions/
│   └── common-models/
├── case-studies/
│   ├── parking-lot/
│   │   ├── v1/
│   │   │   ├── src/
│   │   │   ├── README.md
│   │   │   └── notes.md
│   │   ├── v2/
│   │   │   ├── src/
│   │   │   ├── README.md
│   │   │   └── notes.md
│   │   └── final/
│   │       ├── src/
│   │       ├── README.md
│   │       └── design.md
│   ├── splitwise/
│   │   ├── v1/
│   │   └── final/
│   ├── chess/
│   │   ├── v1/
│   │   └── final/
│   └── elevator/
│       ├── v1/
│       └── final/
├── patterns/
│   ├── singleton/
│   │   ├── src/
│   │   └── README.md
│   ├── factory/
│   ├── strategy/
│   ├── observer/
│   └── builder/
├── lld-notes/
├── machine-coding/
│   ├── online-assessment-style/
│   ├── interview-style/
│   └── timed-practice/
└── playground/
    ├── experiments/
    └── quick-tests/
```

## Java package template for each case study

```text
src/
└── com/yourname/<problem-name>/
    ├── Main.java
    ├── model/
    │   ├── Vehicle.java
    │   ├── Ticket.java
    │   ├── Floor.java
    │   └── ...
    ├── service/
    │   ├── ParkingService.java
    │   ├── AllocationService.java
    │   └── ...
    ├── repository/
    │   ├── TicketRepository.java
    │   └── ...
    ├── strategy/
    │   ├── PricingStrategy.java
    │   ├── SpotAllocationStrategy.java
    │   └── ...
    ├── factory/
    │   ├── VehicleFactory.java
    │   └── ...
    ├── handler/
    │   └── ...
    ├── exception/
    │   ├── ParkingFullException.java
    │   ├── InvalidTicketException.java
    │   └── ...
    └── util/
        └── ...
```

## For small machine coding problems

Keep it simple:

```text
com.yourname.tictactoe
├── Main.java
├── model
├── service
├── exception
└── util
```

## For larger LLD problems

Use more structure:

```text
com.yourname.parkinglot
├── Main.java
├── model
├── service
├── repository
├── strategy
├── factory
├── exception
├── controller
└── util
```

## Example: Parking Lot package layout

```text
src/
└── com/yourname/parkinglot/
    ├── Main.java
    ├── model/
    │   ├── ParkingLot.java
    │   ├── Floor.java
    │   ├── ParkingSpot.java
    │   ├── Vehicle.java
    │   ├── Ticket.java
    │   └── enums/
    │       ├── VehicleType.java
    │       └── SpotType.java
    ├── service/
    │   ├── ParkingLotService.java
    │   ├── EntryService.java
    │   └── ExitService.java
    ├── strategy/
    │   ├── SpotAllocationStrategy.java
    │   ├── ClosestSpotAllocationStrategy.java
    │   └── PricingStrategy.java
    ├── repository/
    │   └── TicketRepository.java
    ├── exception/
    │   ├── ParkingFullException.java
    │   └── TicketNotFoundException.java
    └── util/
        └── IdGenerator.java
```

## How to save multiple case studies cleanly

For each case study, follow this pattern:

```text
case-studies/<problem-name>/
├── v1/
│   ├── src/
│   ├── README.md
│   └── notes.md
├── v2/
│   ├── src/
│   ├── README.md
│   └── notes.md
└── final/
    ├── src/
    ├── README.md
    ├── design.md
    └── test-cases.md
```

### What each file should contain

<br>**README.md** → problem summary + how to run.
<br>**design.md** → classes, relationships, patterns used.
<br>**notes.md** → mistakes, improvements, interview learnings.
<br>**test-cases.md** → edge cases and sample scenarios.


## Goal

Practice:

- OOP design
- SOLID principles
- design patterns
- clean code
- interview-style machine coding



