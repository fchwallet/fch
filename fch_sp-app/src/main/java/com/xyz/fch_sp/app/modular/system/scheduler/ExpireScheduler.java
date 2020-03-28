package com.xyz.fch_sp.app.modular.system.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyz.fch_sp.app.core.common.annotion.TimeStat;
import com.xyz.fch_sp.app.core.util.DateUtil;
import com.xyz.fch_sp.app.core.util.HttpUtils;
import com.xyz.fch_sp.app.core.util.UnicodeUtil;
import com.xyz.fch_sp.app.modular.system.model.Company;
import com.xyz.fch_sp.app.modular.system.model.Contract;
import com.xyz.fch_sp.app.modular.system.model.Tx;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import com.xyz.fch_sp.app.modular.system.service.ContractService;
import com.xyz.fch_sp.app.modular.system.service.TxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConditionalOnProperty(prefix = "guns.scheduler-switch", name = "signature", havingValue = "true")
@Slf4j
public class ExpireScheduler {

    @Autowired
    private ExpireScheduler self;

    @Autowired
    private ContractService contractService;

    private static FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd");

    @Scheduled(cron = "0 0 0 */1 * ?")
    public void work() {
        self.start();
    }

    @TimeStat
    public void start(){

        try {

            List<Contract> list = contractService.findContractList();
            String date = sdf.format(new Date());

            for (Contract contract : list) {

                Boolean bl = DateUtil.comparetoTime(date, contract.getOverdueDate());

                if (!bl) {
                    Contract up = new Contract();
                    up.setStatus(4);
                    up.setId(contract.getId());
                    contractService.updateContract(up);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


