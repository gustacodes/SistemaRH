package com.projeto.sistemarh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.sistemarh.model.Vaga;
import com.projeto.sistemarh.repository.CandidatoRepository;
import com.projeto.sistemarh.repository.VagaRepository;

@Controller
public class VagaController {

    private VagaRepository vagaRepository;
    private CandidatoRepository candidatoRepository;
    
    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public String index() {
        return "vagas/form";
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    public String form(@Validated Vaga vaga, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()){
            attributes.addFlashAttribute("Mensagem", "Verifique os campos");
            return "redirect:/cliente";
        }
        vagaRepository.save(vaga);
        attributes.addFlashAttribute("Mensagem", "Vaga cadastrada com sucesso");
        return "redirect:/cliente";
    }
}
