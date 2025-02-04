# Server-client-application

The aim of the project is to connect the client with the server using TCP flow.

## Getting Started

To start the project, follow these steps:

1. **Create Projects**: Create two separate projects for the two classes, one for the server and one for the client.
2. **Configure IP Address**: Use the `ipconfig` command to find the IP address of the server. Insert this IP address into the string section of the server class.
3. **Start the Server**: Run the server class first. The client needs the server to be running in order to connect.
4. **Start the Client**: After the server is running, run the client class. Type "CONNECT" to establish a connection with the server. Once connected, you can start chatting with the server, sending and receiving messages from both ends.
5. **Disconnect**: When the client wants to disconnect, type "QUIT" to exit the chat.

## Example Commands

- **CONNECT**: Initiate the connection with the server.
- **QUIT**: Disconnect the client from the server.

## Additional Information

This project is implemented in Java. Ensure that both the server and client are running on the same network for successful communication.

