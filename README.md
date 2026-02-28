# Elfar Website - UI Automation

Automated UI testing framework for the **Mahmoud Elfar Official Website** built with **Playwright** and **Java**.

## Tech Stack
Java 11, Playwright, TestNG, Maven, Allure Reports.

## How to Run (Execution)
Open your terminal and run the following command to execute the full regression suite and open the report automatically:

```bash
mvn clean test -Prun-regression
mvn allure:serve
