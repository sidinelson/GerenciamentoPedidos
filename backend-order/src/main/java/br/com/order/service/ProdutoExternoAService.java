package br.com.order.service;


import br.com.order.model.ProdutoExternoAModel;
import br.com.order.repository.ProdutoExternoARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProdutoExternoAService {

    @Autowired
    private ProdutoExternoARepository produtoRepository;

    public ProdutoExternoAModel cadastrarProduto(ProdutoExternoAModel produto) {

        return produtoRepository.save(produto);
    }

    public List<ProdutoExternoAModel> listarProdutos() {
        return produtoRepository.findAll();
    }

}
