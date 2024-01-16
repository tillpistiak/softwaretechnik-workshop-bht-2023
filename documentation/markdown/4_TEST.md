# TEST

## Inhaltsverzeichnis
- [TEST](#test)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [jUnit: RestAssured !](#junit-restassured-)
  - [Integration into build pipeline !](#integration-into-build-pipeline-)
  - [Coverage: JaCoCo !](#coverage-jacoco-)

## jUnit: RestAssured !
JUnit and RestAssured are two distinct, yet complementary libraries used in Java for testing purposes. JUnit is a renowned testing framework for Java applications. It is primarily used for unit testing, which is the process of testing the smallest testable parts of your application, such as methods and classes. JUnit provides a set of annotations to identify test methods and contains assertions to test expected results.
On the other hand, RestAssured is a Java library specifically designed for testing RESTful APIs. It provides a domain-specific language (DSL) that allows you to write powerful, maintainable tests for your APIs. RestAssured supports a given/when/then test style and can be used in conjunction with JUnit or any other testing framework.
When you see "JUnit: RestAssured", it typically implies that JUnit is being used as the testing framework, while RestAssured is being used for testing RESTful APIs. In this context, you would write your tests using JUnit, and then use RestAssured to make HTTP requests to your API and validate the responses. This combination allows for robust testing of both the functionality of your methods and classes, as well as the behavior of your RESTful APIs. (Github Copilot) 

## Integration into build pipeline !
A build pipeline is a sequential set of processes guiding a code change from commit to deployment. Orchestrated on platforms like GitHub, it encompasses tasks such as code compilation, test execution, and application packaging for deployment. Leveraging GitHub as a central medium, the pipeline automates these steps to ensure a smooth and controlled development lifecycle. Developers commit their code, triggering automated processes that compile, test, and package the application. This seamless integration fosters a Continuous Integration (CI) environment, promoting efficient and reliable software development practices.

## Coverage: JaCoCo !
JaCoCo, short for Java Code Coverage, is an open-source toolkit used for measuring test coverage in Java applications. It allows developers to analyze the extent to which their tests cover the source code by tracking which parts of the code are executed during test runs. JaCoCo generates reports providing detailed information on covered and uncovered code lines, branches, methods, and classes. Test coverage serves as a metric to assess how well the source code is tested, aiding developers in identifying areas that lack coverage and improving the overall software quality. It is commonly employed in Continuous Integration and Continuous Delivery pipelines to ensure that code changes do not adversely impact test coverage.