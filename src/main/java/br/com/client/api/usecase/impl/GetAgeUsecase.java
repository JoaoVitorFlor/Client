package br.com.client.api.usecase.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GetAgeUsecase implements br.com.client.api.usecase.GetAgeUsecase {

  @Override
  public Long execute(final LocalDate birthDay) {
    return ChronoUnit.YEARS.between(birthDay, LocalDate.now());
  }
}
