package com.example.duanlon.controller;

import com.example.duanlon.config.ConfigApplication;
import com.example.duanlon.core.CaseStatus;
import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.model.Evidence;
import com.example.duanlon.service.ICriminalCaseService;
import com.example.duanlon.service.IEvidenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/criminalCase")
public class AuthController {
    private ModelMapper modelMapper = ConfigApplication.modelMapper();

    @Autowired
    private ICriminalCaseService criminalCaseService;

    @Autowired
    private IEvidenceService evidenceService;

    @GetMapping("/status/{caseStatus}")
    public List<CriminalCase> getCaseByStatus(@PathVariable String caseStatus) {
        return criminalCaseService.findAllByStatus(CaseStatus.valueOf(caseStatus))
                .stream().map(criminalCase -> modelMapper.map(criminalCase, CriminalCase.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/number/{caseNumber}")
    public List<Evidence> getEvidencesByCaseNumber(@PathVariable String caseNumber) {
        return evidenceService.findByCriminalCase_Number(caseNumber)
                .stream().map(evidence -> modelMapper.map(evidence, Evidence.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/username/{username}")
    public List<CriminalCase> getCaseByUsername(@PathVariable("username") String username) {
        return criminalCaseService.findByAssigned_Username(username)
                .stream().map(criminalCase -> modelMapper.map(criminalCase, CriminalCase.class))
                .collect(Collectors.toList());
    }
}
