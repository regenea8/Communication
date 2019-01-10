package com.regenea8.communication.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient {

	public static void main(String[] args) {

		try {
			String serverIp = "127.0.0.1";

			System.out.println("서버에 연결중입니다. 서버 IP: " + serverIp);
			// 소켓을 생성하여 연결을 요청한다.
			Socket socket = new Socket(serverIp, 7777);

			// 소켓의 출력력스트림을 얻는다.
			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

			// 소켓으로 부터 데이터를 전송한다.
			String clientMsg = "[Notice] Test Message from Client.";
			dataOutputStream.writeUTF(clientMsg);

			// 소켓의 입력스트림을 얻는다.
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);

			String msg = dataInputStream.readUTF();
			System.out.println("Server Msg : " + msg);
			System.out.println("연결을 종료합니다.");

			// 스트림과 소켓을 닫는다.
			dataOutputStream.close();
			socket.close();
			System.out.println("연결이 종료되었습니다.");
		} catch(ConnectException connectException) {
			connectException.printStackTrace();
		} catch(IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
