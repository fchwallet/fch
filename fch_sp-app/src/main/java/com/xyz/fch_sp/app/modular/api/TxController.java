package com.xyz.fch_sp.app.modular.api;

import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.modular.system.model.Company;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.model.Tx;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import com.xyz.fch_sp.app.modular.system.service.TxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/tx")
@Slf4j
public class TxController {

    @Autowired
    private TxService txService;



}
