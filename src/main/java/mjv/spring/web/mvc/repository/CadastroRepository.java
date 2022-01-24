package mjv.spring.web.mvc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mjv.spring.web.mvc.model.Cadastro;
import mjv.spring.web.mvc.model.Profissao;

@Repository
@Transactional
public interface CadastroRepository extends JpaRepository<Cadastro, Integer> {
	public List<Cadastro> findByProfissao(Integer idProfissao);

	public List<Cadastro> getByProfissao(Profissao prof);
}