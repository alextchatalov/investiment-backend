package com.monitor.business;

import com.monitor.domain.Investiment;
import com.monitor.domain.WalletRebalance;
import com.monitor.dto.WalletRebalanceDTO;
import com.monitor.repository.InvestimentRepository;
import com.monitor.repository.WalletRebalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class WalletRebalanceBusiness {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private WalletRebalanceRepository repository;

    @Autowired
    private InvestimentRepository investimentRepository;

    Executor executor = Executors.newFixedThreadPool(4);
    CompletionService<WalletRebalanceDTO> completionService = new ExecutorCompletionService<>(executor);

    private List<Investiment> getAllInvestimet() {

        return investimentRepository.findAll();
    }

    public List<WalletRebalanceDTO> getAll() throws InterruptedException {


        List<WalletRebalance> walletRebalances = repository.findAll();
        if (walletRebalances.isEmpty()) {
            createInitialWalletRebalance();
        }
        List<Future<WalletRebalanceDTO>> tasks = new ArrayList<>();
        for (WalletRebalance rebalance : walletRebalances) {

            Future<WalletRebalanceDTO> task = completionService.submit(() -> {
                WalletRebalanceDTO rebalanceDTO = null;
                BigDecimal price = BigDecimal.ZERO;
                if (rebalance.getInvestiment().isStonkOrFII()) {
                    try {
                        price = getPrice(rebalance.getInvestiment());
                    } catch (IOException e) {
                        System.out.println("Não foi possivel recuperar o valor da cotação da ação: " + rebalance.getInvestiment().getInvestimentCode());
                    }
                }
                rebalanceDTO = WalletRebalanceDTO.builder()
                        .id(rebalance.getId())
                        .investiment(rebalance.getInvestiment())
                        .note(rebalance.getNote())
                        .percentWallet(rebalance.getPercentWallet())
                        .idealTotalApplied(rebalance.getIdealTotalApplied())
                        .idealPercentWallet(rebalance.getIdealPercentWallet())
                        .idealAmount(rebalance.getIdealAmount())
                        .adValueApply(rebalance.getAdValueApply())
                        .adPercentWallet(rebalance.getAdPercentWallet())
                        .adAmount(rebalance.getAdAmount())
                        .status(rebalance.getStatus())
                        .priceInResquest(price)
                        .amount(rebalance.getInvestiment().getAmount())
                        .build();
                return rebalanceDTO;
            });

            tasks.add(task);
        }
        List<WalletRebalanceDTO> rebalanceDTO = new ArrayList<>();
        int tasksFinished = 0;
        boolean error = false;
        while (tasksFinished < walletRebalances.size() && !error ) {
            Future<WalletRebalanceDTO> task = completionService.take();
            try {
                rebalanceDTO.add(task.get());
                tasksFinished ++;
            } catch (ExecutionException e) {
                error = true;
            }
        }

        return rebalanceDTO;
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

    public void save(WalletRebalance rebalance) {
        repository.save(rebalance);
        investimentRepository.save(rebalance.getInvestiment());
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
