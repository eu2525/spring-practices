package guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestBookRepository;
import guestbook.service.GuestBookService;
import guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	private GuestBookService guestBookService;
	
	public GuestBookController(GuestBookService guestBookService) {
		this.guestBookService = guestBookService;
	}
	
	@RequestMapping("/")
	public String index(/*HttpServletRequest request,*/ Model model) {
		/*
		ServletContext sc = request.getServletContext();
		Enumeration<String> e = sc.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name);
		}
		ApplicationContext ac1 = (ApplicationContext)sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		ApplicationContext ac2 = (ApplicationContext)sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");
		GuestBookRepository repository = ac1.getBean(GuestBookRepository.class);
		System.out.println(repository);
		GuestBookController controller = ac2.getBean(GuestBookController.class);
		System.out.println(controller);
		System.out.println(ac1 == ac2);
		*/
		
		model.addAttribute("list", guestBookService.getContentsList());
		return "index";
	}
	
	@RequestMapping("/add")
	public String add(GuestBookVo vo) {
		guestBookService.addContents(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		return "delete";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String delete(
		@PathVariable("id") Long id,
		@RequestParam(value="password", required=true, defaultValue="") String password) {
		guestBookService.deleteContents(id, password);
		return "redirect:/";
	}
}