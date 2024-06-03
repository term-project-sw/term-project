package com.example.airbnb.house.controller;

import com.example.airbnb.house.dto.HouseCreateRequest;
import com.example.airbnb.house.service.HouseService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/house")
public class HouseController {

    private HouseService houseService;

    public HouseController(final HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("host/host-house-register");
        modelAndView.addObject("houseCreateRequest", new HouseCreateRequest());
        return modelAndView;
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerHouse(@RequestParam("name") String name,
                                @RequestParam("maxPeople") Integer maxPeople,
                                @RequestParam("address") String address,
                                @RequestParam("introduce") String introduce,
                                @RequestParam("description") String description,
                                @RequestParam("pricePerPerson") Integer pricePerPerson,
                                @RequestParam("images") List<MultipartFile> images,
                                HttpSession session
    ) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");

        HouseCreateRequest request = new HouseCreateRequest(name, maxPeople, address, introduce, description,
                pricePerPerson, images);
        houseService.saveHouse(request, memberId);
        return "Success";
    }
}
