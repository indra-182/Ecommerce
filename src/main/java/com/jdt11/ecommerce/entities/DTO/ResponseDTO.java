package com.jdt11.ecommerce.entities.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ResponseDTO<Payload> {

    private List<String> messages = new ArrayList<>();

    private Payload payload;
}
