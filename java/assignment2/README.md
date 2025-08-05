# Java UDP Multi-Client Chat Application 💬

A simple, GUI-based multi-client chat application built in Java. It uses the UDP (User Datagram Protocol) for communication between a central server and multiple clients, featuring a clean user interface made with Java Swing.

## 📝 Description

This application provides a real-time chat environment where multiple users can connect to a central server and communicate with each other. The server is responsible for receiving messages from clients and broadcasting them to all other connected users. The client provides a user-friendly graphical interface for sending and viewing messages.

## ✨ Features

- **Multi-User Chat**: Supports multiple clients connecting and chatting simultaneously.
- **Graphical User Interface (GUI)**: A clean and intuitive chat window built with Java Swing.
- **Join & Quit Notifications**: The server announces when a user joins or leaves the chat.
- **Timestamped Messages**: Messages are timestamped on the server to maintain a clear chronological order.
- **Dynamic IP & Username**: The client application prompts the user for the server's IP address and a desired username upon startup.

## 🏛️ Project Structure

The project is divided into two main components, separating the client and server logic for clarity and maintainability.

- `UDPServer.java`: The **backend server** application. It runs in the console and performs the following tasks:
    - Listens for incoming client connections and messages on port `12345`.
    - Manages a list of connected clients and their unique usernames.
    - Handles special commands like `/join` and `/quit` to manage the user list.
    - Broadcasts received messages to all other clients in the chat room.

- `UDPClient.java`: The **frontend client** application. It provides the user-facing experience:
    - A GUI built with **Java Swing** for an interactive chat window.
    - Prompts for server IP and username to establish a connection.
    - Sends user messages to the server.
    - Listens for incoming broadcasted messages from the server and displays them in the chat area.

## 🚀 How to Compile and Run

To run this project, you will need to have the **Java Development Kit (JDK)** installed on your system.

### 1. Place Files

Ensure both `UDPServer.java` and `UDPClient.java` are in the same directory.

### 2. Compile

Open a terminal or command prompt, navigate to the directory containing the files, and run the following command to compile the Java source files:

```bash
javac UDPServer.java UDPClient.java