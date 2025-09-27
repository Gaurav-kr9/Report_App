 package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.CitizenPlan;
import com.example.demo.request.SearchRequest;
import com.example.demo.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
<<<<<<< HEAD
=======
	@GetMapping("/pdf")
	public void downloadpdf(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plan.pdf"); 
		service.exportPdf(response);
	}
>>>>>>> 9cb9afc (Final roject 0.1)
	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plan.xls"); 
		service.exportExcel(response);
	}

	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) {
		
		System.out.println(search);
		
		List<CitizenPlan>  plans = service.search(search);
		
		model.addAttribute("plans",plans);
		
		init(model);
		
		return "index";
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		 
		model.addAttribute("search" , new SearchRequest());
		init(model);
		
		return "index";
	}


	private void init(Model model) {
		model.addAttribute("names" , service.getPlanNames());
		model.addAttribute("status" , service.getPlanStatuses());
	}

}
