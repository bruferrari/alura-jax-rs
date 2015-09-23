package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoDAO {

	private static Map<Long, Projeto> banco = new HashMap<>();
	private static AtomicLong contador = new AtomicLong(1);
	
	
	static {
		banco.put(1L, new Projeto("Minha Loja", 1L, 2015));
		banco.put(2L, new Projeto("Outra Loja", 2L, 2015));
	}
	
	public void adiciona(Projeto projeto) {
		Long id = contador.incrementAndGet();
		projeto.setId(id);
		banco.put(id, projeto);
	}
	
	public Projeto busca(Long id) {
		return banco.get(id);
	}
	
	public Projeto remove(Long id) {
		return banco.remove(id);
	}
}
