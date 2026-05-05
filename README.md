# WACC Compiler

This repository contains a complete compiler pipeline for the WACC teaching language, implemented in Scala. It takes a `.wacc` source file through lexing, parsing, semantic analysis, and ARM assembly generation, then writes the resulting program as a `.s` file.

The codebase has moved well beyond the starter template. The implemented work here is a real end-to-end compiler with a custom frontend, typed AST, semantic checker, intermediate representation, ARM code generator, runtime helper routines, and an example-driven automated test suite.

## What This Repo Solves

The main problems solved in this project are:

- Turning WACC source programs into a structured AST with operator precedence, function parsing, nested scopes, and source positions.
- Rejecting invalid programs early with syntax failures and semantic errors, including undefined identifiers, duplicate functions, invalid return paths, type mismatches, bad function calls, and illegal array/pair usage.
- Lowering valid WACC programs into ARM11-style assembly with stack management, heap allocation, control flow labels, function calls, I/O, and runtime safety checks.
- Generating the runtime support code needed for printing, reading, division, bounds checks, null-pair checks, freeing pairs, and overflow handling.
- Verifying the compiler across valid programs, invalid programs, and backend execution tests against expected output.

## Implemented Compiler Stages

### 1. Lexing

`src/main/scala/wacc/Lexer.scala` defines the WACC lexer using `parsley`. It covers:

- WACC keywords such as `begin`, `if`, `while`, `call`, `newpair`, `fst`, `snd`, `return`
- Operators including arithmetic, comparison, equality, and boolean operators
- Identifiers with underscore support
- Character and string literals with escape handling
- Line comments starting with `#`

### 2. Parsing

`src/main/scala/wacc/Parser.scala` builds the grammar and produces a typed AST from `src/main/scala/wacc/Syntax.scala`.

The parser supports:

- Program blocks and nested scopes
- Function declarations and calls
- Variable declarations and assignments
- Arrays and pairs
- `if/then/else`, `while/do/done`, `print`, `println`, `read`, `free`, `exit`, `return`
- Unary operators: `!`, unary `-`, `len`, `ord`, `chr`
- Binary operators with defined precedence and associativity

### 3. Semantic Analysis

`src/main/scala/wacc/Semantic.scala` and `src/main/scala/wacc/SemanticTools.scala` implement semantic checking on top of a nested symbol table.

The semantic phase handles:

- Function symbol collection before body checking
- Nested scope lookup for variables and functions
- Type checking for expressions and assignments
- Function argument count and type validation
- Array index and dimension validation
- Pair construction and pair element access checks
- Return-path validation for functions
- Aggregated semantic error reporting

The compiler exits with:

- `0` for successful compilation
- `100` for syntax failures
- `200` for semantic failures

### 4. IR and Code Generation

`src/main/scala/wacc/Generator.scala`, `src/main/scala/wacc/IR.scala`, `src/main/scala/wacc/Instructions.scala`, and `src/main/scala/wacc/AssemblyWriter.scala` implement the backend.

The backend includes:

- A custom instruction IR
- Register and stack-slot tracking via `MemLocGenerator`
- Translation of statements, expressions, functions, scopes, arrays, and pairs
- Heap allocation with `malloc`
- Runtime deallocation with `free`
- ARM assembly emission for the main program, functions, data section, and helper routines

Runtime helper templates are generated for:

- Integer, boolean, character, string, and pointer printing
- `read int` and `read char`
- Array load/store and bounds checking
- Division/modulo by zero checks
- Integer overflow checks
- Null-pair dereference/free checks
- Pair freeing

## Supported WACC Features

Based on the AST, semantic rules, and backend generation in this repository, the compiler supports:

- Base types: `int`, `bool`, `char`, `string`
- Compound types: arrays and pairs
- Variable declarations and reassignment
- Function definitions, parameters, returns, and calls
- Arithmetic, relational, equality, and boolean expressions
- Control flow with conditionals and loops
- I/O with `print`, `println`, and `read`
- Heap-allocated arrays and pairs
- Pair element access with `fst` and `snd`
- `free`, `exit`, `len`, `ord`, and `chr`

## Tech Stack

- Scala `2.13.10`
- SBT for build and test orchestration
- `parsley 4.2.8` for lexer/parser combinators
- ScalaTest `3.2.15` for automated tests
- ARM assembly as the backend target
- `arm-linux-gnueabi-gcc` and `qemu-arm` for backend execution tests

The packaged compiler jar is built as `wacc-15-compiler.jar`.

## Testing

The test suite is substantial and split by concern:

- `SeparateTestsSyntax.scala`: valid syntax programs
- `SeparateTestsSemantic.scala`: valid programs that must pass semantic analysis
- `SeparateTestsSynI.scala`: invalid syntax programs
- `SeparateTestsSemI.scala`: invalid semantic programs
- `GeneratedBackendTests.scala`: backend execution tests that compile, assemble, and run generated ARM code
- `IntegrationTest.scala`: end-to-end smoke test through `make`, `./compile`, assembly, and execution
- `MiscUnitTests.scala`: small IR/runtime invariants

The generated suites alone contain 799 example-based tests, and the additional integration/unit tests push the repository over 800 test cases.

## Build And Run

Build the compiler:

```bash
make
```

Or directly through SBT:

```bash
sbt compile assembly
```

Compile a WACC program:

```bash
./compile path/to/program.wacc
```

This produces:

```text
program.s
```

Run tests:

```bash
sbt test
```

## Important Repo Notes

- The root `wacc_examples/` directory is empty in this checkout. The test suites are written to use the standard WACC example corpus, so you will need that corpus present for the example-based tests to run successfully.
- Backend execution tests also require an ARM cross-toolchain and emulator, specifically `arm-linux-gnueabi-gcc` and `qemu-arm`.
- The root `compile` script is a thin wrapper around the assembled jar and is the expected entrypoint for running the compiler.

## Repository Map

- `src/main/scala/wacc/Main.scala`: compiler entrypoint
- `src/main/scala/wacc/Lexer.scala`: token definitions
- `src/main/scala/wacc/Parser.scala`: grammar and parser
- `src/main/scala/wacc/Syntax.scala`: AST definitions
- `src/main/scala/wacc/Semantic.scala`: semantic analysis
- `src/main/scala/wacc/Generator.scala`: AST to IR/codegen lowering
- `src/main/scala/wacc/IR.scala`: IR model and runtime templates
- `src/main/scala/wacc/Instructions.scala`: backend instruction model
- `src/main/scala/wacc/AssemblyWriter.scala`: ARM assembly emission
- `src/main/scala/wacc/Writer.scala`: `.s` file output
- `src/test/scala/wacc`: test suites

## Summary

This repository implements an end-to-end WACC compiler targeting ARM assembly. The strongest completed work in the codebase is the full frontend and backend pipeline: grammar-driven parsing, semantic validation with symbol tables and type rules, assembly generation with runtime safety helpers, and broad automated coverage around valid, invalid, and executable programs.
