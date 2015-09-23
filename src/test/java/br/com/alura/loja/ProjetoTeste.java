package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTeste {
	
	private Client client;
	private WebTarget target;

	@Before
	public void inicializaServidor() {
		Servidor.iniciaServidor();
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080");
	}
	
	@After
	public void mataServidor() {
		Servidor.paraServidor();
	}
	
	@Test
	public void TestaQueProjetoTrazOProjetoEsperado() {
		Projeto projeto = target.path("/projeto/1").request().get(Projeto.class);
		Assert.assertEquals("Minha Loja", projeto.getNome());
	}
}
