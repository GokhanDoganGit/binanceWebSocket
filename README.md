# Binance WebSocket Price Data Collector

## Overview
This project collects real-time cryptocurrency price data from Binance using their WebSocket API and exposes it via a REST API. The application efficiently handles multiple cryptocurrency pairs and provides price updates to clients on demand.

## Features
- Connects to Binance's WebSocket API to receive real-time price updates for popular cryptocurrency pairs.
- Exposes a RESTful API for clients to retrieve the latest price data for specific symbols.
- Supports high concurrency and performance to handle multiple requests simultaneously.
- Implements error handling for missing or invalid symbols.

## Technologies Used
- **Java 17**
- **Spring Boot**
- **WebSocket API**
- **RESTful API**
- **JUnit & Mockito** for testing
- **Maven** for dependency management

## Getting Started

### Prerequisites
- Java 17
- Maven

### Configuration
1. Clone the repository
2. Update application.yml file to track crypto you want to
3. Build the app with: mvn clean install
4. Run the application with: mvn spring-boot:run
5. Retrieve the price data for a specific cryptocurrency pair: GET /api/prices/{symbol} Example: localhost:8080/api/prices/BTCUSDT


Contributing
Contributions are welcome! Please create a pull request with your changes or open an issue for discussion.
