package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import com.fullcycle.admin.catalogo.domain.validation.Validator;

import static java.util.Objects.isNull;

public class CategoryValidator extends Validator {
  public static final int NAME_MAX_LENGTH = 255;
  public static final int NAME_MIN_LENGTH = 3;
  private final Category category;

  protected CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
    super(aHandler);
    this.category = aCategory;
  }

  @Override
  public void validate() {
    final var name = this.category.getName();

    if (isNull(name)) {
      this.validationHandler().append(new Error("'name' should not be null"));
      return;
    }

    if (name.isBlank()) {
      this.validationHandler().append(new Error("'name' should not be empty"));
    }

    final int length = name.trim().length();
    if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
      this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
    }
  }
}
