package agrotechfields.measureshelter.controller;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import agrotechfields.measureshelter.dto.ImageDto;
import agrotechfields.measureshelter.model.Image;
import agrotechfields.measureshelter.service.ImageService;

public class ImageController {

  @Autowired
  private ImageService imageService;

  @GetMapping
  public ResponseEntity<List<Image>> findImages() {
    return ResponseEntity.status(HttpStatus.OK).body(this.imageService.findImages());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Image> findImageById(@PathVariable("id") String imageId) {
    ObjectId id = new ObjectId(imageId);
    Image image = this.imageService.findImageById(id);
    return ResponseEntity.status(HttpStatus.OK).body(image);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Image> findImageByName(@PathVariable("name") String imageName) {
    Image image = this.imageService.findImageByName(imageName);
    return ResponseEntity.status(HttpStatus.OK).body(image);
  }

  @PostMapping
  public ResponseEntity<Image> saveImage(@RequestBody ImageDto imageDto) {
    Image image = this.imageService.saveImage(imageDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(image);
  }
}
