package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.dtos.UserDto;
import bg.softuni.mobilele.models.dtos.UserRoleDto;
import bg.softuni.mobilele.models.entities.Offer;
import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.models.entities.UserRole;
import bg.softuni.mobilele.repos.ModelRepository;
import bg.softuni.mobilele.repos.OfferRepository;
import bg.softuni.mobilele.repos.UserRepository;
import bg.softuni.mobilele.repos.UserRoleRepository;
import bg.softuni.mobilele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ModelRepository modelRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfferDto> findAllOffers() {
        List<OfferDto> offerDtos = new ArrayList<>();

        for (Offer offer : offerRepository.findAll()) {
            OfferDto offerDto = new OfferDto();
            modelMapper.map(offer, offerDto);
            offerDtos.add(offerDto);
        }

//        List<UserDto> userDtos = new ArrayList<>();
//        for (User userEntity : this.userRepository.findAll()) {
//            UserDto userDto = new UserDto();
//            modelMapper.map(userEntity, userDto);
//
//        }
        return offerDtos;
    }

    private static Optional<OfferDto> findOfferById(Long id, List<OfferDto> offerDtos) {
        return offerDtos
                .stream()
                .filter(offerDto -> id.equals(offerDto.getId()))
                .findAny();
    }

    private static Optional<OfferDto> findBrandDtoById(Long id, List<OfferDto> offerDtos) {

        return offerDtos
                .stream()
                .filter(offerDto -> id.equals(offerDto.getId()))
                .findAny();
    }

    private static Optional<UserRoleDto> findRoleDtoById(Long id, List<UserRoleDto> roleDtos) {

        return roleDtos
                .stream()
                .filter(roleDto -> id.equals(roleDto.getId()))
                .findAny();
    }
}