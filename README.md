# A Chat Spring Boot project using Spring AI framework

### **Chat**
A simple Spring Boot project that uses Spring AI framework for building a chat application with AI capabilities.

## **How to run the projects**
The applications can be run using IDE (IntelliJ IDEA) or by using the command line with Maven. 
Make sure to have the required dependencies and configurations set up before running the applications.
The application requires:
* OpenAI API key to be set as an environment variable OPENAI_API_KEY
* API Ninjas API key to be set as an environment variable API_NINJAS_API_KEY
* Redis server running on localhost:6379 (default port) for caching purposes in order to limit usage of completion tokens.

There is a Docker `compose.yml` file provided for running the applications in Docker containers.
There are also several Kubernetes deployment files provided for deploying the applications in a Kubernetes cluster.

## **Supported REST Endpoints**

### Summarization Endpoints

#### 1. POST `/api/v1/summarization`
**Description:** Summarizes the provided text message and returns a summary response. Additionally, it saves the summary to a database using Spring Data JPA and returns a URI to the created summary resource.

**Request:**
- **Content-Type:** text/plain
- **Body:** Plain text message to be summarized

**Response:**
- **Status:** 201 Created
- **Body:** Summarized text
- **Location Header:** URI to the created summary resource

**Example:**
```bash
curl -X POST http://localhost:8080/api/v1/summarization \
  -H "Content-Type: text/plain" \
  -d "Your long text here..."
```

#### 2. POST `/api/v1/summarization/stream`
**Description:** Streams the summarization result in real-time.

**Request:**
- **Content-Type:** text/plain
- **Body:** Plain text message to be summarized

**Response:**
- **Status:** 200 OK
- **Content-Type:** text/event-stream
- **Body:** Stream of summary chunks

**Example:**
```bash
curl -X POST http://localhost:8080/api/v1/summarization/stream \
  -H "Content-Type: text/plain" \
  -d "Your long text here..."
```

### Weather Suggestion Endpoints

#### 3. GET `/api/v1/weatherSuggestion`
**Description:** Provides clothing suggestions based on current weather conditions at the specified geographic location. The endpoint accepts latitude and longitude as query parameters, fetches the current weather information from external REST service (OpenMeteo), fetches the information about city and country from external service (API Ninjas) and returns a formatted weather and location information with clothing recommendations. 


**Query Parameters:**
- `latitude` (required, double): Latitude coordinate of the location
- `longitude` (required, double): Longitude coordinate of the location

**Response:**
- **Status:** 200 OK
- **Content-Type:** application/json
- **Body:** Formatted weather suggestion with clothing recommendations

**Example:**
```bash
curl "http://localhost:8080/api/v1/weatherSuggestion?latitude=40.7128&longitude=-74.0060"
```
