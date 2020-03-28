package com.xyz.fch_sp.app.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("block")
public class Block extends Model<Block> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long blockcount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlockcount() {
        return blockcount;
    }

    public void setBlockcount(Long blockcount) {
        this.blockcount = blockcount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
