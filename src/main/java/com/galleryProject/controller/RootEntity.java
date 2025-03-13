package com.galleryProject.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class RootEntity <T> {

    private Integer status;

    private T payload;

    private String errorMesssage;

    public static <T> RootEntity<T> ok(T payload){
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(200);
        rootEntity.setPayload(payload);
        rootEntity.setErrorMesssage(null);
        return rootEntity;

    }

    public static <T> RootEntity<T> error(String errorMessage){
        RootEntity <T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(500);
        rootEntity.setPayload(null);
        rootEntity.setErrorMesssage(errorMessage);
        return rootEntity;
    }



}
