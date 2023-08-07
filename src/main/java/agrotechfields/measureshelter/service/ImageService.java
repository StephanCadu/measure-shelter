package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.ImageDto;
import agrotechfields.measureshelter.model.Image;
import agrotechfields.measureshelter.repository.ImageRepository;

@Service
public class ImageService {

  @Autowired
  private ImageRepository imageRepository;

  public List<Image> findImages() {
    return this.imageRepository.findAll();
  }

  public Image findImageById(int imageId) {
    Optional<Image> imageFound = this.imageRepository.findById(imageId);

    if (imageFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    return imageFound.get();
  }

  public Image findImageByName(String imageName) {
    Optional<Image> imageFound = this.imageRepository.findByName(imageName);

    if (imageFound.isEmpty()) {
      // throw ObjectNotFoundException
    }

    return imageFound.get();
  }

  public Image saveImage(ImageDto imageDto) {
    Optional<Image> imageFound = this.imageRepository.findByName(imageDto.getName());

    if (imageFound.isPresent()) {
      // throw ObjectAlreadyExistsException
    }

    Image image = new Image();
    image.setName(imageDto.getName());
    image.setBytes(imageDto.getBytes());

    return this.imageRepository.insert(image);
  }
}
