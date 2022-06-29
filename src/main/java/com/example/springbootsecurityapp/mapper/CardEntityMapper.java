package com.example.springbootsecurityapp.mapper;

import com.example.springbootsecurityapp.dto.CardRS;
import com.example.springbootsecurityapp.entity.CardEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CardEntityMapper {
  List<CardRS> toRS(final List<CardEntity> entities);
}
