package com.fullcycle.admin.catalogo.domain;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

  @Test
  public void givenAValidParams_WhenCallNewCategory_ThenInstantiationCategory() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A Categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    assertNotNull(actualCategory);
    assertNotNull(actualCategory.getId());
    assertEquals(expectedName, actualCategory.getName());
    assertEquals(expectedDescription, actualCategory.getDescription());
    assertEquals(expectedIsActive, actualCategory.isActive());
    assertNotNull(actualCategory.getCreatedAt());
    assertNotNull(actualCategory.getUpdatedAt());
    assertNull(actualCategory.getDeletedAt());
  }

  @Test
  public void givenAnInvalidNullName_WhenCallNewCategoryAndValidate_ThenSouldReceiveError() {
    final String expectedName = null;
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' sould not be null";
    final var expectedDescription = "A Categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException =
        assertThrows(
            DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

    assertEquals(expectedErrorCount, actualException.getErrors().size());
    assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final var expectedName = "  ";
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' should not be empty";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException =
        assertThrows(
            DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

    assertEquals(expectedErrorCount, actualException.getErrors().size());
    assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void
      givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final var expectedName = "Fi ";
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException =
        assertThrows(
            DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

    assertEquals(expectedErrorCount, actualException.getErrors().size());
    assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void
      givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
    final var expectedName =
        """
                Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
                composição das posturas dos órgãos dirigentes com relação às suas atribuições.
                Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
                manutenção das novas proposições.
                """;
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    final var actualException =
        assertThrows(
            DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

    assertEquals(expectedErrorCount, actualException.getErrors().size());
    assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
    final var expectedName = "Filmes";
    final var expectedDescription = "  ";
    final var expectedIsActive = true;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

    assertNotNull(actualCategory);
    assertNotNull(actualCategory.getId());
    assertEquals(expectedName, actualCategory.getName());
    assertEquals(expectedDescription, actualCategory.getDescription());
    assertEquals(expectedIsActive, actualCategory.isActive());
    assertNotNull(actualCategory.getCreatedAt());
    assertNotNull(actualCategory.getUpdatedAt());
    assertNull(actualCategory.getDeletedAt());
  }

  @Test
  public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;

    final var actualCategory =
        Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(actualCategory.getUpdatedAt());
    Assertions.assertNotNull(actualCategory.getDeletedAt());
  }
}
