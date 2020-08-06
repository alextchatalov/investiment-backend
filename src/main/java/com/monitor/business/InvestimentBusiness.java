package com.monitor.business;

import com.monitor.domain.Investiment;
import com.monitor.domain.User;
import com.monitor.domain.WalletRebalance;
import com.monitor.domain.enums.TypeInvestiment;
import com.monitor.dto.InvestimentDTO;
import com.monitor.repository.InvestimentRepository;
import com.monitor.repository.UserRepository;
import com.monitor.repository.WalletRebalanceRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class InvestimentBusiness {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private InvestimentRepository repository;

    @Autowired
    private WalletRebalanceRepository rebalanceRepository;

    public List<Investiment> getAll() {
        return (List<Investiment>) repository.findAll();
    }

    public void save(Investiment investiment) {
        repository.save(investiment);
    }

    public void delete(String investimentCode) {
        Investiment investiment = repository.findInvestmentByInvestimentCode(investimentCode);
        WalletRebalance rebalance = rebalanceRepository.findByInvestiment(investiment);
        if(rebalance != null){
            rebalanceRepository.delete(rebalance);
        }
        repository.delete(investiment);
    }

    public void uploadFile(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                DataFormatter dataFormatter = new DataFormatter();
                File investimentFile = multipartToFile(file, fileName);
                Workbook workbook = WorkbookFactory.create(investimentFile);
                Sheet sheet = workbook.getSheetAt(0);
                sheet.forEach(row -> {
                    if (row.getRowNum() == 0) return;

                    String stonk = dataFormatter.formatCellValue(row.getCell(0));
                    String type = dataFormatter.formatCellValue(row.getCell(1));
                    String broker = dataFormatter.formatCellValue(row.getCell(2));
                    LocalDate firstApplication = LocalDate.parse(dataFormatter.formatCellValue(row.getCell(3)), formatter);
                    BigDecimal appliedAmount = new BigDecimal(dataFormatter.formatCellValue(row.getCell(4)).replace(".","").replace(",","."));
                    BigDecimal balance = new BigDecimal(dataFormatter.formatCellValue(row.getCell(5)).replace(".","").replace(",","."));
                    BigDecimal rentail = new BigDecimal(dataFormatter.formatCellValue(row.getCell(6)).replace(".","").replace(",","."));
                    BigDecimal portfolioShare = new BigDecimal(dataFormatter.formatCellValue(row.getCell(7)).replace(".","").replace(",","."));

                    Investiment importInvestiment = new Investiment();
                    importInvestiment.setInvestimentCode(stonk);
                    importInvestiment.setType(TypeInvestiment.fromString(StringUtils.isEmpty(type) ? TypeInvestiment.OTHER.getDescription() : type));
                    importInvestiment.setBroker(broker);
                    importInvestiment.setFirstDateApplication(firstApplication);
                    importInvestiment.setAppliedAmount(appliedAmount);
                    importInvestiment.setBalance(balance);
                    importInvestiment.setRentail(rentail);
                    importInvestiment.setPortfolioShare(portfolioShare);
                    importInvestiment.setAmount(0);

                    save(importInvestiment);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File tempFile = File.createTempFile(fileName,"");
        multipart.transferTo(tempFile);
        return tempFile;
    }

    private String getCodeStonkYahoo(String investimentCode) {
        String codeYahoo = investimentCode.substring(0, investimentCode.indexOf(" -"));
        return codeYahoo +".SA";
    }

    public void update(InvestimentDTO investimentDTO, String investimentCode) {

        Investiment investiment = repository.findInvestmentByInvestimentCode(investimentCode);
        investiment.setInvestimentCode(investimentDTO.getInvestimentCode());
        investiment.setType(TypeInvestiment.fromString(investimentDTO.getType()));
        investiment.setBroker(investimentDTO.getBroker());
        investiment.setFirstDateApplication(investimentDTO.getFirstDateApplication());
        investiment.setAppliedAmount(investimentDTO.getAppliedAmount());
        investiment.setBalance(investimentDTO.getBalance());
        investiment.setRentail(investimentDTO.getRentail());
        investiment.setPortfolioShare(investimentDTO.getPortfolioShare());
        investiment.setAmount(investimentDTO.getAmount());
        repository.save(investiment);
    }
}
