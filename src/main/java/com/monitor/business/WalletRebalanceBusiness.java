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
        return (List<Investiment>) investimentRepository.findAll();
    }

    public List<WalletRebalance> getAll() {
        List<WalletRebalance> walletRebalances = (List<WalletRebalance>) repository.findAll();
        if (walletRebalances.isEmpty()) {
            createInitialWalletRebalance();
        }

        return (List<WalletRebalance>) repository.findAll();
    }

    private void createInitialWalletRebalance() {
        for (Investiment investiment : investimentRepository.findAll()) {
            WalletRebalance rebalance = new WalletRebalance();
            rebalance.setInvestiment(investiment);
            save(rebalance);
        }

    }

    public void save(WalletRebalance WalletRebalance) {
        repository.save(WalletRebalance);
    }

    public void getRebalanceamentoCarteira() throws IOException {
        List<Investiment> wallet = getAllInvestimet();
        for (Investiment stonk : wallet) {
            if (stonk.isStonkOrFII()) {
                String codeStonkYahoo = getCodeStonkYahoo(stonk.getInvestimentCode());
                Stock itsa = YahooFinance.get(codeStonkYahoo);
                itsa.print();
            }
        }


    }

    private String getCodeStonkYahoo(String WalletRebalanceCode) {
        String codeYahoo = WalletRebalanceCode.substring(0, WalletRebalanceCode.indexOf(" -"));
        return codeYahoo +".SA";
    }
}
