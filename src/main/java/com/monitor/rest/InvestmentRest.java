package com.monitor.rest;

import com.monitor.domain.Investiment;
import com.monitor.domain.enums.TypeInvestiment;
import com.monitor.dto.InvestimentDTO;
import com.monitor.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/investiment")
public class InvestmentRest {

    @Autowired
    private InvestmentService service;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<InvestimentDTO> getAll() {
        List<Investiment> investiments = service.getAll();

        return castToDto(investiments);
    }

    @PostMapping("/newInvestiment")
    @ResponseStatus(HttpStatus.CREATED)
    public void newInvestiment(@RequestBody Investiment investimentRest) {
        try {
            service.save(investimentRest);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{investimentCode}")
    public void deleteInvestiment(@PathVariable String investimentCode) {
        try {
            service.delete(investimentCode);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/upload")
    public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        System.out.println("UPLOAD");
        service.uploadFile(files);
    }

    private List<InvestimentDTO> castToDto(List<Investiment> investiments) {

        List<InvestimentDTO> investimentDTOs = new ArrayList<>();
        investiments.forEach(inv -> {
            InvestimentDTO investmentDTO = InvestimentDTO.builder()
                    .investimentCode(inv.getInvestimentCode())
                    .type(inv.getType().getDescription())
                    .broker(inv.getBroker())
                    .firstDateApplication(inv.getFirstDateApplication())
                    .appliedAmount(inv.getAppliedAmount())
                    .balance(inv.getBalance())
                    .rentail(inv.getRentail())
                    .portfolioShare(inv.getPortfolioShare())
                    .build();
            investimentDTOs.add(investmentDTO);
        });
        return investimentDTOs;
    }

}
