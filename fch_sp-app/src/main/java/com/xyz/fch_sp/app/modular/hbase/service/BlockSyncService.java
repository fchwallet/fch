/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xyz.fch_sp.app.modular.hbase.service;

import com.xyz.fch_sp.app.modular.hbase.model.Block;
import com.xyz.fch_sp.app.modular.hbase.model.BlockSync;

import java.util.List;

public interface BlockSyncService {

//    List<Block> list();

    void deleteByHash(String hash);

    List<BlockSync> selectAll();
}
