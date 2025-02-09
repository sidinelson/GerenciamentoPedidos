package br.com.order.service;


import br.com.order.model.ProdutoExternoBModel;
import br.com.order.repository.ProdutoExternoBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoExternoBService {

    @Autowired
    private ProdutoExternoBRepository produtoRepository;

    public ProdutoExternoBModel cadastrarProduto(ProdutoExternoBModel produto) {
        return produtoRepository.save(produto);
    }

    public List<ProdutoExternoBModel> listarProdutos() {
        return produtoRepository.findAll();
    }

}
