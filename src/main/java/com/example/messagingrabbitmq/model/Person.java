package com.example.messagingrabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 995267137098540267L;
    private String firstName;
    private String lastName;
}
