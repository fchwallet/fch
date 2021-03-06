package com.xyz.fch_sp.app.modular.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private String name;
    private String avatar;
}
