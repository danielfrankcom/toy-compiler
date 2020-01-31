# CSC 435 Compiler

## Prerequisites

- An installation of Java (8+)
- A Unix environment

## Building files & running tests

This project is built using Gradle, however it contains a `Makefile` wrapper for convenience.

### Commands

Any of the following commands will build the project and run the tests:

`make`

`make build`

`./gradlew build`

### Cleaning up

If there are conflicts between new files and output artifacts from a previous build, the project can be cleaned using any of the following commands:

`make clean`

`./gradlew clean`

Using the `Makefile` will always perform a clean operation before a new build, so this is not likely to be an issue in practice.

### Troubleshooting

Gradle handles its own installation and dependencies when the `./gradlew` script is called (it is executed from the `Makefile` too). Providing a Java 8 installation is present on the host system, this should work, however there may be some edge-cases that require resolution. If the `Makefile` is not working, try the `./gradlew` commands directly. If this still doesn't work, then it may be necessary to perform the compilation manually.

Here are the locations for the relevant sourcefiles:

ANTLR grammar: `src/main/antlr`

Java source: `src/main/java/om/frankc/csc435/compiler/`

Java test source: `src/test/java/om/frankc/csc435/compiler/`

Java test resources: `src/test/resources/`

There may or may not be a conflict if an ANTLR jar file is present in the host `CLASSPATH` environment variable, as Gradle pulls its own ANTLR dependency from the web. If this is an issue, removing the jar from the `CLASSPATH` variable should resolve the problem.

I have experienced issues with Gradle when working behind a corporate proxy before, so if you are in a similarly constrained environment you may have to add you corporations certificates to the Java keystore.

## Compiling files

There is a `compile` script in the root directory of this project, which provides a set of convenient operations that can be run against the compiler. This script will perform the build process on your behalf if it has not already been executed.

### Usage

To check if an input program will compile (pass/fail), use the following command:

`./compile <input-file-path>`

To pretty-print an input program use the following command:

`./compile <input-file-path> <pretty-output-file-path>`