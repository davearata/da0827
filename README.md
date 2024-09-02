## Project Overview

This project is a Java-based command-line application designed for ease of use and portability. All necessary tooling and dependencies are automatically installed when the project is loaded using either VSCode Devcontainers or GitHub Codespaces. This setup ensures a consistent development environment, making it easy for contributors to get started quickly.

## Getting Started

### Loading the Project

To begin working on the project, simply open it in VSCode or GitHub Codespaces. The development environment will be automatically set up inside a Devcontainer, with all required tools and dependencies installed.

### Running Unit Tests

To run the unit tests, use the following Maven command:

```bash
mvn test
```

### Running Integration Tests

Integration tests can be executed using the following Maven command:

```bash
mvn verify
```

### Building the Application

To build the application, run:

```bash
mvn package
```

### Running the Application

After building the application, you can run it via the command line. For example, to view the help options, use:

```bash
./target/davea-demo --help
```
