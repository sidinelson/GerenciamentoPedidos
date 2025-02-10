package br.com.order.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoExternoARepository extends JpaRepository<ProdutoExternoAModel, Long> {

}
