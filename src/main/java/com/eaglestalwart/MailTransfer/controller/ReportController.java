package com.eaglestalwart.MailTransfer.controller;

import com.eaglestalwart.MailTransfer.models.Report;
import com.eaglestalwart.MailTransfer.models.Report;
import com.eaglestalwart.MailTransfer.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class ReportController {
    
    @Autowired
    private ReportRepo reportRepo;
    
    @PostMapping("/report")
    Report newReport(@RequestBody Report newReport){
        return reportRepo.save(newReport);
    }
    
    @GetMapping("/report")
    List<Report> getReports(){
        return reportRepo.findAll();
    }

    @GetMapping("/report/{id}")
    Report getReportById(@PathVariable("id") Long id){
        return reportRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Report with id "
                                + id +
                                " does not exist"
                ));
    }

    @GetMapping("/reportType/{reportType}")
    List<Report> getReportByReportType(@PathVariable("reportType") String name){

        return reportRepo.findByReportType(name);
    }


    @PutMapping("/report/{id}")
    Report updateReport(@RequestBody Report newReport, @PathVariable("id") Long id){
        Report report = reportRepo.findById(id)
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Report with id "
                                + id +
                                " does not exist"
                ));

        if (newReport.getReportType() != null &&
                newReport.getReportType().length() > 0 &&
                !Objects.equals(report.getReportType(), newReport.getReportType())){
            report.setReportType(newReport.getReportType());
        }


        if (newReport.getContent() != null &&
                newReport.getContent().toString().length() > 0 &&
                !Objects.equals(report.getContent(), newReport.getContent())){
            report.setContent(newReport.getContent());
        }


        return reportRepo.save(report);
    }


    @DeleteMapping("/report/{id}")
    String deleteReport(@PathVariable("id") Long id){
        if(!reportRepo.existsById(id)){
            throw new IllegalStateException(
                    "Report with id "
                            + id +
                            " does not exist"
            );
        }
        reportRepo.deleteById(id);

        return "Report has been deleted successfully";
    }
}
