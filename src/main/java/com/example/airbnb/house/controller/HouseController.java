package com.example.airbnb.house.controller;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.dto.HouseCreateRequest;
import com.example.airbnb.house.dto.RoomCreateRequest;
import com.example.airbnb.house.service.HouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;
    private final ObjectMapper objectMapper;

    public HouseController(final HouseService houseService, final ObjectMapper objectMapper) {
        this.houseService = houseService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("host/host-house-register");
        modelAndView.addObject("houseCreateRequest", new HouseCreateRequest());
        return modelAndView;
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerHouse(
            @RequestParam("name") String name,
            @RequestParam("maxPeople") Integer maxPeople,
            @RequestParam("address") String address,
            @RequestParam("introduce") String introduce,
            @RequestParam("description") String description,
            @RequestParam("pricePerDay") Integer pricePerDay,
            @RequestParam("images") List<MultipartFile> images,
            @RequestParam("rooms") String roomsJson,
            HttpSession session
    ) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");

        HouseCreateRequest request = new HouseCreateRequest(name, maxPeople, address, introduce, description,
                pricePerDay, images);

        List<RoomCreateRequest> rooms = objectMapper.readValue(roomsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, RoomCreateRequest.class));

        houseService.saveHouse(request, memberId, rooms);

        return "Success";
    }

    @GetMapping("/houses")
    public ModelAndView listHouses(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<House> housePage;

        if (search != null && !search.isEmpty()) {
            housePage = houseService.searchHousesByName(search, pageable);
        } else {
            housePage = houseService.getHouses(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("house/house-list");
        modelAndView.addObject("housePage", housePage);
        modelAndView.addObject("search", search);
        return modelAndView;
    }

    @GetMapping("/houses/{id}")
    public ModelAndView getHouseDetails(@PathVariable Long id) {
        House house = houseService.getHouseById(id);
        ModelAndView modelAndView = new ModelAndView("house/house-detail");
        modelAndView.addObject("house", house);
        return modelAndView;
    }
}
