package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.entity.CitizenPlan;
import com.example.demo.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	
    List<String> getPlanNames();
    List<String> getPlanStatuses();
    List<CitizenPlan> search(SearchRequest request);

    void exportExcel(HttpServletResponse response) throws IOException;
    void exportPdf(HttpServletResponse response) throws IOException;
}
