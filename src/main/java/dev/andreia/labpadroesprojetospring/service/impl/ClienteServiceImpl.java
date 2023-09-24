package dev.andreia.labpadroesprojetospring.service.impl;

import dev.andreia.labpadroesprojetospring.model.Cliente;
import dev.andreia.labpadroesprojetospring.model.Endereco;
import dev.andreia.labpadroesprojetospring.repository.ClienteRepository;
import dev.andreia.labpadroesprojetospring.repository.EnderecoRepository;
import dev.andreia.labpadroesprojetospring.service.ClienteService;
import dev.andreia.labpadroesprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        cliente.setEndereco(verificarCep(cliente.getEndereco().getCep()));
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            cliente.setEndereco(verificarCep(cliente.getEndereco().getCep()));
            clienteRepository.save(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private Endereco verificarCep(String cep){
        Optional<Endereco> enderecoBd = enderecoRepository.findById(cep);
        return enderecoBd.orElseGet(() -> {
            Endereco enderecoViaCep = viaCepService.consultarCep(cep);
            enderecoRepository.save(enderecoViaCep);
            return enderecoViaCep;
        });
    }
}
