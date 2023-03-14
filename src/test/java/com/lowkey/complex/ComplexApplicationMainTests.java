package com.lowkey.complex;

import com.lowkey.complex.util.OSUtil;
import org.junit.jupiter.api.Test;

/**
 * @author yuanjifan
 * @description
 * @date 2022年01月21日 15:41
 */
public class ComplexApplicationMainTests {

    @Test
    void testOSIPGet() {
        String localAddr = OSUtil.getLocalAddr();
        System.out.println(localAddr);
    }

}
