package com.eaglestalwart.MailTransfer.repository;

import com.eaglestalwart.MailTransfer.models.Driver;
import com.eaglestalwart.MailTransfer.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    public List<Report> findByReportType(String firstName);

}
