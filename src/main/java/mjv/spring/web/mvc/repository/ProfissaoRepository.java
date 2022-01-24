package mjv.spring.web.mvc.repository;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mjv.spring.web.mvc.model.Cadastro;
import mjv.spring.web.mvc.model.Profissao;

@Repository
@Transactional
public interface ProfissaoRepository extends JpaRepository<Profissao, Integer> {

	Optional<Cadastro> findById(Profissao profissao);

}
