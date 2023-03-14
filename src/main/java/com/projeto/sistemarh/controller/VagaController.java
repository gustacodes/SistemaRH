package com.projeto.sistemarh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import com.projeto.sistemarh.model.Candidato;
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

    public ModelAndView listaVagas() {

        ModelAndView mv = new ModelAndView("vaga/listavaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        mv.addObject("vagas", vagas);
        return mv;

    }
    
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {

        Vaga vaga = new Vaga();
        ModelAndView mv = new ModelAndView("vaga/detalhesvaga");
        mv.addObject("vaga", vaga);

        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);
        mv.addObject("candidatos", candidatos);

        return mv;

    }

    @RequestMapping("/deletarvaga")
    public String deletarVaga(long codigo) {

        Vaga vaga = vagaRepository.findByCodigo(codigo);
        vagaRepository.delete(vaga);

        return "redirect:/vagas";
    }

    public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes){
       
        if(result.hasErrors()){
            attributes.addFlashAttribute("Mensagem", "Verifique os campos");
        }

        if(candidatoRepository.findByRg(candidato.getRg()) != null){
            attributes.addFlashAttribute("Mensagem", "RG Duplicado");
            return "redirect:/{codigo}";
        }

        Vaga vaga = vagaRepository.findByCodigo(codigo);
        candidato.setVaga(vaga);
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("Mensagem", "Candidato adicionado com sucesso");

        return "redirect:/{codigo}";
    }

}

