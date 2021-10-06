package com.wang.jasperreportsone.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2020-06-13 20:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String statusCode;
    private String statusName;
    private Object data;

}
