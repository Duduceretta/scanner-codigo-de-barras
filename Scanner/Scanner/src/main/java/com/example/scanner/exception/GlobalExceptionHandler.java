package com.example.scanner.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", exception.getMessage());
        return "redirect:" + exception.getRedirectUrl();
    }

    @ExceptionHandler(AutenticacaoException.class)
    public String handleAutenticacaoException(AutenticacaoException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", exception.getMessage());
        return "redirect:/login";
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public String handleEmailJaCadastradoException(EmailJaCadastradoException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", exception.getMessage());
        return "redirect:/cadastro";
    }

    @ExceptionHandler(SenhasNaoCoincidemExcepetion.class)
    public String handleSenhasNaoCoincidem(SenhasNaoCoincidemExcepetion exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", exception.getMessage());
        return "redirect:/cadastro";
    }

}