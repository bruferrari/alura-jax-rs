package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	
	private static HttpServer server;

	public static void main(String[] args) throws IOException {
		server = iniciaServidor();
		System.out.println("Servidor Rodando...");
		System.in.read();
		paraServidor();
	}

	public static void paraServidor() {
		server.stop();
	}

	public static HttpServer iniciaServidor() {
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = URI.create("http://localhost:8080/");
		server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}
	
	public boolean isIniciado() {
		if(server.isStarted())
			return true;
		else
			return false;
	}
}
