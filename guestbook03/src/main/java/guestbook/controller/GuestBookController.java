package guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import guestbook.repository.GuestBookRepository;
import guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	private GuestBookRepository guestBookRepository;
	
	public GuestBookController(GuestBookRepository guestBookRepository) {
		this.guestBookRepository = guestBookRepository;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		List<GuestBookVo> list = guestBookRepository.findAll();
		model.addAttribute("list", list);
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
