package com.example.weizhi.sg_psi;

import com.example.weizhi.sg_psi.network.NetworkResponse;
import com.example.weizhi.sg_psi.network.WebService;
import com.example.weizhi.sg_psi.network.WebServiceImpl;
import com.example.weizhi.sg_psi.network.response.PsiJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class WebServiceTest {
    @Test
    public void getPsiTest() throws Exception {
        WebService ws = new WebServiceImpl(WebServiceImpl.BASE_URL);
        NetworkResponse<PsiJson> response = ws.getPsi(null, null);
        assertThat(response.httpStatusCode, is(200));
        assertThat(response.payload, is(notNullValue()));

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = mapper.writeValueAsString(response.payload);
        System.out.print(jsonString);
    }
}
