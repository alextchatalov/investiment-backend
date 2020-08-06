package com.monitor.rest;

import com.monitor.domain.Investiment;
import com.monitor.domain.enums.TypeInvestiment;
import com.monitor.dto.InvestimentDTO;
import com.monitor.service.InvestmentService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/investiment")
public class InvestmentRest {

    @Autowired
    private InvestmentService service;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<InvestimentDTO> getAll() throws IOException {
        List<Investiment> investiments = service.getAll();
        return castToDto(investiments);
    }

    @GetMapping("/totalApplied")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTotalApplied() {
        List<Investiment> investiments = service.getAll();
        BigDecimal total = investiments.stream()
                .map(Investiment::getAppliedAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }

    @PostMapping("/newInvestiment")
    @ResponseStatus(HttpStatus.CREATED)
    public void newInvestiment(@RequestBody InvestimentDTO investimentDTO) {
        try {
            service.save(castToEntity(investimentDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/updateInvestimet/{investimentCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvestimentDTO> updateInvestiment(@RequestBody InvestimentDTO investimentDTO,
                                  @PathVariable("investimentCode") String investimentCode) {
        try {
            service.update(investimentDTO, investimentCode);
            return getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{investimentCode}")
    public void deleteInvestiment(@PathVariable("investimentCode") String investimentCode) {
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
                    .amount(inv.getAmount())
                    .build();
            investimentDTOs.add(investmentDTO);
        });
        return investimentDTOs;
    }

    private Investiment castToEntity(InvestimentDTO investimentDTO) {
        Investiment investiment = new Investiment();
        investiment.setInvestimentCode(investimentDTO.getInvestimentCode());
        investiment.setType(TypeInvestiment.fromString(investimentDTO.getType()));
        investiment.setBroker(investimentDTO.getBroker());
        investiment.setFirstDateApplication(investimentDTO.getFirstDateApplication());
        investiment.setAppliedAmount(investimentDTO.getAppliedAmount());
        investiment.setBalance(investimentDTO.getBalance());
        investiment.setRentail(investimentDTO.getRentail());
        investiment.setPortfolioShare(investimentDTO.getPortfolioShare());
        investiment.setAmount(investimentDTO.getAmount());
        return investiment;
    }
}
