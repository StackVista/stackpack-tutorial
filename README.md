# StackState StackPack tutorial

This repository contains a sample project that sets up a sample StackPack.

See the [complete tutorial](https://docs.stackstate.com/develop/tutorials) in the StackState documentation for more information.

## Building

The StackPack is built using the [SBT](https://www.scala-sbt.org/) tool.

In the main directory, compile the StackPack using:

```
sbt compile
```

## Testing

The StackPack comes with a small testing library that validates that the StackPack packaging is correct.

In the main directory, test the StackPack using:

```
sbt test
```

## Packaging

In the main directory, package the StackPack using:

```
sbt package
```

## Installing

Use the StackState CLI to install the tutorial StackPack:

```
sts-cli stackpack upload target/tutorial-0.0.2-master-SNAPSHOT.sts
```
