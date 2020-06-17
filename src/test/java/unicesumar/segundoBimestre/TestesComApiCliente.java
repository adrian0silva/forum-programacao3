package unicesumar.segundoBimestre;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import unicesumar.segundoBimestre.cliente.Cliente;
import unicesumar.segundoBimestre.cliente.ClienteController;
import unicesumar.segundoBimestre.cliente.ClienteService;

@WebMvcTest
@AutoConfigureMockMvc
class TestesComApiCliente {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ClienteController controller;

	@MockBean
	private ClienteService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testandoPost() throws Exception {
		when(service.save(ArgumentMatchers.any(Cliente.class))).thenReturn("10");

		Map<String, String> cliente = new HashMap<String, String>() {
			{
				put("id", "3");
				put("nomeCompleto", "adriano");
				put("senha", "123");
			}
		};

		String clienteComoJson = objectMapper.writeValueAsString(cliente);

		mockMvc.perform(post("/api/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteComoJson))
				.andExpect(status().isCreated()).andExpect(content().string("10"));

	}

	@Test
	public void testandoGetAll() throws Exception {
		Cliente clienteA = new Cliente("1", "adriano", "123");
		Cliente clienteB = new Cliente("2", "silva", "admin");
		when(service.getAll()).thenReturn(Arrays.asList(clienteA, clienteB));

		mockMvc.perform(get("/api/clientes")).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$.[0].id").value("1")).andExpect(jsonPath("$.[0].nomeCompleto").value("adriano"))
				.andExpect(jsonPath("$.[0].senha").value("123")).andExpect(jsonPath("$.[1].id").value("2"))
				.andExpect(jsonPath("$.[1].nomeCompleto").value("silva"))
				.andExpect(jsonPath("$.[1].senha").value("admin")).andExpect(status().isOk());
	}

	@Test
	public void testandoGetByIdComDadoExistente() throws Exception {
		Cliente cliente = new Cliente("1", "adriano", "123");
		when(service.getById("1")).thenReturn(cliente);

		mockMvc.perform(get("/api/clientes/1")).andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.nomeCompleto").value("adriano")).andExpect(jsonPath("$.senha").value("123"))
				.andExpect(status().isOk());

	}

	@Test
	public void testandoDeleteById() throws Exception {
		Cliente cliente = new Cliente("5", "jose", "java");

		service.save(cliente);

		mockMvc.perform(delete("/api/clientes/5")).andExpect(status().isAccepted());
	}

	@Test
	public void testandoPut() throws Exception {
		Cliente cliente = new Cliente("5", "jose", "java");
		service.save(cliente);

		Map<String, String> clienteAtualizado = new HashMap<String, String>() {
			{
				put("id", "5");
				put("nomeCompleto", "adriano");
				put("senha", "123");
			}
		};

		String writeValueAsString = objectMapper.writeValueAsString(clienteAtualizado);

		mockMvc.perform(put("/api/clientes/5").contentType(MediaType.APPLICATION_JSON).content(writeValueAsString))
				.andExpect(status().isOk());

	}

	@Test
	void testandoGetByIdComDadoInexistente() throws Exception {
		when(service.getById("2")).thenThrow(unicesumar.segundoBimestre.cliente.NotFoundException.class);

		mockMvc.perform(get("/api/clientes/2")).andExpect(status().isNotFound());
	}

}
