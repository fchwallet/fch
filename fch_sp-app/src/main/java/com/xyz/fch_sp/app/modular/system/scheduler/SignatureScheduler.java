package com.xyz.fch_sp.app.modular.system.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyz.fch_sp.app.core.common.annotion.TimeStat;
import com.xyz.fch_sp.app.core.util.DateUtil;
import com.xyz.fch_sp.app.core.util.HttpUtils;
import com.xyz.fch_sp.app.core.util.UnicodeUtil;
import com.xyz.fch_sp.app.modular.system.model.*;
import com.xyz.fch_sp.app.modular.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ConditionalOnProperty(prefix = "guns.scheduler-switch", name = "signature", havingValue = "true")
@Slf4j
public class SignatureScheduler {

    @Autowired
    private SignatureScheduler self;

    @Autowired
    private TxService txService;

    @Autowired
    private DataService dataService;

    private static FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd");

    @Scheduled(cron = "0/59 * * * * ?")
    public void work() {
        self.start();
    }

    @TimeStat
    public void start() {

        try {

            List<Tx> txList = txService.findStatus(0);

            for (Tx tx : txList) {

                String hex = tx.getData().substring(4, tx.getData().length());
                String str = UnicodeUtil.hexStringToString(hex);
                String[] hexStrs = str.split("\\|");

                for (int i = 0; i < hexStrs.length; i++) {

                    String agreementType = hexStrs[0];      // 协议类型

                    if (agreementType.equals("FEIP")) {
                        Integer agreementNumber = Integer.valueOf(hexStrs[1]);    // 协议编号
                        Integer versionNumber = Integer.valueOf(hexStrs[2]);     // 版本号
                        Data data = new Data();
                        if (hexStrs.length > 3) {
                            String nickname = hexStrs[3];
                            data.setNickname(nickname);
                            String cid = tx.getAddress().substring(tx.getAddress().length() - 4, tx.getAddress().length());
                            data.setCid(nickname + "_" + cid);
                        }
                        String extent = null;
                        if (hexStrs.length > 4)
                            extent = hexStrs[4];

                        data.setAgreementType(agreementType);
                        data.setAgreementNumber(agreementNumber);
                        data.setVersionNumber(versionNumber);

                        if (extent != null && extent.contains("#")) {
                            data.setExtent(extent);
                        }

                        data.setAddress(tx.getAddress());
                        Long cidCount = dataService.findByCidCount(tx.getAddress());

                        if (cidCount > 0) {
                            if (data.getNickname() == null && data.getExtent() == null)
                                dataService.deleteAddress(tx.getAddress());
                            else if (data.getNickname() != null || data.getExtent() != null || data.getCid() != null)
                                dataService.updataData(data);
                        } else {
                            if (data.getNickname() != null || data.getExtent() != null)
                                dataService.insertData(data);
                        }


                        Tx txup = new Tx();
                        txup.setTxid(tx.getTxid());
                        txup.setStatus(1);
                        txService.updateTx(txup);

                    }
                    break;

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

