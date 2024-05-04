# Weather Application

## Overview

This Weather Application is a Spring Boot-based service designed to retrieve, store, and update weather data from the OpenWeather API. It allows users to search for weather data by city and automatically updates stored data at scheduled intervals using Spring Scheduler.

## Prerequisites

Before running this application, ensure you have the following installed:
- Java JDK 17 or later
- Maven 3.9 or later
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse)
- An OpenWeather API key (You can obtain one by registering at [OpenWeatherMap](https://openweathermap.org/appid))

## Installation

### Clone the Repository

Start by cloning the repository to your local machine:

```shell
git clone https://github.com/yourusername/weather-app.git
cd weather-app
```

### Configure API Key

To ensure the application can retrieve weather data, 
add your OpenWeather API key to the 
`application.yml` file located in `src/main/resources`:

```yaml
weather:
  api:
    url: "YOUR_API_KEY_HERE"
```

### Build the Application

Compile and build the application using Maven:

```bash
mvn clean install
```

This command will build the application and run all 
the tests, ensuring everything is set up correctly.

## Running the Application

Launch the application with Maven by executing:

```bash
mvn spring-boot:run
```

This will start the application on your local machine, making it accessible via `http://localhost:8080`.

### Available Endpoints

The application supports the following endpoints:

- `GET /api/v1/weather/{city}`: Retrieves current weather data for a specified city.
- `GET /api/v1/weather/all`: Retrieves weather data for all cities stored in the database.

## Testing

To run the unit tests independently:

```bash
mvn test
```

This command will execute all configured tests, 
ensuring that the application functions as expected.


## Experience while Developing/Learning with ChatGPT:
- It was fairly straightforward to complete this task 
with AI, as it had all the specific answers that 
I was searching for. I could provide snippets, 
and the AI would deliver tailored solutions for my project.
- This task took me around 3 hours to finish 
because it wasn't very complex; it was a simple application.
- Code was not always ready to run because there were 
instances when GPT provided DTOs that didn't exist in 
the project or were not relevant to my specific use cases. 
However, overall, about 90%-95% of the code was ready 
to be used.
- The most challenging part in this project was figuring
out how to use third party API in my own application
to send requests and receive information, and then
how to parse JSON and retrieve information that I needed.
- One type of prompt that I used the most `Help me do specific task
  within this method """Code snippet"""` As well as
```How can I implement X in my project```