package br.com.client.api.usecase;

import java.time.LocalDate;

@FunctionalInterface
public interface GetAgeUsecase {
  Long execute(final LocalDate birthDate);
}
