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
package com.xyz.fch_sp.app.core.common.constant.dictmap;

import com.xyz.fch_sp.app.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 横幅map
 *
 * @author fengshuonan
 * @date 2017-05-06 15:43
 */
public class BannerMap extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "横幅ID");
        put("imageUrl", "横幅图片");
        put("url", "横幅链接");
    }

    @Override
    protected void initBeWrapped() {

    }
}
