Publication
---

This tool consists of two main components: code transformation and tool execution with data analysis.You can download the tool from the following command:

```bash
git clone https://github.com/Static-Analysis-study/home.git
```
## Code Structure

The code structure of the tool is as follows:

```bash
home
    ├── Dataset
    ├── testCase
    ├── codeTransformation
        ├── TransformationRule.java
        ├── AddThisToFieldAccessExpr.java
        ├── AnonymousClassToLambda.java
        ├── MoveMethodToNestedClass.java
        ├─- ChangeOneVariableNameToMethodName.java
        ├── pom.xml
    ├── testAndAnalysis
        ├── analysisPmd.py
        ├── analysisSonarqube.py
        ├── analysisSpotbugs.py
        ├── getPMDResult.py
        ├── getSonarqubeResult.py
        ├── getSpotbugsResult.py
        ├── runPmd.py
        ├── runSonarqube.py
        ├── runSpotbugs.py
        ├── Sonarqubeapi.py
        ├── config.json
    ├── README.md

```


## 1. Code Transformation
---

This section provides instructions for setting up and running the code transformation process. 

### Prerequisites

Make sure you have the following software installed on your system:

- The latest or recent version of Maven for building the project.
- Java 11 or above to execute the project.

### Building the Project

To compile the project, open a terminal or command prompt, navigate to the project directory, and run the command:

```bash
mvn clean install
```

Maven will compile the project and generate the necessary artifacts.

### Running the Tool

After successfully building the project, you can run the generated JAR file. Follow these steps:

1. Navigate to the `target` directory in the project folder.
2. You will find a JAR file named `code_transformation-1.0-SNAPSHOT-shaded.jar`.
3. Run the JAR file using the following command, replacing `<param1>` and `<param2>` with appropriate values:

```bash
java -jar code_transformation-1.0-SNAPSHOT-shaded.jar <param1> <param2>
```

- `<param1>`: Specify the transformation rule you want to apply. You can refer to the section **Transformation Rule** in detail.
- `<param2>`: Specify the path to the directory containing your test cases. For the test cases, please see the details in the **Test Case Structure** section below. Also, you can refer to the example test cases located in the `testCases` directory.
  

After running the tool with the specified parameters, you will find the transformed test cases in the same directory of test cases.
  
### param1 : Transformation Rule

The following transformation rules are available:

- a. Wrapping a method with a nested class: `nestedclass`
- b. Converting an anonymous class to a lambda expression: `lambdaexpr`
- c. Renaming symbol names to create same identifiers: `renamesymbols`
- d. Adding "this." to field access: `addthis`

### param2 : Test Case Structure

Ensure that your test cases are organized as follows:

- Place all original cases in a directory named `originalCase`.
- Each individual test case should be located within a separate directory named with a numeric identifier (e.g., `1`, `2`, `3`, ...).


## 2. Tool Execution and Data Analysis

This section provides instructions for executing the tool on test cases and performing a comparative analysis of the obtained results. Prior to using the tool, ensure you have PMD, Spotbugs, and Sonarqube installed on your system and added to your system's environment variables. You can refer to the following resources for installation guidance:

- PMD: [PMD Installation Guide](https://docs.pmd-code.org/latest/pmd_userdocs_installation.html)
- Spotbugs: [Spotbugs Installation Guide](https://spotbugs.readthedocs.io/en/stable/installing.html)
- Sonarqube: [Sonarqube Setup and Installation Guide](https://docs.sonarsource.com/sonarqube/latest/setup-and-upgrade/overview/)

Additionally, for Sonarqube, you will need to configure the `sonarqubeLink` and `sonarqubeToken` in the `config.json` file. For Spotbugs, you should provide the installation path within the `config.json` file.

The following subsections will describe how to run and analyze each static code analyzer using scripts. The tool offers functionality to assess the code using PMD, Spotbugs, and Sonarqube, and to compare the analysis results. The primary command for executing these operations is:

```bash
python3 main.py -f <filename>
```

### (1) PMD

The PMD-related operations are facilitated through the following three files: `runPmd.py`, `getPMDResult.py`, and `analysisPmd.py`. These files respectively handle running PMD, transforming PMD output results into easily analyzable CSV format, and comparing different CSV files to extract differential results.

In the `testAndAnalysis` directory, you can run the following commands:

- Run PMD on test cases:
  ```bash
  python3 main.py -f runPmd
  ```

- Convert PMD output to CSV:
  ```bash
  python3 main.py -f getPMDResult
  ```

- Compare and analyze PMD results (provide CSV paths `csv1` and `csv2` in `config.json`):
  ```bash
  python3 main.py -f analysisPmd
  ```

### (2) Spotbugs

The Spotbugs-related operations consist of three steps:

- Run Spotbugs on test cases (including compilation):
  ```bash
  python3 main.py -f runSpotbugs
  ```

- Convert Spotbugs XML results to CSV:
  ```bash
  python3 main.py -f getSpotbugsResult
  ```

- Compare and analyze Spotbugs results (set CSV paths in `config.json`):
  ```bash
  python3 main.py -f analysisSpotbugs
  ```

Note: Ensure that the `config.json` file has correctly specified paths for the original and transformed code CSV results: `spotbugsSCV1` and `spotbugsSCV2`. If you are using custom test cases, provide the required environment in the `spotbugscaseClasspath` field.

### (3) Sonarqube

The Sonarqube-related operations involve:

- Run Sonarqube on test cases:
  ```bash
  python3 main.py -f runSonarqube
  ```

- Convert Sonarqube XML results to CSV:
  ```bash
  python3 main.py -f getSonarqubeResult
  ```

- Compare and analyze Sonarqube results (set CSV paths in `config.json`):
  ```bash
  python3 main.py -f analysisSonarqube
  ```

Ensure that the `config.json` file has correctly specified paths for the original and transformed code CSV results: `csv1` and `csv2`.

### Analysis and Comparison

After performing the above steps, you can analyze and compare the results generated by PMD, Spotbugs, and Sonarqube with markdown report. The comparison reports will highlight the differences in code issues between the original and transformed code. 


