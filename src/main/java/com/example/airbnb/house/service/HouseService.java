package com.example.airbnb.house.service;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.domain.Image;
import com.example.airbnb.house.dto.HouseCreateRequest;
import com.example.airbnb.house.repository.HouseRepository;
import com.example.airbnb.house.repository.ImageRepository;
import com.example.airbnb.house.repository.RoomRepository;
import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.repository.MemberRepository;
import java.io.IOException;
import java.util.Base64;
import lombok.NoArgsConstructor;
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
    public void saveHouse(HouseCreateRequest request, Long memberId) throws IOException {

        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() -> new IllegalArgumentException("없는 사람입니다."));
        House house = new House(
                request.getName(),
                request.getMaxPeople(),
                request.getAddress(),
                request.getIntroduce(),
                request.getDescription(),
                request.getPricePerPerson(),
                member
        );
        House savedHouse = houseRepository.save(house);

        for (MultipartFile file : request.getImages()) {
            if (!file.isEmpty()) {
                String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
                Image image = new Image(base64Image, house);
                // Save the image entity to the database
                // (Assuming you have a repository for Image)
                imageRepository.save(image);
            }
        }
    }
}
