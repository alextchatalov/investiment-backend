package com.monitor.business;

import com.monitor.domain.Investiment;
import com.monitor.domain.WalletRebalance;
import com.monitor.repository.InvestimentRepository;
import com.monitor.repository.WalletRebalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class WalletRebalanceBusiness {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private WalletRebalanceRepository repository;

    @Autowired
    private InvestimentRepository investimentRepository;

    private List<Investiment> getAllInvestimet() {

        return investimentRepository.findAll();
    }

    public List<WalletRebalance> getAll() {
        List<WalletRebalance> walletRebalances = repository.findAll();
        if (walletRebalances.isEmpty()) {
            createInitialWalletRebalance();
        }
        return repository.findAll();
    }

    private void createInitialWalletRebalance() {

        investimentRepository.findAll().stream().forEach(investiment -> {
            WalletRebalance rebalance = new WalletRebalance();
            rebalance.setInvestiment(investiment);
//            rebalance.setNote(0);
//            rebalance.setPercentWallet(BigDecimal.ZERO);
//            rebalance.setIdealTotalApplied(BigDecimal.ZERO);
//            rebalance.setIdealPercentWallet(BigDecimal.ZERO);
//            rebalance.setIdealAmount(0);
//            rebalance.setAdValueApply(BigDecimal.ZERO);
//            rebalance.setAdPercentWallet(BigDecimal.ZERO);
//            rebalance.setAdAmount(0);
            save(rebalance);
        });

    }

    public void save(WalletRebalance WalletRebalance) {
        repository.save(WalletRebalance);
    }

    private String getCodeStonkYahoo(String code) {
        System.out.println(code);
        String codeYahoo = code.substring(0, code.indexOf(" -"));
        return codeYahoo +".SA";
    }

    public BigDecimal getPrice(Investiment investiment) throws IOException {
        String codeStonkYahoo = getCodeStonkYahoo(investiment.getInvestimentCode());
        return YahooFinance.get(codeStonkYahoo).getQuote().getPrice();
    }
}
