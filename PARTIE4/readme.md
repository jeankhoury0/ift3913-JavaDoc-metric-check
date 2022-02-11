Jean KHORUY
Tian PENG

[**Link to github repo**](https://github.com/jeankhoury0/ift3913-JavaDoc-metric-check)

# JavaDoc metric check
## About
Project to mesure the documentation embeded in java code. It aims to flag complicated code with an inapropriate level of comments. 

This project was made for the class IFT3913 - software quality and metrics in winter 2022. [Link to the assignement](https://github.com/jeankhoury0/ift3913-JavaDoc-metric-check/blob/main/TP1-Question.pdf) (in French)

## Contributor
- @jeankhoury0
- @tianpeng97
# What we mesure:
## Class related metrics
- **classe_LOC**: The number of lines of code in a class
- **classe_CLOC**: The number of lines of comments in a class
- **classe_DC**: The densité of comments in a class (classe_CLOC/classe_LOC)
- **WMC**: Weighted Methods per Class - mesure of cyclomatic complexity 
- **classe_BC**: How well is the code commented (classe_DC/WMC)

## Package related metrics
- **paquet_LOC**: The number of lines of code in a package
- **paquet_CLOC**: The number of lines of comments in a package
- **paquet_DC**: The densité of comments in a package (paquet_CLOC/paquet_LOC)
- **WMC**: Weighted Methods per package - mesure of cyclomatic complexity 
- **paquet_BC**: How well is the package commented (paquet_DC/WMC)

# How to use
Run in the terminal

If specifing the path in arguments
``` console
$ java -jar codecheck.jar [path_to_repository]
```
if specifing the path in the config.properties
```console 
$ java -jar codecheck.jar
```

## Hypothesis
- The java repository used is correctly formated and has passed through a first linting. 
- package-info.java and module-info.java are not considered as valid file
- Enums are not considered in the as less well for  the less commented classes.

