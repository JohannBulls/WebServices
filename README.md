# Java Communication Project

This project demonstrates the implementation of various communication mechanisms in Java, including TCP and UDP sockets, as well as RMI (Remote Method Invocation). Different components have been developed to illustrate chat systems and echo services.

## Project Components

### 1. Chat

- **`ChatClient`**: RMI interface for connecting to the chat server and managing messages.
- **`ChatClientImpl`**: Implementation of the chat client. Allows sending and receiving messages from the server.
- **`ChatServer`**: RMI interface for managing clients and distributing messages.
- **`ChatServerImpl`**: Implementation of the chat server. Handles client connections and communication between them.

### 2. Echo

- **`EchoClient`**: TCP client that connects to the echo server and displays the responses received.
- **`EchoServer`**: TCP server that listens on port 35000, receives messages, and responds with a modified string.
- **`EchoClient_2`**: RMI client for the echo service that connects and receives responses from the server.
- **`EchoServerImpl_2`**: RMI server for the echo service. Responds to echo requests from the client.

### 3. Datagram

- **`DatagramTimeClient`**: UDP client that requests the current time from the server and displays the response.
- **`DatagramTimeServer`**: UDP server that responds with the current time to client requests.

### 4. Additional Services

- **`HttpServer`**: Simple HTTP server that listens on port 35000 and responds with a static HTML page.
- **`MathClient`**: Client that connects to a server on port 35000 and sends numbers to calculate their squares. Displays the server's response on the console.
- **`MathServer`**: Mathematical server that listens on port 35000 and performs mathematical operations (`sin`, `cos`, `tan`) based on the client's input. It can also change the current operation with a specific command.
- **`NumClient`**: Client that connects to a server on port 35000, sends a number, and displays the server's response, which is the square of the number.
- **`NumServer`**: Simple server that listens on port 35000, calculates the square of numbers sent by the client, and responds with the result.
- **`SimpleWebServer`**: Web server that handles HTTP GET and POST requests on port 35000. Allows retrieval and upload of files from and to the server.
- **`URLInfoPrinter`**: Program that reads a URL provided by the user and prints its components using methods from the `URL` class.
- **`URLReader`**: Application that reads data from a URL provided by the user and saves the content to a file named `result.html` in the `src/main/java/resources/` directory.

## Project Structure

- `edu.escuelaing.arsw.ASE.app`
  - **`ChatClient.java`**: RMI interface for the chat client.
  - **`ChatClientImpl.java`**: Implementation of the chat client.
  - **`ChatServer.java`**: RMI interface for the chat server.
  - **`ChatServerImpl.java`**: Implementation of the chat server.
  - **`DatagramTimeClient.java`**: UDP client for obtaining the time.
  - **`DatagramTimeServer.java`**: UDP server that provides the time.
  - **`EchoClient.java`**: TCP client for the echo service.
  - **`EchoServer.java`**: TCP server for the echo service.
  - **`EchoClient_2.java`**: RMI client for the echo service.
  - **`EchoServerImpl_2.java`**: RMI server for the echo service.
  - **`HttpServer.java`**: Simple HTTP server.
  - **`MathClient.java`**: Client for mathematical calculations.
  - **`MathServer.java`**: Mathematical server.
  - **`NumClient.java`**: Client for calculating squares.
  - **`NumServer.java`**: Server for calculating squares.
  - **`SimpleWebServer.java`**: Web server for handling HTTP requests.
  - **`URLInfoPrinter.java`**: Program for printing URL components.
  - **`URLReader.java`**: Application for reading data from a URL and saving it to a file.

## Usage

### Run the Chat Server and Client

1. **Start the Chat Server**  
   Run `ChatServerImpl`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.ChatServerImpl
   ```

2. **Start the Chat Client**  
   Run `ChatClientImpl` providing the server's IP address, RMI port, and username:
   ```bash
   java edu.escuelaing.arsw.ASE.app.ChatClientImpl
   ```

### Run the Echo Service

1. **Start the Echo RMI Server**  
   Run `EchoServerImpl_2`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.EchoServerImpl_2
   ```

2. **Start the Echo RMI Client**  
   Run `EchoClient_2`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.EchoClient_2
   ```

3. **Start the Echo TCP Server**  
   Run `EchoServer`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.EchoServer
   ```

4. **Start the Echo TCP Client**  
   Run `EchoClient`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.EchoClient
   ```

### Run the Datagram Server and Client

1. **Start the UDP Time Server**  
   Run `DatagramTimeServer`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.DatagramTimeServer
   ```

2. **Start the UDP Time Client**  
   Run `DatagramTimeClient`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.DatagramTimeClient
   ```

### Run Additional Services

1. **Start the HTTP Server**  
   Run `HttpServer`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.HttpServer
   ```

2. **Start the Math Client**  
   Run `MathClient`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.MathClient
   ```

3. **Start the Math Server**  
   Run `MathServer`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.MathServer
   ```

4. **Start the Number Client**  
   Run `NumClient`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.NumClient
   ```

5. **Start the Number Server**  
   Run `NumServer`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.NumServer
   ```

6. **Start the Simple Web Server**  
   Run `SimpleWebServer`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.SimpleWebServer
   ```

7. **Run the URL Info Printer Program**  
   Run `URLInfoPrinter`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.URLInfoPrinter
   ```

8. **Run the URL Reader**  
   Run `URLReader`:
   ```bash
   java edu.escuelaing.arsw.ASE.app.URLReader
   ```

## Contributing

Contributions are welcome. Please fork the repository and submit pull requests with improvements or new features.

## Author

Johann Amaya Lopez - [GitHub](https://github.com/johannamaya)

## License

This project is licensed under the MIT License. See the LICENSE file for details.
