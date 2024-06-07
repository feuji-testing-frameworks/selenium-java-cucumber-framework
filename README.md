# selenium-java-cucumber-framework

## Description

This project is designed for testing a UI using Selenium WebDriver with the TestNG and cucumber framework in Java. It allows you to automate UI testing.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java JDK 8 or higher
- Maven
- An IDE (Eclipse, IntelliJ IDEA, etc.)

## Project Structure

```plaintext
selenium-java-cucumber-framework
├── src
│   ├── test
│   │   ├── java
│   │   │   └── com
│   │   │       └── cucumber
│   │   │           ├── pages
│   │   │           │   └── ui
│   │   │           │       ├── AddToCartPage.java
│   │   │           │       ├── HomePage.java
│   │   │           │       ├── LoginPage.java
│   │   │           │       └── SideBarValidation.java
│   │   │           ├── stepdefinitions
│   │   │           │   └── ui
│   │   │           │       ├── SauceDemoLoginTest.java
│   │   │           │       ├── SauceDemoCheckoutTest.java
│   │   │           │       ├── SauceDemoSideBarTest.java
│   │   │           │       ├── SauceDemoSortingTest.java
│   │   │           └── testrunner
│   │   │               └── TestRunner.java
│   │   └── resources
│   │       └── features
│   │           ├── login.feature
│   │           └── add_to_cart.feature
├── pom.xml
└── testng.xml

## Setup Instruction

1. **Clone the Project** : Clone the project repository from GitHub using the following command:

    `https://github.com/feuji-testing-frameworks/selenium-java-cucumber-framework.git`

2. **Install dependencies** : If you are using IDE it will automatically install dependecies present in pom.xml or if we use command prompt for download the dependencies using the following command

    `mvn clean install`

3. **Running Tests** : For running the tescases using the following command in command prompt or if we use IDE right click on testng.xml file and select Run As then click on TestNG suite

    `mvn test`
