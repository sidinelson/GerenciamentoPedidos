package br.com.order.repository;



import br.com.order.model.ProdutoExternoAModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProdutoExternoARepository extends JpaRepository<ProdutoExternoAModel, Long> {

}
