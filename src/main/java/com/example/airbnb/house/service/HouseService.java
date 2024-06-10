package com.example.airbnb.house.service;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.domain.Image;
import com.example.airbnb.house.domain.Room;
import com.example.airbnb.house.dto.HouseCreateRequest;
import com.example.airbnb.house.dto.HouseDetailResponse;
import com.example.airbnb.house.dto.RoomCreateRequest;
import com.example.airbnb.house.repository.HouseRepository;
import com.example.airbnb.house.repository.ImageRepository;
import com.example.airbnb.house.repository.RoomRepository;
import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.repository.MemberRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HouseService {

    private static final String UPLOAD_DIR = "uploads/";

    private final HouseRepository houseRepository;

    private final RoomRepository roomRepository;
    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    public HouseService(final HouseRepository houseRepository, final RoomRepository roomRepository,
                        final ImageRepository imageRepository,
                        final MemberRepository memberRepository) {
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.imageRepository = imageRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void saveHouse(HouseCreateRequest houseCreateRequest, Long memberId, List<RoomCreateRequest> roomCreateRequest)  throws IOException {

        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() -> new IllegalArgumentException("없는 사람입니다."));
        House house = new House(
                houseCreateRequest.getName(),
                houseCreateRequest.getMaxPeople(),
                houseCreateRequest.getAddress(),
                houseCreateRequest.getIntroduce(),
                houseCreateRequest.getDescription(),
                houseCreateRequest.getPricePerDay(),
                member
        );
        House savedHouse = houseRepository.save(house);

        for (MultipartFile file : houseCreateRequest.getImages()) {
            if (!file.isEmpty()) {
                String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
                Image image = new Image(base64Image, house);
                imageRepository.save(image);
            }
        }

        for (RoomCreateRequest request : roomCreateRequest) {
            Room room = new Room(
                    request.getFurniutureCount(),
                    request.getRoomType(),
                    savedHouse
            );
            roomRepository.save(room);
        }
    }

    public Page<House> getHouses(Pageable pageable) {
        return houseRepository.findAll(pageable);
    }

    public Page<House> searchHousesByName(String name, Pageable pageable) {
        return houseRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public HouseDetailResponse getHouseById(Long id) {
        House house = houseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid house Id:" + id));
        List<Room> rooms = roomRepository.findByHouse(house);
        List<Image> images = imageRepository.findByHouse(house);
        return HouseDetailResponse.of(house, rooms, images);
    }
}
