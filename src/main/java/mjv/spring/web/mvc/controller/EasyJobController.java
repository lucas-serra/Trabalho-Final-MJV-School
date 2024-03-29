package mjv.spring.web.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mjv.spring.web.mvc.model.Cadastro;
import mjv.spring.web.mvc.model.Profissao;
import mjv.spring.web.mvc.model.Sexo;
import mjv.spring.web.mvc.repository.CadastroRepository;
import mjv.spring.web.mvc.repository.ProfissaoRepository;
import mjv.spring.web.mvc.service.CadastroService;

@Controller
public class EasyJobController {

	@Autowired
	private CadastroRepository repository;

	@Autowired
	private ProfissaoRepository profRepository;

	@Autowired
	private CadastroService service;

	@GetMapping("/")
	public ModelAndView index() {
		List<Profissao> profissoes = profRepository.findAll();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("profissoes", profissoes);
		List<Cadastro> cadastros = repository.findAll();
		mv.addObject("cadastros", cadastros);
		mv.addObject("cadastro", new Cadastro());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/grava")
	public ModelAndView save(@Validated Cadastro cadastro) {
		service.salvarCadastro(cadastro);
		return index();
	}

	@GetMapping("/consultar")
	public String consulta() {
		return "consulta";
	}

	@GetMapping("/busca/{id}")
	public ModelAndView busca(@PathVariable("id") Integer id, Model model) {
		ModelAndView mv = new ModelAndView("consulta");
		mv.addObject("cadastros", service.listarPorProfissao(profRepository.getById(id)));
		return mv;
	}

	@RequestMapping("busca/")
	public ModelAndView retornar() {
		ModelAndView modv = new ModelAndView("index");

		return modv;
	}

	@ModelAttribute(name = "sexos")
	public List<Sexo> sexos() {
		return Arrays.asList(Sexo.values());
	}
}
