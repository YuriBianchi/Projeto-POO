package com.projeto.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintValidatorContext;

class CPFValidatorTest {

    private CPFValidator cpfValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        context = null; // Não necessário para este teste simples
    }

    @Test
    void testCPFValido() {
        // CPF válido: 123.456.789-09 (valores de teste)
        assertTrue(cpfValidator.isValid("11144477735", context));
    }

    @Test
    void testCPFValidoComFormatacao() {
        // CPF válido com formatação
        assertTrue(cpfValidator.isValid("111.444.777-35", context));
    }

    @Test
    void testCPFInvalidoTodosDigitosIguais() {
        // CPF com todos dígitos iguais
        assertFalse(cpfValidator.isValid("11111111111", context));
    }

    @Test
    void testCPFInvalidoTamanho() {
        // CPF com menos de 11 dígitos
        assertFalse(cpfValidator.isValid("12345678", context));
    }

    @Test
    void testCPFInvalidoPrimeiroDigito() {
        // CPF com primeiro dígito verificador inválido
        assertFalse(cpfValidator.isValid("11144477736", context));
    }

    @Test
    void testCPFInvalidoSegundoDigito() {
        // CPF com segundo dígito verificador inválido
        assertFalse(cpfValidator.isValid("11144477734", context));
    }

    @Test
    void testCPFNulo() {
        // CPF nulo deve ser inválido
        assertFalse(cpfValidator.isValid(null, context));
    }

    @Test
    void testCPFVazio() {
        // CPF vazio deve ser inválido
        assertFalse(cpfValidator.isValid("", context));
    }

    @Test
    void testCPFComApenasLetras() {
        // CPF com apenas letras
        assertFalse(cpfValidator.isValid("abcdefghijk", context));
    }
}
