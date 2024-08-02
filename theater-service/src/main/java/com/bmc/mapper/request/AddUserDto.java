package com.bmc.mapper.request;

import lombok.Data;

@Data
public class AddUserDto {
    private String name;
    private Integer age;
    private String mobNo;
    private String emailId;


}
