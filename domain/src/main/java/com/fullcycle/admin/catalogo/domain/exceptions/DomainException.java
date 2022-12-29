package com.fullcycle.admin.catalogo.domain.exceptions;

import com.fullcycle.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException {
  private final List<Error> erros;

  protected DomainException(final String aMessage, final List<Error> anErros) {
    super(aMessage);
    this.erros = anErros;
  }

  public static DomainException with(final Error anErro) {
    return new DomainException(anErro.message(), List.of(anErro));
  }

  public static DomainException with(final List<Error> anErros) {
    return new DomainException("", anErros);
  }

  public List<Error> getErrors() {
    return erros;
  }
}
