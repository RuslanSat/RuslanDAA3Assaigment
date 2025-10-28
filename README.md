# Minimum Spanning Tree Algorithms – City Transportation Network Optimization

## Overview

This project demonstrates the use of **Prim’s** and **Kruskal’s** algorithms to compute the **Minimum Spanning Tree (MST)** for optimizing a city’s transportation network.
The objective is to connect all city districts with the **lowest possible total construction cost**.

## Requirements

* **Java 11 or higher**

## Running the Program

### Compile:

```bash
javac -d out Tree/src/*.java
```

### Run:

```bash
java -cp out Main
```

##Runing should be only manually

## Program Functionality

The program:

1. Loads several test graphs representing different city layouts.
2. Executes both **Prim’s** and **Kruskal’s** algorithms.
3. Records performance data — number of operations and execution time.
4. Compares both algorithms to confirm identical MST cost.
5. Displays detailed results for analysis.

## Example Output

```
Graph 2 Results:
  Vertices: 4, Edges: 5

  Prim's Algorithm:
    MST Cost: 6
    Operations: 38
    Time: 0,10 ms
    MST Edges: [A-B(1), B-C(2), C-D(3)]

  Kruskal's Algorithm:
    MST Cost: 6
    Operations: 97
    Time: 0,05 ms
    MST Edges: [A-B(1), B-C(2), C-D(3)]
```

## Project Structure

```
Tree/
src/
├──ass_3_input.json
├──ass_3_output.json
├── Main.java              # Entry point – builds and tests sample graphs
├── PrimsAlgorithm.java    # Prim’s algorithm implementation
├── KruskalsAlgorithm.java # Kruskal’s algorithm implementation
└── MSTResult.java         # Data structure for storing MST results
```

## Features

✅ Implementations of **both MST algorithms**
✅ **Operation counting** for performance measurement
✅ **Execution time tracking** for efficiency comparison
✅ **Readable, well-documented code** for educational use
✅ **No external libraries** – pure Java solution

## Algorithm Details

### Prim’s Algorithm

* Employs a **priority queue** (min-heap) to select the smallest edge.
* Expands the MST from a starting vertex.
* Complexity: **O(E log V)** time, **O(V)** space.

### Kruskal’s Algorithm

* Sorts all edges by weight and applies a **Union-Find** structure for cycle detection.
* Adds edges greedily if no cycle is formed.
* Complexity: **O(E log E)** time, **O(V + E)** space.

## Analysis

A detailed analytical comparison of both algorithms is available in
**`REPORT.md`**.

## Author
Ruslan Satvaldiev SE-2407

Developed for **Assignment 3: Optimization of a City Transportation Network**.
