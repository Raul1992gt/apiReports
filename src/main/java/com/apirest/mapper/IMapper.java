package com.apirest.mapper;

public interface IMapper <I, O> {
    public O map(I i);
}
