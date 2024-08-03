package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long reportId;
    private String reportType;
    private LocalDateTime dateCreated;
    private String content;

    @PrePersist
    public void generateDateCreated(){
        if (dateCreated == null){

        this.dateCreated = LocalDateTime.now();
        }
    }

    public Report(String content, LocalDateTime dateCreated, String reportType) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.reportType = reportType;
    }

    public Report() {
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportType='" + reportType + '\'' +
                ", dateCreated=" + dateCreated +
                ", content='" + content + '\'' +
                '}';
    }
}
