package com.xyz.fch_sp.app.core.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Gaode location.
 *
 * @author paascloud.net @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GaodeLocation extends GaodeBaseDto {
    private String province;
    private String city;
    private String adcode;
    private String rectangle;
}
