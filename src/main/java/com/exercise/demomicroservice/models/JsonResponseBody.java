package com.exercise.demomicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class JsonResponseBody {
    @Getter @Setter
    private int status;

    @Getter @Setter
    private Object response;
}
