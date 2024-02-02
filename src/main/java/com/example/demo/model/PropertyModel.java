package com.example.demo.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyModel {
    @Id
    private String id;

    private String property;
    private String type;
    private String input;
    private String value;
    private int comparison;
    private String gate;
}
