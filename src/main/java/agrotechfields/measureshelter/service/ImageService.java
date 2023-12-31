package agrotechfields.measureshelter.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agrotechfields.measureshelter.dto.ImageDto;
import agrotechfields.measureshelter.exception.ObjectAlreadyExistsException;
import agrotechfields.measureshelter.exception.ObjectNotFoundException;
import agrotechfields.measureshelter.model.Image;
import agrotechfields.measureshelter.repository.ImageRepository;

@Service
public class ImageService {

  @Autowired
  private ImageRepository imageRepository;

  public List<Image> findImages() {
    return this.imageRepository.findAll();
  }

  public Image findImageById(ObjectId imageId) {
    Optional<Image> imageFound = this.imageRepository.findById(imageId);

    if (imageFound.isEmpty()) {
      throw new ObjectNotFoundException("Image with ID: " + imageId + " not found.");
    }

    return imageFound.get();
  }

  public Image findImageByName(String imageName) {
    Optional<Image> imageFound = this.imageRepository.findByName(imageName);

    if (imageFound.isEmpty()) {
      throw new ObjectNotFoundException("Image with Name: " + imageName + " not found.");
    }

    return imageFound.get();
  }

  public Image saveImage(ImageDto imageDto) {
    Optional<Image> imageFound = this.imageRepository.findByName(imageDto.getName());

    if (imageFound.isPresent()) {
      throw new ObjectAlreadyExistsException(
          "Image with Name: " + imageDto.getName() + " already exists.");
    }

    Image image = new Image(null, imageDto.getName(), imageDto.getBytes());

    return this.imageRepository.insert(image);
  }
}
