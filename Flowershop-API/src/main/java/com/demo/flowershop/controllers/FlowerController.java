package com.demo.flowershop.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.io.IOException;
import java.lang.RuntimeException;

import com.demo.flowershop.dtos.FlowerDto;
import com.demo.flowershop.models.FlowerModel;
import com.demo.flowershop.repositories.FlowerRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/flower")
@CrossOrigin(origins = "http://localhost:4200")
public class FlowerController {

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private ModelMapper modelmapper;

    private static Logger logger = Logger.getLogger("FlowerController.java");

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void uploadFlower(@RequestParam("name") String name, @RequestParam("type") String type,
            @RequestParam("image") MultipartFile image) throws Exception {
        FlowerDto flower = new FlowerDto();
        String fileName = org.springframework.util.StringUtils.cleanPath(image.getOriginalFilename());

        try{
            if (fileName.contains("..")){
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }

            flower.setName(name);
            flower.setType(type);
            flower.setImg(image.getBytes());

            flowerRepository.saveAndFlush(convertToEntity(flower));
        } catch (IOException ex){
            throw new Exception("Could not store file " + fileName + ". Please try again!");
        }
    }

    @GetMapping("/delete/{id}")
    public void deleteFlower(@PathVariable("id") long id) {
        flowerRepository.deleteById(id);
        logger.info("Deleted flower with ID " + id);
    }

    @GetMapping
    public List<FlowerDto> getFlowers() {
        List<FlowerModel> flowers = flowerRepository.findAll();
        return Arrays.asList(convertListToDto(flowers.toArray(new FlowerModel[flowers.size()])));
    }

    @GetMapping("/{id}")
    public FlowerDto getFlower(@PathVariable("id") long id) throws Exception {
        FlowerModel retrievedFlower = flowerRepository.findById(id)
                .orElseThrow(() -> new Exception("Flower not found"));

        return convertToDto(retrievedFlower);
    }

    private FlowerDto convertToDto(FlowerModel flower) {
        return modelmapper.map(flower, FlowerDto.class);
    }

    private FlowerModel convertToEntity(FlowerDto flowerDto) {
        return modelmapper.map(flowerDto, FlowerModel.class);
    }

    private FlowerDto[] convertListToDto(FlowerModel[] flowers) {
        return modelmapper.map(flowers, FlowerDto[].class);
    }

}