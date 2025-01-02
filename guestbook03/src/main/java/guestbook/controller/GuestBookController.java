package guestbook.controller;

import java.util.Enumeration;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestBookRepository;
import guestbook.vo.GuestBookVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GuestBookController {
	private GuestBookRepository guestBookRepository;
	
	public GuestBookController(GuestBookRepository guestBookRepository) {
		this.guestBookRepository = guestBookRepository;
	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		ServletContext sc = request.getServletContext();
		Enumeration<String> e = sc.getAttributeNames();
		
		while (e.hasMoreElements()){
			String s = e.nextElement();
			System.out.println(s);
		}
		ApplicationContext ac1 = (ApplicationContext)sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		ApplicationContext ac2 = (ApplicationContext)sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");
		GuestBookRepository repository = ac1.getBean(GuestBookRepository.class);
		System.out.println(repository);
		
		model.addAttribute("list", guestBookRepository.findAll());
		return "index";
	}

	
	@RequestMapping("/add")
	public String add(GuestBookVo vo) {
		guestBookRepository.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable(value="no") Long id, Model model) {
		model.addAttribute("id", id);
		return "deleteform";
	}

	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String deleteform(@PathVariable(value="no") Long id, 
			@RequestParam(value="password", required=true, defaultValue="") String password, 
			Model model) 
	{
		guestBookRepository.deleteByIdandPassword(id, password);
		return "redirect:/";
	}
	
	
	
}
