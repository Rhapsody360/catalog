# Polynomial Solver

This Java program calculates the constant term \( c \) of an unknown polynomial of degree \( m \) based on given roots provided in a JSON format. It reads the JSON file, decodes the values, and uses Lagrange interpolation to determine the constant term.

## Prerequisites

- **Java 9 or later** is required to run this program, as it uses the `org.json` library for JSON parsing, which is included in later versions of Java. You can check your Java version by running:
  ```bash
  java -version
