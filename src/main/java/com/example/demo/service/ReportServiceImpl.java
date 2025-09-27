package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CitizenPlan;
import com.example.demo.repo.CitizenPlanRepository;
import com.example.demo.request.SearchRequest;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		
		if (request.getPlanName() != null && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (request.getGender() != null && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("plans-data");
		HSSFRow headerRow = sheet.createRow(0);

		// Header
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");

		List<CitizenPlan> records = planRepo.findAll();
		int dataRowIndex = 1;

		for (CitizenPlan plan : records) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(
				plan.getPlanStartDate() != null ? plan.getPlanStartDate().toString() : ""
			);
			dataRow.createCell(5).setCellValue(
				plan.getPlanEndDate() != null ? plan.getPlanEndDate().toString() : ""
			);
			dataRow.createCell(6).setCellValue(
				plan.getBenefitAmt() != null ? plan.getBenefitAmt() : 0.0
			);

			dataRowIndex++;
		}

		// Set response headers
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=plans.xls");

		// Write workbook to response output stream
//		workbook.write(response.getOutputStream());
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
//		return false;
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws IOException {
		// Later we’ll add PDF export logic
	}
}
