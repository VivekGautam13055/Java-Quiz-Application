# Java Quiz Application

A beginner-friendly desktop quiz application built with Java Swing.

## Features
- Multiple-choice Java questions with four options each
- One question displayed at a time
- Prevents moving forward without selecting an answer
- Automatic score and percentage calculation
- Result screen and restart option

## Project structure

```text
QuizApplication/
├── src/
│   ├── Main.java          # Starts the application
│   ├── Question.java     # Stores one question and its options
│   ├── Quiz.java         # Manages questions, progress and score
│   └── QuizFrame.java    # Builds and controls the Swing interface
└── README.md
```

## Java concepts used
- Classes, objects and encapsulation
- Arrays and ArrayList / List
- Methods and constructors
- Loops and conditional statements
- Java Swing components and event handling

## Requirements
- JDK 17 or later

## Compile and run
Open a terminal in the `QuizApplication` folder and run:

```bash
javac -d out src/*.java
java -cp out Main
```

No external libraries are required.
