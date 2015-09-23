package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class CarrinhoTeste {
	
	private Client client;
	private WebTarget target;

	@Before
	public void startaServidor() {
		Servidor.iniciaServidor();
		
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrasOCarrinhoEsperado() {
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
	
	@Test
	public void testaInserirUmNovoCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(123, "Iphone 6", 2800, 2));
		carrinho.adiciona(new Produto(345, "Teste", 200, 1));
		carrinho.setCidade("Ourinhos");
		carrinho.setRua("José Geraldo Beltrani, 52, Jarim Estoril");
		
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);
		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		Carrinho carrinhoCarregado = client.target(location).request().get(Carrinho.class);
		Assert.assertEquals("Iphone 6", carrinho.getProdutos().get(0).getNome());
	}
	
	@After
	public void mataServidor() {
		Servidor.paraServidor();
	}

}
