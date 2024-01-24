package com.encore.board.Dto;

import lombok.Data;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Data
public class AuthorSaveReqDto {
    private String name;
    private String email;
    private String password;
}