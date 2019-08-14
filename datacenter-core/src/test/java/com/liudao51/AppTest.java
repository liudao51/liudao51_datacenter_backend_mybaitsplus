package com.liudao51;

import com.liudao51.datacenter.core.util.ShiroUtil;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void resetPassword() {
        String userName = "admin";
        String password = "123456";
        String result = ShiroUtil.encryptPassword(password, userName);
        System.out.println(result);
    }
}
