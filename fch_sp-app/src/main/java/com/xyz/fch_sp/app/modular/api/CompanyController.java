package com.xyz.fch_sp.app.modular.api;

import com.xyz.fch_sp.app.core.common.exception.BizExceptionEnum;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.modular.system.model.Company;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private MemberService memberService;

    String uploadPath =  "/java/electronic_signature/upload-images/";

    @RequestMapping(value="/blImgUpload", method = RequestMethod.POST)
    public JsonResult blImgUpload(@RequestParam(value = "blImg") MultipartFile blImg, HttpServletRequest request) {

        String img1 = null;

        File uploadDirectory = new File(uploadPath);

        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }

        BufferedOutputStream bw = null;

        try {

            String fileName1 = blImg.getOriginalFilename();
            //判断是否有文件且是否为图片文件
            if(fileName1!=null && !"".equalsIgnoreCase(fileName1.trim()) && isImageFile(fileName1)) {
                //创建输出文件对象
                String name = UUID.randomUUID().toString()+ getFileType(fileName1);
                File outFile = new File( uploadPath+name);
                //拷贝文件到输出文件对象
                FileUtils.copyInputStreamToFile(blImg.getInputStream(), outFile);
                img1 = "http://120.27.232.146:8422/upload-images/"+name;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new JsonResult().addData("blImg", img1);
    }


    @RequestMapping(value="/pidImgUpload", method = RequestMethod.POST)
    public JsonResult pidImgUpload(@RequestParam(value = "pidImg") MultipartFile pidImg, HttpServletRequest request) {

        String img2 = null;

        File uploadDirectory = new File(uploadPath);

        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }

        BufferedOutputStream bw = null;

        try {

            String fileName2 = pidImg.getOriginalFilename();
            //判断是否有文件且是否为图片文件
            if(fileName2!=null && !"".equalsIgnoreCase(fileName2.trim()) && isImageFile(fileName2)) {
                //创建输出文件对象
                String name = UUID.randomUUID().toString()+ getFileType(fileName2);
                File outFile = new File(uploadPath+name);
                //拷贝文件到输出文件对象
                FileUtils.copyInputStreamToFile(pidImg.getInputStream(), outFile);
                img2 = "http://120.27.232.146:8422/upload-images/"+name;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new JsonResult().addData("pidImg", img2);
    }

    @RequestMapping(value="/bidImgUpload", method = RequestMethod.POST)
    public JsonResult bidImgUpload(@RequestParam(value = "bidImg") MultipartFile bidImg, HttpServletRequest request) {

        String img3 = null;

        File uploadDirectory = new File(uploadPath);

        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }

        BufferedOutputStream bw = null;

        try {

            String fileName3 = bidImg.getOriginalFilename();
            //判断是否有文件且是否为图片文件
            if(fileName3!=null && !"".equalsIgnoreCase(fileName3.trim()) && isImageFile(fileName3)) {
                //创建输出文件对象
                String name = UUID.randomUUID().toString()+ getFileType(fileName3);
                File outFile = new File(uploadPath+name);
                //拷贝文件到输出文件对象
                FileUtils.copyInputStreamToFile(bidImg.getInputStream(), outFile);
                img3 = "http://120.27.232.146:8422/upload-images/"+name;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new JsonResult().addData("bidImg", img3);
    }

    /**
     * 企业认证
     * @param company
     * @return
     */
    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insert(Company company){

        Member member = memberService.selectMemberInfo();

        Company memberCompany = companyService.findByMemberId(member.getId());

        if (memberCompany != null)
            return new JsonResult(BizExceptionEnum.USER_INFO_ERROR.getCode(),BizExceptionEnum.USER_INFO_ERROR.getMessage());

        Company companyAddress = companyService.findByAddress(company.getAddress());

        if (companyAddress != null)
            return new JsonResult(BizExceptionEnum.USER_ADDRESS_ERROR.getCode(),BizExceptionEnum.USER_ADDRESS_ERROR.getMessage());


        company.setMemberId(member.getId());

        companyService.insert(company);

        return new JsonResult();

    }

    private Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }


    @RequestMapping(value = "/findCompanyInfo", method = RequestMethod.POST)
    public JsonResult findMyAddress() {

        Member member = memberService.selectMemberInfo();

        Company company = companyService.findByMemberId(member.getId());

        return new JsonResult().addData("company", company);

    }
}
