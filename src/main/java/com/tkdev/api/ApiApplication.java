package com.tkdev.api;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tkdev.api.domain.Categoria;
import com.tkdev.api.domain.Cidade;
import com.tkdev.api.domain.Cliente;
import com.tkdev.api.domain.Endereco;
import com.tkdev.api.domain.Estado;
import com.tkdev.api.domain.ItemPedido;
import com.tkdev.api.domain.Pagamento;
import com.tkdev.api.domain.PagamentoComBoleto;
import com.tkdev.api.domain.PagamentoComCartao;
import com.tkdev.api.domain.Pedido;
import com.tkdev.api.domain.Produto;
import com.tkdev.api.domain.enums.EstadoPagamento;
import com.tkdev.api.domain.enums.TipoCliente;
import com.tkdev.api.repositories.CategoriaRepository;
import com.tkdev.api.repositories.CidadeRepository;
import com.tkdev.api.repositories.ClienteRepository;
import com.tkdev.api.repositories.EnderecoRepository;
import com.tkdev.api.repositories.EstadoRepository;
import com.tkdev.api.repositories.ItemPedidoRepository;
import com.tkdev.api.repositories.PagamentoRepository;
import com.tkdev.api.repositories.PedidoRepository;
import com.tkdev.api.repositories.ProdutoRepository;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 4000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 60.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Paraná");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Conselheiro Mairinck", est1);
		Cidade c2 = new Cidade(null, "Curitiba", est1);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		Cidade c4 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3, c4));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

		Cliente cli1 = new Cliente(null, "Thiago Kichiro", "kichiro.88@gmail.com", "06071886937",
				TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("4335611308", "11966023857"));

		Endereco e1 = new Endereco(null, "Rua Brasilia", "102", "Casa", "Centro", "86480000", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Celso Garcia", "4430", "Apto", "Tatuapé", "03064000", cli1, c3);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2021 10:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2021 19:35"), cli1, e2);

		Pagamento pgt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgt1);

		Pagamento pgt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"),
				null);
		ped2.setPagamento(pgt2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pgt1, pgt2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped1.getItens().addAll(Arrays.asList(ip1, ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
