package com.demo.flowershop.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import java.io.IOException;

import com.demo.flowershop.dtos.FlowerDto;
import com.demo.flowershop.models.FlowerModel;
import com.demo.flowershop.models.UserModel;
import com.demo.flowershop.repositories.FlowerRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/flower")
@CrossOrigin()
public class FlowerController {

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private ModelMapper modelmapper;

    private static Logger logger = Logger.getLogger("FlowerController.java");

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void addFlower(@RequestParam("name") String name, @RequestParam("type") String type,
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

    @Transactional
    @PostMapping("/delete/{name}")
    public void deleteFlower(@PathVariable("name") String name) {
        flowerRepository.deleteByName(name);
        System.out.println("Delete "+name);
        logger.info("Deleted flower with name " + name);
    }

    @GetMapping("/flowers")
    public List<FlowerDto> getFlowers() {
        List<FlowerModel> flowers = flowerRepository.findAll();
        return Arrays.asList(convertListToDto(flowers.toArray(new FlowerModel[flowers.size()])));
    }

    @ResponseBody
    @GetMapping("/{name}")
    public ResponseEntity getFlower(@PathVariable("name") String name) {
            Optional<FlowerModel> opt = flowerRepository.findByNameContains(name);
            if(opt.isPresent()){
                return ResponseEntity.ok(convertToDto(opt.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //FlowerModel retrievedFlower = flowerRepository.findByNameContains(name)
        //        .orElseThrow(() -> new Exception("Flower not found"));
    
    @ResponseBody
    @GetMapping("/type/{type}")
    public ResponseEntity getFlowersByType(@PathVariable("type") String type) {
    	List<FlowerModel> flowers = flowerRepository.findAll();
    	
    	final Predicate<FlowerModel> isType = FlowerModel -> FlowerModel.getType().equals(type);
    	final Stream<FlowerModel> flowersStream = flowers.stream().filter(isType);
    	
    	final List<FlowerModel> filteredList = flowersStream.collect(Collectors.toList());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(filteredList);
    	
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