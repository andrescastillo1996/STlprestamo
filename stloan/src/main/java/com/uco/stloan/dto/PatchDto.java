package com.uco.stloan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PatchDto {

  String op;

  String key;

  String value;
}
